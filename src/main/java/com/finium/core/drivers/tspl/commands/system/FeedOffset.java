/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * FeedOffset.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.system;

import com.finium.core.drivers.tspl.commands.TSPLCommand;
import com.finium.core.drivers.tspl.exceptions.LabelParserException;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command defines the selective, extra label feeding length each form feed takes, which, especially
 * in peel-off mode and cutter mode, is used to adjust label stop position, so as for label to register at
 * proper places for the intended purposes. The printer back tracks the extra feeding length before the
 * next run of printing.
 *
 * @author Venkaiah Chowdary Koneru
 */
@NoArgsConstructor
public class FeedOffset implements TSPLCommand<byte[]> {
    /**
     * The offset distance (mm).<br>
     * <b>CAUTION: </b>Impropriety offset value may cause paper jam.
     */
    private Integer offsetDistance;

    /**
     * @param offsetDistance The offset distance (mm).
     *                       Impropriety offset value may cause paper jam.
     * @throws LabelParserException if offsetDistance is null
     */
    public FeedOffset(Integer offsetDistance) {
        if (offsetDistance == null) {
            throw new LabelParserException("ParseException OFFSET Command: offset can't be empty");
        }

        this.offsetDistance = offsetDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (offsetDistance == null) {
            throw new LabelParserException("ParseException OFFSET Command: offset can't be empty");
        }

        return (SystemCommand.OFFSET.name() + " " + offsetDistance + " mm").getBytes(US_ASCII);
    }
}
