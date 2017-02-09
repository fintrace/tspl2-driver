/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * ConnectionClientException.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.connection;

/**
 * Exception that will be thrown by the internal process of Zebra client.
 *
 * @author Venkaiah Chowdary Koneru
 */
public class ConnectionClientException extends Exception{
    private static final long serialVersionUID = 2031498887869905758L;

    /**
     * Default constructor
     */
    public ConnectionClientException() {
        super();
    }

    /**
     * Constructor with message and possible causing exception
     *
     * @param arg0
     *            Additional message regarding the error.
     * @param arg1
     *            The exception/throwable that causing the error.
     */
    public ConnectionClientException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * Constructor with message.
     *
     * @param arg0
     *            The message about causing error.
     */
    public ConnectionClientException(String arg0) {
        super(arg0);
    }

    /**
     * Constructor with causing exception.
     *
     * @param arg0
     *            The exception/throwable that causing the error.
     */
    public ConnectionClientException(Throwable arg0) {
        super(arg0);
    }
}
