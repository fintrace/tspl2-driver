/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * TSPLCommand.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands;

import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;

/**
 * TSPL2 Command set
 *
 * @author Venkaiah Chowdary Koneru
 */
public interface TSPLCommand<T> {

    /**
     * returns the TSPL2 command
     *
     * @return
     * @throws UnsupportedCharsetException
     */
    T getCommand() throws UnsupportedEncodingException;
}
