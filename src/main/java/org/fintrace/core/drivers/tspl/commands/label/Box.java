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

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command draws rectangles on the label.<br>
 * <p>
 * <b>Syntax</b><br>
 * BOX x,y,x_end,y_end,line thickness[,radius]<br>
 * <p>
 * x Specify x-coordinate of upper left corner (in dots)<br>
 * y Specify y-coordinate of upper left corner (in dots)<br>
 * x_end Specify x-coordinate of lower right corner (in dots)<br>
 * y_end Specify y-coordinate of lower right corner (in dots)<br>
 * line thickness Line thickness (in dots)<br>
 * radius Optional. Specify the round corner. Default is 0.<br>
 * <p>
 * <b>Note:</b><br>
 * - 200 DPI : 1 mm = 8 dots<br>
 * 300 DPI : 1 mm = 12 dots<br>
 * - Recommended max. thickness of box is 12 mm at 4” width. Thickness of box larger than 12
 * mm may damage the power supply and affect the print quality. Max. print ratio is different
 * for each printer model. Desktop and industrial printer print ratio is limited to 20% and 30%
 * respectively
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Box implements TSPLCommand<byte[]> {
    /**
     * x-coordinate of upper left corner (in dots)
     */
    private Integer xCoordinate;

    /**
     * y-coordinate of upper left corner (in dots)
     */
    private Integer yCoordinate;

    /**
     * x-coordinate of lower right corner (in dots)
     */
    private Integer xEndCoordinate;

    /**
     * y-coordinate of lower right corner (in dots)
     */
    private Integer yEndCoordinate;

    /**
     * Line thickness (in dots)
     */
    private Integer lineThickness;

    /**
     * Optional. radius of the round corner. Default is 0.
     */
    @Builder.Default
    private Integer radius = 0;

    @Override
    public byte[] getCommand() {
        return (LabelCommand.BOX.name() + " " + xCoordinate + ","
                + yCoordinate + ","
                + xEndCoordinate + ","
                + yEndCoordinate + ","
                + lineThickness + ","
                + radius + "\n").getBytes(US_ASCII);
    }
}
