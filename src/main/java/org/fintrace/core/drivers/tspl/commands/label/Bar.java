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

import lombok.Data;
import lombok.NoArgsConstructor;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command draws a bar on the label format.<br>
 * <p>
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
@NoArgsConstructor
public class Bar implements TSPLCommand<byte[]> {

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
     * @param xCoordinate The upper left corner x-coordinate (in dots)
     * @param yCoordinate The upper left corner y-coordinate (in dots)
     * @param width       width Bar width (in dots)
     * @param height      height Bar height (in dots)
     */
    public Bar(Integer xCoordinate, Integer yCoordinate, Integer width, Integer height) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() {
        return (LabelCommand.BAR.name() + " " + xCoordinate + ","
                + yCoordinate + ","
                + width + "," + height + "\n").getBytes(US_ASCII);
    }
}
