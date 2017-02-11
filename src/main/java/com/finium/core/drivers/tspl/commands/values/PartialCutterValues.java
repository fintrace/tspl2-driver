/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * PartialCutterValues.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.values;

import com.finium.core.drivers.tspl.commands.device.DeviceConfigCommand;

/**
 * PARTIAL_CUTTER command values
 *
 * @author Venkaiah Chowdary Koneru
 * @see DeviceConfigCommand#PARTIAL_CUTTER
 */
public enum PartialCutterValues implements CommandValues<String> {
    /**
     * Disable cutter function.
     */
    OFF,

    /**
     * Set printer to cut label at the end of printing job.
     */
    BATCH,

    /**
     * Set number of printing labels per cut. 0<= pieces <=65535.
     */
    Pieces;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandValue() {
        if (this == Pieces) {
            return "";
        }

        return this.name();
    }
}
