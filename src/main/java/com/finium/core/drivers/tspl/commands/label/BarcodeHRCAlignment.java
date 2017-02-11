/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * BarcodeHRCAlignment.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.label;

/**
 * Human readable code alignment<br>
 * <p>
 * 0: not readable<br>
 * 1: human readable aligns to left<br>
 * 2: human readable aligns to center<br>
 * 3: human readable aligns to right<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum BarcodeHRCAlignment {
    /**
     * not readable
     */
    NO_HRC_DISPLAY(0),

    /**
     * human readable aligns to left
     */
    HRC_ALIGN_LEFT(1),

    /**
     * human readable aligns to center
     */
    HRC_ALIGN_CENTER(2),

    /**
     * human readable aligns to right
     */
    HRC_ALIGN_RIGHT(3);

    private int alignment;

    /**
     * @param alignment
     */
    private BarcodeHRCAlignment(int alignment) {
        this.alignment = alignment;
    }

    /**
     * @return
     */
    public int getAlignment() {
        return alignment;
    }
}
