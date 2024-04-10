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

import org.fintrace.core.drivers.tspl.exceptions.ConnectionClientException;
import org.fintrace.core.drivers.tspl.listeners.ClientListener;
import org.fintrace.core.drivers.tspl.listeners.DataListener;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * Generic client connection implementation for TSPL2 device
 * <p>
 * IMPORTANT : Each notifications fired by this class for each of its registered
 * listeners is done by their own independent thread. This means new thread for
 * each notification. Thus, the listener implementation added into this
 * socket do not need to implement "Fast Return" method and will not clog the
 * selector thread.
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
public abstract class AbstractConnectionClient implements TSPLConnectionClient {
    protected List<ClientListener> clientListeners = new ArrayList<>();
    protected List<DataListener> dataListeners = new ArrayList<>();
    protected ExecutorService listenerExecutorService = Executors.newCachedThreadPool();
    protected boolean isConnected = Boolean.FALSE;
    protected boolean alive = Boolean.FALSE;

    private Charset charset = US_ASCII;

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
     * @param message
     */
    protected void notifyMessageReceived(String message) {
        dataListeners.forEach(dataListener -> listenerExecutorService.execute(() ->
                dataListener.messageReceived(message)
        ));
    }

    /**
     * Notifies all the dataListeners about the submitted message.
     *
     * @param message
     */
    protected void notifyMessageSent(String message) {
        dataListeners.forEach(dataListener -> listenerExecutorService.execute(() ->
                dataListener.messageSent(message)
        ));
    }

    /**
     * Notifies all the dataListeners about the failure to send message.
     *
     * @param exception
     * @param messageToSend
     */
    protected void notifyMessageSendFailed(final ConnectionClientException exception,
                                           final String messageToSend) {
        /*
         * For each listener, create a separate independent thread for firing
         * the listener methods.
         */
        dataListeners.forEach(dataListener -> listenerExecutorService.execute(() ->
                dataListener.messageSendFailed(exception, messageToSend)
        ));
    }

    /**
     * All the client listeners will be informed about the successful
     * connection establishment to the TSPL2 device.
     */
    protected void notifyConnection() {
        /*
         * For each listener, create a separate independent thread for firing
         * the listener methods.
         */
        clientListeners.forEach(clientListener -> listenerExecutorService.execute(() ->
                clientListener.connectionEstablished(AbstractConnectionClient.this)
        ));
    }

    /**
     * All the client listeners will be informed about loss of connection
     * to the TSPL2 device.
     */
    protected void notifyConnectionLost() {
        /*
         * For each listener, create a separate independent thread for firing
         * the listener methods.
         */
        clientListeners.forEach(clientListener -> listenerExecutorService.execute(() ->
                clientListener.connectionLost(AbstractConnectionClient.this)
        ));
    }

    /**
     * All the client listeners will be informed about persistent connection failure
     * to the TSPL2 device.
     */
    protected void notifyConnectionFailed() {
        /*
         * For each listener, create a separate independent thread for firing
         * the listener methods.
         */
        clientListeners.forEach(clientListener -> listenerExecutorService.execute(() ->
                clientListener.connectionIsFailing(AbstractConnectionClient.this, null)
        ));
    }

    /**
     *
     */
    protected void notifyDisconnected() {
        /*
         * For each listener, create a separate independent thread for firing
         * the listener methods.
         */
        clientListeners.forEach(clientListener -> listenerExecutorService.execute(() ->
                clientListener.connectionLost(AbstractConnectionClient.this)
        ));
    }

    public void send(String message) {
        send(message.getBytes(charset));
    }

    protected abstract void send(byte[] message);

    /** Sets the charset for transmitting Strings as bytes.
     * Does not add the corresponding CODEPAGE command. */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public Charset getCharset() {
        return charset;
    }

}
