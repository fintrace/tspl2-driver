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
import org.fintrace.core.drivers.tspl.commands.label.TscLabel;
import org.fintrace.core.drivers.tspl.exceptions.PrinterException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * TCP/IP client communication implementation for TSPL2 device
 *
 * @author Venkaiah Chowdary Koneru
 */
@Slf4j
public class EthernetConnectionClient extends AbstractConnectionClient
        implements TSPLConnectionClient {
    private final String ipAddress;
    private final int port;
    private Socket clientSocket = null;
    private DataOutputStream outToServer = null;

    /**
     * @param ipAddress
     * @param port
     */
    public EthernetConnectionClient(String ipAddress, int port) {
        this.ipAddress = ipAddress;
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
        if (isConnected()) {
            return;
        }

        try {
            clientSocket = new Socket(ipAddress, port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());

            isConnected = true;
            notifyConnection();

        } catch (IOException e) {
            log.error("Error connecting to printer on {}:{}", ipAddress, port);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect() {
        if (!isConnected())
            return;

        try {

            if (outToServer != null) {
                outToServer.close();
            }

            if (clientSocket != null) {
                clientSocket.close();
            }

            isConnected = false;
            notifyConnectionLost();

        } catch (IOException e) {
            log.error("Error disconnecting from printer", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shutdown() {
        outToServer = null;
        clientSocket = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(TscLabel label) {
        send(label.getTsplCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(String tsplMessage) {
        send(tsplMessage.getBytes(US_ASCII));
    }

    /**
     * @param message
     */
    private void send(byte[] message) {
        if (!isConnected()) {
            throw new PrinterException("Printer is not connected");
        }

        try {
            outToServer.write(message);
        } catch (IOException e) {
            log.error("Error sending TSPL message ", e);
        }
    }
}
