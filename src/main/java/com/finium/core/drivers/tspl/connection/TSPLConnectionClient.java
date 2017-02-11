/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * TSPLConnectionClient.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.connection;


import com.finium.core.drivers.tspl.listeners.ClientListener;
import com.finium.core.drivers.tspl.listeners.DataListener;

import javax.usb.UsbException;

/**
 * This interface define the Connection level contract with the TSPL (a.k.a TSC branded printer) system.
 * Implementation can be done with TCP/IP Socket, USB or other mechanism.
 *
 * @author Venkaiah Chowdary Koneru
 */
public interface TSPLConnectionClient {

    /**
     * Add a ClientListener to this client. This method will not check for
     * duplicated listener. So please be careful not to add the listener twice,
     * cause if you do, your listener will receive multiple notification.
     *
     * @param listener Listener to add.
     */
    void addClientListener(ClientListener listener);

    /**
     * Remove the previously added ClientListener. If the specified listener listener
     * doesn't exist, the method will not do anything.
     *
     * @param listener The listener to remove.
     */
    void removeClientListener(ClientListener listener);

    /**
     * Add a DataListener to this client. This method will not check for duplicated listener.
     */
    void addDataListener(DataListener listener);

    /**
     * Remove the previously added DataListener.
     * If the specified listener doesn't exist, the method will not do anything.
     */
    void removeDataListener(DataListener listener);

    /**
     * initializes the printer with defaults.
     */
    void init();

    /**
     * Instruct the client to initiate the connection. This method will return
     * immediately. You should listen for connection status using the provided
     * listeners call backs. (ie, ClientListener). OR, using the isConnected
     * method. Connection might not happen instantly after the method call.
     */
    void connect() throws UsbException;

    /**
     * Instruct the client to execute disconnect routines. This method will
     * return immediately. You should listen for connection status using the
     * provided listeners call backs. (ie, ClientListener). OR, using is
     * Connected method. Connection close might not happen instantly after the
     * method call.
     */
    void disconnect();

    /**
     * Shutdown the communication channels to the printer.
     * Once shutdown is invoked, Client should invoke TsplConnectionClient#init() again
     * in order to initiate the communication.
     */
    void shutdown();

    /**
     * Check the connectivity status.
     *
     * @return <code>true</code> if connection is currently established, or
     * <code>false</code> if the client is disconnected.
     */
    boolean isConnected();

    /**
     * Instruct the client to send TSPL2 communication message. This method will
     * either return immediately or block until the submission is complete depending on the implementation.
     * Listen for response using the provided listeners call backs. (ie, DataListener).
     *
     * @param tsplMessage The message to send.
     */
    void send(String tsplMessage);

    /**
     *
     * @param message
     */
    void send(byte[] message);
}
