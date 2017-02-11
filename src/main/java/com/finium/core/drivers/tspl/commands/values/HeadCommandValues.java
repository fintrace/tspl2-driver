/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * HeadCommandValues.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.values;

import com.finium.core.drivers.tspl.commands.device.DeviceConfigCommand;

/**
 * HEAD command values
 *
 * @author Venkaiah Chowdary Koneru
 * @see DeviceConfigCommand#HEAD
 */
public enum HeadCommandValues implements CommandValues<String> {
    /**
     * Turn off the "HEAD OPEN" sensor
     */
    OFF,

    /**
     * Turn on the "HEAD OPEN" sensor
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
