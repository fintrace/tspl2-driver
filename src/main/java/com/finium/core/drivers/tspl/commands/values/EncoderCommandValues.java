/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * EncoderCommandValues.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.values;

import com.finium.core.drivers.tspl.commands.DeviceConfigCommand;

/**
 * ENCODER command values
 *
 * @author Venkaiah Chowdary Koneru
 * @see DeviceConfigCommand#ENCODER
 */
public enum EncoderCommandValues implements CommandValues<String> {
    /**
     * Disable ribbon encoder sensor.
     */
    OFF,

    /**
     * Enable ribbon encoder sensor.
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
