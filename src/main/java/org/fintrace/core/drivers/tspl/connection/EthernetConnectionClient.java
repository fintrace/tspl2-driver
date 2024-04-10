/*
 * Copyright 2017 fintrace (https://fintrace.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fintrace.core.drivers.tspl.connection;

import lombok.extern.slf4j.Slf4j;
import org.fintrace.core.drivers.tspl.commands.label.TSPLLabel;
import org.fintrace.core.drivers.tspl.exceptions.ConnectionClientException;
import org.fintrace.core.drivers.tspl.exceptions.PrinterException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.fintrace.core.drivers.tspl.DriverConstants.CR_BYTES;
import static org.fintrace.core.drivers.tspl.DriverConstants.LF_BYTES;

/**
 * This class is an implementation of <code>TSPLConnectionClient</code> That
 * will communicate with supported TSPL2 based printer using TCP/IP connectivity protocol and
 * their supported medium (LAN, INTERNET or simple cross UTP).
 * <p>
 * This implementation is using high performance NIO non blocking method.
 * However, it employs single independent thread that do the channel selector
 * and invocation.
 * <p>
 * IMPORTANT : Each notifications fired by this class for each of its registered
 * listeners is done by their own independent thread. This means new thread for
 * each notification. Thus, the listener implementation added into this
 * socket do not need to implement "Fast Return" method and will not clog the
 * selector thread.
 *
 * @author Venkaiah Chowdary Koneru
 */
@Slf4j
public class EthernetConnectionClient extends AbstractConnectionClient
        implements TSPLConnectionClient {
    private final String host;
    private final int port;
    private ExecutorService connectionExecutorService = Executors.newSingleThreadExecutor();
    private SocketChannel channel;
    private Selector selector;

    // The buffer into which we'll read data when it's available
    private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
    private ByteBuffer readDataBuffer = ByteBuffer.allocate(8192);

    private Runnable connectionRunnable = new Runnable() {
        public void run() {
            if (!isConnected) {
                try {
                    selector = Selector.open();
                    channel = SocketChannel.open();
                    channel.configureBlocking(false);

                    channel.register(selector, SelectionKey.OP_CONNECT);
                    channel.connect(new InetSocketAddress(host, port));

                    alive = true;
                    while (alive && !Thread.interrupted()) {
                        selector.select(1000);
                        Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                        while (keys.hasNext()) {
                            SelectionKey key = keys.next();
                            keys.remove();
                            if (!key.isValid()) {
                                continue;
                            } else if (key.isConnectable()) {
                                makeConnect(key);
                                isConnected = true;
                                notifyConnection();
                                channel.register(selector, SelectionKey.OP_READ);
                            } else if (key.isReadable()) {
                                read(key);
                            } else {
                                log.warn("Unrecognized key {}", key);
                            }
                        }
                    }
                } catch (IOException ioe) {
                    log.error("", ioe);
                    notifyConnectionFailed();
                } finally {
                    if (isConnected) {
                        notifyDisconnected();
                    }
                    isConnected = false;
                    alive = false;
                    try {
                        if (channel != null)
                            channel.close();
                        if (selector != null)
                            selector.close();
                    } catch (IOException e) {
                        log.error("", e);
                    }
                }
            }
        }
    };

    /**
     * Constructor
     *
     * @param host The zebra printer host address or IP address.
     * @param port The port number on zebra printer that will accept the
     *             connection.
     */
    public EthernetConnectionClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        log.info("Initialized");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect() {
        if (isConnected || alive)
            return;

        connectionExecutorService.execute(connectionRunnable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect() {
        if (!alive) {
            return;
        }

        alive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shutdown() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(TSPLLabel label) {
        send(label.getTsplCode());
    }

    /**
     * @param message
     */
    protected void send(byte[] message) {
        if (!isConnected) {
            throw new PrinterException("Printer is not connected");
        }

        try {
            ByteBuffer bb = ByteBuffer.wrap(message);
            while (bb.hasRemaining()) {
                channel.write(bb);
            }
            notifyMessageSent(new String(message));
        } catch (IOException ioe) {
            notifyMessageSendFailed(new ConnectionClientException("Failed to send message.", ioe),
                    new String(message));
        }
    }

    /**
     * @param key
     * @throws IOException
     */
    private void makeConnect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        channel.configureBlocking(false);
    }

    /**
     * @param key
     * @throws IOException
     */
    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            readBuffer.clear();
            int readed = channel.read(readBuffer);
            readBuffer.flip();
            while (readed > 0 && readBuffer.hasRemaining()) {
                byte b = readBuffer.get();
                if (b != CR_BYTES[0] && b != LF_BYTES[0]) {
                    readDataBuffer.put(b);
                }
                if (b == LF_BYTES[0]) {
                    readDataBuffer.put((byte) '\n');
                    readDataBuffer.flip();

                    byte[] data = new byte[readDataBuffer.limit()];
                    readDataBuffer.get(data);

                    notifyMessageReceived(new String(data, US_ASCII));
                    readDataBuffer.clear();
                }
            }
            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            readDataBuffer.clear();
            readBuffer.clear();
            readBuffer.limit(0);
            key.cancel();
            channel.close();
        }
    }
}
