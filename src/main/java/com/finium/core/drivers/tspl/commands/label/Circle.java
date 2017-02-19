/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * Circle.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.label;

import com.finium.core.drivers.tspl.commands.TSPLCommand;
import lombok.Builder;
import lombok.Data;

import java.io.UnsupportedEncodingException;

import static com.finium.core.drivers.tspl.commands.label.LabelCommand.CIRCLE;
import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command draws a circle on the label.<br>
 * <p>
 * <b>Syntax</b><br>
 * CIRCLE X_start,Y_start,diameter,thickness<br>
 * X_start Specify x-coordinate of upper left corner (in dots)<br>
 * Y_start Specify y-coordinate of upper left corner (in dots)<br>
 * diameter Specify the diameter of the circle (in dots)<br>
 * thickness Thickness of the circle (in dots)<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Circle implements TSPLCommand<byte[]> {

    /**
     * x-coordinate of upper left corner (in dots)
     */
    private Integer xStart;

    /**
     * y-coordinate of upper left corner (in dots)
     */
    private Integer yStart;

    /**
     * the diameter of the circle (in dots)
     */
    private Integer diameter;

    /**
     * Thickness of the circle (in dots)
     */
    private Integer thickness;

    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        return (CIRCLE.name() + " "
                + xStart + ","
                + yStart + ","
                + diameter + ","
                + thickness + "\n").getBytes(US_ASCII);
    }
}
