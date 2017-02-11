/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * Speed.java
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
 * This command defines the print speed.<br>
 * <p>
 * <b>Syntax</b><br>
 * SPEED n<br>
 * <blockquote><i>n Printing speed in inch per second</i></blockquote>
 *
 * @author Venkaiah Chowdary Koneru
 */
@NoArgsConstructor
@Data
public class Speed implements TSPLCommand<byte[]> {

    /**
     * Printing speed in inch per second
     */
    private Double printSpeed;

    /**
     * @param printSpeed Printing speed in inch per second
     */
    public Speed(Double printSpeed) {
        if (printSpeed == null) {
            throw new LabelParserException("ParseException SPEED Command: speed can't be empty");
        }
        this.printSpeed = printSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (printSpeed == null) {
            throw new LabelParserException("ParseException SPEED Command: speed can't be empty");
        }

        return (SystemCommand.SPEED.name() + " " + printSpeed + "\n").getBytes(US_ASCII);
    }
}
