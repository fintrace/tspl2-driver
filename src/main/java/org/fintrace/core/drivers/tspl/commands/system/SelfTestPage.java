package org.fintrace.core.drivers.tspl.commands.system;

/**
 * @author Venkaiah Chowdary Koneru
 */
public enum SelfTestPage {
    /**
     * a pattern to check the status of print head heat line.
     */
    PATTERN,

    /**
     * a self-test page with Ethernet settings.
     */
    ETHERNET,

    /**
     * a self-test page with Wi-Fi settings.
     */
    WLAN,

    /**
     * a self-test page with RS-232 settings.
     */
    RS232,

    /**
     * a self-test page with printer settings.
     */
    SYSTEM,

    /**
     * a self-test page with emulated language settings.
     */
    Z
}
