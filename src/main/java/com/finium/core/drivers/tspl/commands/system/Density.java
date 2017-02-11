/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * Density.java
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
 * This command sets the printing darkness.<br>
 * <p>
 * <b>Syntax</b><br>
 * DENSITY n<br>
 * <blockquote>n 0~15<br>
 * 0: specifies the lightest level<br>
 * 15: specifies the darkest leve</blockquote>
 * <p>
 * <b><i>NOTE: </i></b>Default printer DENSITY setting is 8.
 *
 * @author Venkaiah Chowdary Koneru
 */
@NoArgsConstructor
@Data
public class Density implements TSPLCommand<byte[]> {

    /**
     * printer darkness
     */
    private Integer darkness;

    /**
     * @param darkness printer darkness
     */
    public Density(Integer darkness) {
        if (darkness == null) {
            throw new LabelParserException("ParseException DENSITY Command: darkness can't be empty");
        }
        this.darkness = darkness;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (darkness == null) {
            throw new LabelParserException("ParseException DENSITY Command: darkness can't be empty");
        }

        return (SystemCommand.DENSITY.name() + " " + darkness).getBytes(US_ASCII);
    }
}
