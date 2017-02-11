/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * BarcodeRotation.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.label;

/**
 * 0 : No rotation<br>
 * 90 : Rotate 90 degrees clockwise<br>
 * 180 : Rotate 180 degrees clockwise<br>
 * 270 : Rotate 270 degrees clockwise<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum BarcodeRotation {
    /**
     * No rotation
     */
    NO_ROTATION(0),

    /**
     * Rotate 90 degrees clockwise
     */
    DEGREES_90(90),

    /**
     * Rotate 180 degrees clockwise
     */
    DEGREES_180(180),

    /**
     * Rotate 270 degrees clockwise
     */
    DEGREES_270(270);

    /**
     * rotation value
     */
    private int rotation;

    /**
     * @param rotation
     */
    private BarcodeRotation(int rotation) {
        this.rotation = rotation;
    }

    /**
     * @return
     */
    public int getRotation() {
        return rotation;
    }
}
