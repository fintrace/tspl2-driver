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
 * This command clears a specified region in the image buffer.<br>
 * <b>Syntax</b><br>
 * ERASE x,y,x_width,y_height<br>
 * <table valign="top" border="1">
 * <tr>
 * <th>Parameter</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>x</td>
 * <td>The x-coordinate of the starting point (in dots)</td>
 * </tr>
 * <tr>
 * <td>y</td>
 * <td>The y-coordinate of the starting point (in dots)</td>
 * </tr>
 * <tr>
 * <td>x_width</td>
 * <td>The region width in x-axis direction (in dots)</td>
 * </tr>
 * <tr>
 * <td>y_height</td>
 * <td>The region height in y-axis direction (in dots)</td>
 * </tr>
 * </table>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Erase implements TSPLCommand {

    /**
     * The x-coordinate of the starting point (in dots)
     */
    private Integer xCoordinate;

    /**
     * The y-coordinate of the starting point (in dots)
     */
    private Integer yCoordinate;

    /**
     * The region width in x-axis direction (in dots)
     */
    private Integer width;

    /**
     * The region height in y-axis direction (in dots)
     */
    private Integer height;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("ERASE: x and y positions are required");
        }

        if (width == null) {
            throw new LabelParserException("ERASE: please specify width");
        }

        if (height == null) {
            throw new LabelParserException("ERASE: please specify height");
        }

        StringBuilder commandBuilder = new StringBuilder(LabelFormatCommand.ERASE.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(width).append(COMMA)
                .append(height)
                .append(LF);

        return commandBuilder.toString();
    }
}
