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

import org.fintrace.core.drivers.tspl.listeners.ClientListener;
import org.fintrace.core.drivers.tspl.listeners.DataListener;

import javax.usb.event.UsbPipeDataEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * Generic client connection implementation for TSPL2 device
 *
 * @author Venkaiah Chowdary Koneru
 */
public abstract class AbstractConnectionClient implements TSPLConnectionClient {
    protected List<ClientListener> clientListeners = new ArrayList<>();
    protected List<DataListener> dataListeners = new ArrayList<>();
    protected ExecutorService executorService = Executors.newCachedThreadPool();
    protected boolean isConnected = Boolean.FALSE;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addClientListener(ClientListener listener) {
        this.clientListeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeClientListener(ClientListener listener) {
        this.clientListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDataListener(DataListener listener) {
        this.dataListeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDataListener(DataListener listener) {
        this.dataListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Notifies all the dataListeners about the received message.
     *
     * @param dataEvent
     */
    protected void notifyReadDataEvent(UsbPipeDataEvent dataEvent) {
        dataListeners.forEach((DataListener dataListener) -> {
            executorService.execute(() -> {
                dataListener.messageReceived(new String(dataEvent.getData(), US_ASCII));
            });
        });
    }

    /**
     * Notifies all the dataListeners about the submitted message.
     *
     * @param dataEvent
     */
    protected void notifyWriteDataEvent(UsbPipeDataEvent dataEvent) {
        dataListeners.forEach((DataListener dataListener) -> {
            executorService.execute(() -> {
                dataListener.messageSent(new String(dataEvent.getData(), US_ASCII));
            });
        });
    }

    /**
     * All the client listeners will be informed about the successful
     * connection establishment to the TSPL2 device.
     */
    protected void notifyConnection() {
        clientListeners.forEach(listener -> listener.connectionEstablished(this));
    }

    /**
     * All the client listeners will be informed about loss of connection
     * to the TSPL2 device.
     */
    protected void notifyConnectionLost() {
        clientListeners.forEach(listener -> listener.connectionLost(this));
    }

    /**
     * All the client listeners will be informed about persistent connection failure
     * to the TSPL2 device.
     */
    protected void notifyConnectionFailed() {
        clientListeners.forEach(listener -> listener.connectionIsFailing(this, null));
    }
}
