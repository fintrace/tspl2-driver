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
package org.fintrace.core.drivers.tspl.commands.label;

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;

import static org.fintrace.core.drivers.tspl.DriverConstants.*;

/**
 * This command defines a 2D Maxicode.<br>
 * <b>Syntax</b>
 * <table valign="top">
 * <tr>
 * <td colspan="2">MAXICODE x,y,mode,[class,country,post,Lm,] "content"</td>
 * </tr>
 * <tr>
 * <td>MAXICODE x,y,mode,class,country,postal code, "content"</td>
 * <td>For mode 2 or 3,<br>
 * If country is 840, the postal code is in 99999,9999 format.<br>
 * For other countries, the code is up to 6 alphanumeric characters.
 * </td>
 * </tr>
 * <tr>
 * <td>MAXICODE x,y,mode,[Lm,] "content"</td>
 * <td>For mode 4,5,6,<br>
 * AIM special format is supported.<br>
 * <b><i>Mode 6 is not supported in TSPL2 printer firmware.</i></b>
 * </td>
 * </tr>
 * </table>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class MaxiCode implements TSPLCommand {

    /**
     * X-coordinate of the starting point (in dot)
     */
    private Integer xCoordinate;

    /**
     * Y-coordinate of the starting point (in dot)
     */
    private Integer yCoordinate;

    /**
     * 2,3,4,5
     */
    private Integer mode;

    /**
     * Class of service, 3-digit number (for mode 2,3)
     */
    private Integer serviceClass;

    /**
     * Country code, 3-digit number (for mode 2,3)
     */
    private Integer countryCode;

    /**
     * Post code (for mode 2,3)<br>
     * Mode 2(USA): 5-digit + 4-digit number<br>
     * Mode 3(Canada): 6 alphanumeric post code included by double quotes.
     */
    private String postCode;

    /**
     * Expression length (double quote is ignored) , 1 <= m <= 138,
     * (this parameter is just for mode 4 and 5)
     */
    private Integer expressionLength;

    /**
     * Content of 2D Maxicode.
     * <b><i>Note:<br>
     * If parameter Lm is used, double quotes (") are unnecessary.
     * </i></b>
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {

        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("MAXICODE: x and y positions are required");
        }

        if (mode == null) {
            throw new LabelParserException("MAXICODE: please specify a mode");
        }

        if ((mode == 2 || mode == 3)
                && (serviceClass == null || countryCode == null || postCode == null)) {
            throw new LabelParserException("MAXICODE: class, country and post must "
                    + "be specified for mode 2 and 3");
        }

        if (mode < 2 || mode > 5) {
            throw new LabelParserException("MAXICODE: mode must be 2,3,4 or 5");
        }

        if (mode == 2 && countryCode == 840 && postCode.length() != 10) {
            throw new LabelParserException("MAXICODE: post for USA is in 99999,9999 format");
        }

        if (mode == 3 && postCode.length() != 6) {
            throw new LabelParserException("MAXICODE: post for mode is 6 alphanumeric");
        }

        StringBuilder commandBuilder = new StringBuilder(LabelFormatCommand.MAXICODE.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(mode).append(COMMA);

        if (mode == 2 || mode == 3) {
            commandBuilder.append(serviceClass).append(COMMA)
                    .append(countryCode).append(COMMA);

            if (mode == 3) {
                commandBuilder.append(ESCAPED_DOUBLE_QUOTE);
            }
            commandBuilder.append(postCode);

            if (mode == 3) {
                commandBuilder.append(ESCAPED_DOUBLE_QUOTE);
            }
            commandBuilder.append(COMMA)
                    .append(ESCAPED_DOUBLE_QUOTE)
                    .append(content).append(ESCAPED_DOUBLE_QUOTE);
        } else {
            if (expressionLength == null) {
                commandBuilder.append(ESCAPED_DOUBLE_QUOTE);
            } else {
                commandBuilder.append("L").append(expressionLength)
                        .append(COMMA);
            }

            commandBuilder.append(content);

            if (expressionLength == null) {
                commandBuilder.append(ESCAPED_DOUBLE_QUOTE);
            }
        }

        commandBuilder.append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
