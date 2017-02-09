/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * BackValues.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.values;

import com.finium.core.drivers.tspl.commands.DeviceConfigCommand;

/**
 * BACK command values
 *
 * @author Venkaiah Chowdary Koneru
 * @see DeviceConfigCommand#BACK
 */
public enum BackCommandValues implements CommandValues<String> {
    /**
     * Disable back function.
     */
    OFF,

    /**
     * Enable back function.
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
