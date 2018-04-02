/*
 * Copyright 2017 fintrace (https://fintrace.org/)
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
package org.fintrace.core.drivers.tspl.commands.system;

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;

import static org.fintrace.core.drivers.tspl.DriverConstants.*;
import static org.fintrace.core.drivers.tspl.commands.system.SystemCommand.PRINT;

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
@Builder
public class Print implements TSPLCommand {
    /**
     * Specifies how many sets of labels will be printed.
     */
    private Integer nbLabels;

    /**
     * Specifies how many copies should be printed for each particular label set
     */
    private Integer nbCopies;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (nbLabels == null) {
            throw new LabelParserException("ParseException PRINT Command: "
                    + "number of sets of labels is required");
        }

        StringBuilder commandBuilder = new StringBuilder(PRINT.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(nbLabels);

        if (nbCopies != null) {
            commandBuilder.append(COMMA)
                    .append(nbCopies);
        }

        commandBuilder.append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
