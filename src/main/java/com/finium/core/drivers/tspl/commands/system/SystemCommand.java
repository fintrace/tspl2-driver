/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * LabelCommand.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.system;

/**
 * Setup and System Commands as part of label formatting
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum SystemCommand {
    /**
     * This command defines the label width and length.
     */
    SIZE,

    /**
     * Defines the gap distance between two labels.
     */
    GAP,

    /**
     * extra label feeding length
     */
    OFFSET,

    /**
     * Printing speed in inch per second
     */
    SPEED,

    /**
     * printing darkness
     */
    DENSITY,

    /**
     * printout direction and mirror image.
     */
    DIRECTION,

    /**
     * clears buffer
     */
    CLS,

    /**
     * prints the label
     */
    PRINT
}
