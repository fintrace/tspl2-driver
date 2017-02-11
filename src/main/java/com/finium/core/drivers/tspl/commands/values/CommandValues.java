/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * CommandValues.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.values;

/**
 * TSPL2 Command values
 *
 * @author Venkaiah Chowdary Koneru
 */
public interface CommandValues<T> {

    /**
     * returns the TSPL2 comman's value.
     *
     * @return command value
     */
    T getCommandValue();
}
