/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * TSPLDeviceConfigurationCommands.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 09, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.device;

import com.finium.core.drivers.tspl.commands.TSPLCommand;
import com.finium.core.drivers.tspl.commands.values.*;

import java.io.UnsupportedEncodingException;

import static com.finium.core.drivers.tspl.DriverConstants.SET_PREFIX;
import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * TSPL2 Device Reconfiguration Commands.
 * <p>
 * <b>Syntax</b><br>
 * SET &lt;COMMAND&gt; &lt;VALUE&gt;
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum TSPLDeviceConfigurationCommands implements TSPLCommand<byte[]> {

    /**
     * Disable cutter function.
     */
    PARTIAL_CUTTER_OFF(DeviceConfigCommand.PARTIAL_CUTTER, PartialCutterValues.OFF),

    /**
     * Set printer to cut label at the end of printing job.
     */
    PARTIAL_CUTTER_BATCH(DeviceConfigCommand.PARTIAL_CUTTER, PartialCutterValues.BATCH),

    /**
     * Set number of printing labels per cut. 0<= pieces <=65535.
     * Shall append the this command with the number of pieces.
     */
    PARTIAL_CUTTER_PIECES(DeviceConfigCommand.PARTIAL_CUTTER, PartialCutterValues.Pieces),

    /**
     * Disable cutter function.
     */
    CUTTER_OFF(DeviceConfigCommand.CUTTER, PartialCutterValues.OFF),

    /**
     * Set printer to cut label at the end of printing job.
     */
    CUTTER_BATCH(DeviceConfigCommand.CUTTER, PartialCutterValues.BATCH),

    /**
     * Set number of printing labels per cut. 0<= pieces <=65535.
     * Shall append the this command with the number of pieces.
     */
    CUTTER_PIECES(DeviceConfigCommand.CUTTER, PartialCutterValues.Pieces),

    /**
     * Diasbles label backfeeding
     */
    BACK_OFF(DeviceConfigCommand.BACK, BackCommandValues.OFF),

    /**
     * enables label backfeeding
     */
    BACK_ON(DeviceConfigCommand.BACK, BackCommandValues.ON),

    /**
     * Enable the self-peeling function
     */
    PEEL_ON(DeviceConfigCommand.PEEL, PeelCommandValues.ON),

    /**
     * Disables the self-peeling function
     */
    PEEL_OFF(DeviceConfigCommand.PEEL, PeelCommandValues.OFF),

    /**
     * Turn on the "HEAD OPEN" sensor
     */
    HEAD_ON(DeviceConfigCommand.HEAD, HeadCommandValues.ON),

    /**
     * Turn off the "HEAD OPEN" sensor
     */
    HEAD_OFF(DeviceConfigCommand.HEAD, HeadCommandValues.OFF),

    /**
     * Enable ribbon encoder sensor.
     */
    ENCODER_ON(DeviceConfigCommand.ENCODER, EncoderCommandValues.ON),

    /**
     * Disable ribbon encoder sensor.
     */
    ENCODER_OFF(DeviceConfigCommand.ENCODER, EncoderCommandValues.OFF);

    private DeviceConfigCommand command;
    private CommandValues commandValue;

    /**
     * @param command
     * @param commandValue
     */
    private TSPLDeviceConfigurationCommands(DeviceConfigCommand command, CommandValues commandValue) {
        this.command = command;
        this.commandValue = commandValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        return (SET_PREFIX + " " + this.command.name() + " " + this.commandValue.getCommandValue())
                .getBytes(US_ASCII);
    }
}
