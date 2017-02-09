/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 *
 * ClientListener.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.listeners;

import com.finium.core.drivers.tspl.connection.TsplConnectionClient;

/**
 * This listener provide a callback structure or listener pattern template for
 * monitoring the <code>TsplConnectionClient</code> statuses. The caller of any
 * method in this interface must be from an independent Thread.
 *
 * @author Venkaiah Chowdary Koneru
 */
public interface ClientListener {

    /**
     * This method will be invoked by client when a connection has successfully
     * established.
     *
     * @param client The client that has established the connection.
     */
    void connectionEstablished(TsplConnectionClient client);

    /**
     * This method will be invoked by client when a connection has been dropped
     * successfully, intentionally or not.
     *
     * @param client The client that has dropped the connection.
     */
    void connectionLost(TsplConnectionClient client);

    /**
     * This method will be invoked by client if it has experience IO exception
     * that is not recoverable. For example when network is down or USB removal, Client must
     * treat this as connection down and thus, it need to re-establish
     * connection.
     *
     * @param client The client that failed the connection.
     * @param e      Exception that it has encounter, most likely it will be an
     *               IOException.
     */
    void connectionIsFailing(TsplConnectionClient client, Exception e);
}
