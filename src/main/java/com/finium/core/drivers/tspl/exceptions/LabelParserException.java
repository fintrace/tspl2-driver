/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * LabelParserException.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.exceptions;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class LabelParserException extends RuntimeException {
    private static final long serialVersionUID = -7032802716798268348L;
    private String message;

    /**
     *
     */
    public LabelParserException() {
        super();
    }

    /**
     * @param message
     */
    public LabelParserException(String message) {
        super(message);
        this.message = message;
    }
}
