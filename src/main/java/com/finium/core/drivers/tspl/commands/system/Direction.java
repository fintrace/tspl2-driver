/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * Direction.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.system;

import com.finium.core.drivers.tspl.commands.TSPLCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

import static com.finium.core.drivers.tspl.commands.system.SystemCommand.DIRECTION;
import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command defines the printout direction and mirror image. This will be stored in the printer
 * memory. <br>
 * <p>
 * <b>Syntax</b><br>
 * DIRECTION n[,m]<br>
 * <blockquote>n 0 or 1.<br>
 * m 0: Print normal image. 1: Print mirror image</blockquote>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@NoArgsConstructor
public class Direction implements TSPLCommand<byte[]> {
    private boolean printPositionAsFeed = true;
    private boolean printMirrorImage = false;

    /**
     * @param printPositionAsFeed
     * @param printMirrorImage
     */
    public Direction(boolean printPositionAsFeed, boolean printMirrorImage) {
        this.printPositionAsFeed = printPositionAsFeed;
        this.printMirrorImage = printMirrorImage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        return (DIRECTION.name() + (printPositionAsFeed ? "1" : "0") + ","
                + (printMirrorImage ? "1" : "0") + "\n").getBytes(US_ASCII);
    }
}
