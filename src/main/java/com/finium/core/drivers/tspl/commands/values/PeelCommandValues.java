/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * PeelCommandValues.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.values;

import com.finium.core.drivers.tspl.commands.DeviceConfigCommand;

/**
 * PEEL command values
 *
 * @author Venkaiah Chowdary Koneru
 * @see DeviceConfigCommand#PEEL
 */
public enum PeelCommandValues implements CommandValues<String> {
    /**
     * Disable the self-peeing function
     */
    OFF,

    /**
     * Enable the self-peeling function
     */
    ON;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandValue() {
        return this.name();
    }
}
