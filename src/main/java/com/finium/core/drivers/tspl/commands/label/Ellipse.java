/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * Ellipse.java
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

import static com.finium.core.drivers.tspl.commands.label.LabelCommand.ELLIPSE;
import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command draws an ellipse on the label.<br>
 * <p>
 * <b>Syntax</b><br>
 * ELLIPSE x,y,width,height,thickness<br>
 * <p>
 * x Specify x-coordinate of upper left corner (in dots)<br>
 * y Specify y-coordinate of upper left corner (in dots)<br>
 * width Specify the width of the ellipse (in dots)<br>
 * height Specify the height of the ellipse (in dots)<br>
 * thickness Thickness of the ellipse (in dots)<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Ellipse implements TSPLCommand<byte[]> {

    /**
     * x-coordinate of upper left corner (in dots)
     */
    private Integer xCoordinate;

    /**
     * y-coordinate of upper left corner (in dots)
     */
    private Integer yCoordinate;

    /**
     * width of the ellipse (in dots)
     */
    private Integer width;

    /**
     * height of the ellipse (in dots)
     */
    private Integer height;

    /**
     * Line thickness (in dots)
     */
    private Integer lineThickness;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        return (ELLIPSE.name() + " "
                + xCoordinate + ","
                + yCoordinate + ","
                + width + ","
                + height + ","
                + lineThickness + "\n").getBytes(US_ASCII);
    }
}
