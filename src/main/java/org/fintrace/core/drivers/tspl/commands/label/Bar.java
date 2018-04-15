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
 * This command draws a bar on the label format.<br>
 *
 * <b>Syntax</b><br>
 * BAR x,y,width,height<br>
 * <p>
 * x The upper left corner x-coordinate (in dots)<br>
 * y The upper left corner y-coordinate (in dots)<br>
 * width Bar width (in dots)<br>
 * height Bar height (in dots)<br><br>
 * <b>Note:</b><br>
 * <ul>
 * <li>200 DPI : 1 mm = 8 dots<br>300 DPI : 1 mm = 12 dots</li>
 * <li>Recommended max. bar height is 12 mm at 4” width. Bar height over 12 mm may damage
 * the power supply and affect the print quality.</li>
 * <li>Max. print ratio is different for each printer model. Desktop and industrial printer print
 * ratio is limited to 20% and 30% respectively.</li>
 * </ul>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Bar implements TSPLCommand {

    /**
     * The upper left corner x-coordinate (in dots)
     */
    private Integer xCoordinate;

    /**
     * The upper left corner y-coordinate (in dots)
     */
    private Integer yCoordinate;

    /**
     * width Bar width (in dots)
     */
    private Integer width;

    /**
     * height Bar height (in dots)
     */
    private Integer height;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("BAR: x and y positions are required");
        }

        if (width == null) {
            throw new LabelParserException("BAR: width is required");
        }

        if (height == null) {
            throw new LabelParserException("BAR: height is required");
        }

        return LabelFormatCommand.BAR.name() + EMPTY_SPACE +
                xCoordinate + COMMA +
                yCoordinate + COMMA +
                width + COMMA +
                height +
                LF;
    }
}
