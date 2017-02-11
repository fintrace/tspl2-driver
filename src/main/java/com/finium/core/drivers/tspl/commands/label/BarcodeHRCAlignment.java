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
 * @author Venkaiah Chowdary Koneru
 */
public enum BarcodeHRCAlignment {
    NO_HRC_DISPLAY(0),
    HRC_ALIGN_LEFT(1),
    HRC_ALIGN_CENTRE(2),
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
