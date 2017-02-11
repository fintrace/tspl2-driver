/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * Size.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.system;

import com.finium.core.drivers.tspl.commands.TSPLCommand;
import com.finium.core.drivers.tspl.exceptions.LabelParserException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.US_ASCII;


/**
 * This command defines the label width and length<br>
 * <p>
 * <b>Syntax</b><br>
 * English system (inch): SIZE m,n <br>
 * Metric system (mm): SIZE m mm,n mm <br>
 * Dot measurement: SIZE m dot,n dot <br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@NoArgsConstructor
@Data
public class Size implements TSPLCommand<byte[]> {
    /**
     * Label width (mm)
     */
    private Integer labelWidth;

    /**
     * Label length (mm)
     */
    private Integer labelLength;

    /**
     * @param labelWidth
     * @param labelLength
     */
    public Size(Integer labelWidth, Integer labelLength) {
        if (labelWidth == null || labelLength == null) {
            throw new LabelParserException("ParseException SIZE Command: label width and label length should be specified");
        }

        this.labelWidth = labelWidth;
        this.labelLength = labelLength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (labelWidth == null || labelLength == null) {
            throw new LabelParserException("ParseException SIZE Command: label width and label length should be specified");
        }

        return (SystemCommand.SIZE.name() + " "
                + labelWidth + " mm," + labelLength
                + " mm").getBytes(US_ASCII);
    }
}
