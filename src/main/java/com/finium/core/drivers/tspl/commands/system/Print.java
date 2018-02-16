/*
 * Copyright 2017 Finium Solutions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
            throw new LabelParserException("ParseException PRINT Command: "
                    + "number of sets of labels is required");
        }

        this.nbLabels = nbLabels;
        this.nbCopies = nbCopies;
    }

    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (nbLabels == null) {
            throw new LabelParserException("ParseException PRINT Command: "
                    + "number of sets of labels is required");
        }

        return (PRINT.name() + " " + nbLabels
                + (nbCopies != null ? ("," + nbCopies) : "") + "\n")
                .getBytes(US_ASCII);
    }
}
