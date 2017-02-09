/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * DriverConstants.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl;

/**
 * @author Venkaiah Chowdary Koneru
 */
public final class DriverConstants {

    /**
     * SET prefix for the device configuration commands
     */
    public static final String SET_PREFIX = "SET";

    public static final String STATUS_COMMAND_PREFIX = "!";

    /**
     * private to prevent un-necessary instantiation.
     */
    private DriverConstants() {
    }
}
