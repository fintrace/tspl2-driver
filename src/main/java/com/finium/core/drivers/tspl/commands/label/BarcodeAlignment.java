/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * BarcodeAlignment.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.label;

/**
 * alignment of barcode<br>
 * <p>
 * 0 : default (Left)<br>
 * 1 : Left<br>
 * 2 : Center<br>
 * 3 : Right<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum BarcodeAlignment {
    /**
     * default (Left)
     */
    DEFAULT_LEFT(0),

    /**
     * Left
     */
    LEFT(1),

    /**
     * Center
     */
    CENTER(2),

    /**
     * Right
     */
    RIGHT(3);

    private int alignment;

    /**
     * @param alignment
     */
    private BarcodeAlignment(int alignment) {
        this.alignment = alignment;
    }

    /**
     * @return
     */
    public int getAlignment() {
        return alignment;
    }
}
