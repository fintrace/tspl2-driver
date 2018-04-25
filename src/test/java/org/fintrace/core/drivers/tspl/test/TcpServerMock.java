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
package org.fintrace.core.drivers.tspl.test;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.fintrace.core.drivers.tspl.DriverConstants.CR_BYTES;
import static org.fintrace.core.drivers.tspl.DriverConstants.LF;
import static org.fintrace.core.drivers.tspl.DriverConstants.LF_BYTES;

@Slf4j
public class TcpServerMock {

    private final InetSocketAddress socketAddress;

    // Selector: multiplexor of SelectableChannel objects
    private Selector selector;

    // ServerSocketChannel: selectable channel for stream-oriented listening sockets
    private ServerSocketChannel serverChannel;

    private boolean isRunning = false;

    private List<String> receivedMessages = new ArrayList<>();

    /**
     * @param port
     * @throws IOException
     */
    public TcpServerMock(int port) throws IOException {
        this("localhost", port);
    }

    /**
     * @param host
     * @param port
     * @throws IOException
     */
    public TcpServerMock(String host, int port) throws IOException {
        this(new InetSocketAddress(host, port));
    }

    /**
     * @param address
     * @throws IOException
     */
    public TcpServerMock(InetSocketAddress address) throws IOException {
        this.socketAddress = address;
        this.selector = Selector.open(); // selector is open here
        this.serverChannel = ServerSocketChannel.open();
    }

    /**
     * @throws IOException
     */
    public void start() throws IOException {
        // Binds the channel's socket to a address and configures the socket to listen for connections
        serverChannel.bind(socketAddress);

        // Adjusts this channel's blocking mode.
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, serverChannel.validOps(), null);
        isRunning = true;

        log.info("TCP Mock server is running and waiting for new connection and buffer select...");
        while (isRunning) {
            // Selects a set of keys whose corresponding channels are ready for I/O operations
            selector.select();

            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()) {
                SelectionKey key = selectionKeyIterator.next();

                // Tests whether this key's channel is ready to accept a new socket connection
                if (key.isAcceptable()) {
                    SocketChannel clientChannel = serverChannel.accept();

                    // Adjusts this channel's blocking mode to false
                    clientChannel.configureBlocking(false);

                    // Operation-set bit for read operations
                    clientChannel.register(selector, SelectionKey.OP_READ);

                    log.info("Connection Accepted: {}", clientChannel.getLocalAddress());
                } else if (key.isReadable()) { // Tests whether this key's channel is ready
                    // for reading
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(256);
                    ByteBuffer readDataBuffer = ByteBuffer.allocate(256);

                    readBuffer.clear();
                    int readed = clientChannel.read(readBuffer);
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

                            if (data[0] == 27
                                    && data[1] == 33
                                    && data[2] == 63) {
                                // Operation-set bit for read operations
                                clientChannel.register(selector, SelectionKey.OP_WRITE);
                                receivedMessages.add("!?");
                            }
                            if (log.isDebugEnabled()) {
                                log.debug("Message received: {}", new String(data));
                            }
                            readDataBuffer.clear();
                        }
                    }
                } else if (key.isWritable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    if (receivedMessages.contains("!?")) {
                        ByteBuffer writeBuffer = ByteBuffer.wrap((((char)0) + LF)
                                .getBytes(US_ASCII));
                        while (writeBuffer.hasRemaining()) {
                            clientChannel.write(writeBuffer);
                        }
                        receivedMessages.remove("!?");
                    }
                }
                selectionKeyIterator.remove();
            }
        }

        log.info("Stopping server");
        try {
            if (serverChannel != null)
                serverChannel.close();
            if (selector != null)
                selector.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     *
     */
    public void stop() {
        log.info("Stop signal is received");
        isRunning = false;
        clearMessages();
    }

    /**
     * @return
     */
    public boolean isRunning() {
        return isRunning;
    }

    public List<String> getRecievedMessages() {
        return receivedMessages;
    }

    public void clearMessages() {
        this.receivedMessages.clear();
    }
}
