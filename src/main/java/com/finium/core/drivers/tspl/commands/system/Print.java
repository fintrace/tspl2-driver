/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * Print.java
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

import static com.finium.core.drivers.tspl.commands.system.SystemCommand.PRINT;
import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command prints the label format currently stored in the image buffer.<br>
 * <p>
 * <b>Syntax</b><br>
 * PRINT m[,n]<br>
 * <blockquote>m Specifies how many sets of labels will be printed.<br>
 * 1 ≤ m ≤ 999999999<br>
 * n Specifies how many copies should be printed for each particular label set.<br>
 * 1 ≤ n ≤ 999999999</blockquote>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@NoArgsConstructor
public class Print implements TSPLCommand<byte[]> {
    /**
     * Specifies how many sets of labels will be printed.
     */
    private Integer nbLabels;

    /**
     * Specifies how many copies should be printed for each particular label set
     */
    private Integer nbCopies;

    /**
     * @param nbLabels
     */
    public Print(Integer nbLabels) {
        this(nbLabels, 1);
    }

    /**
     * @param nbLabels
     * @param nbCopies
     */
    public Print(Integer nbLabels, Integer nbCopies) {
        if (nbLabels == null) {
            throw new LabelParserException("ParseException PRINT Command: number of sets of labels is required");
        }

        this.nbLabels = nbLabels;
        this.nbCopies = nbCopies;
    }

    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (nbLabels == null) {
            throw new LabelParserException("ParseException PRINT Command: number of sets of labels is required");
        }

        return (PRINT.name() + " " + nbLabels +
                (nbCopies != null ? ("," + nbCopies) : ""))
                .getBytes(US_ASCII);
    }
}
