/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * PrinterException.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.connection;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class PrinterException extends RuntimeException{
    private static final long serialVersionUID = -7032802716798268348L;
    private String message;

    /**
     *
     */
    public PrinterException() {
        super();
    }

    /**
     *
     * @param message
     */
    public PrinterException(String message) {
        super(message);
        this.message = message;
    }
}
