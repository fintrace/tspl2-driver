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
import static org.fintrace.core.drivers.tspl.commands.label.LabelCommand.CIRCLE;

/**
 * This command draws a circle on the label.<br>
 * <p>
 * <b>Syntax</b><br>
 * CIRCLE X_start,Y_start,diameter,thickness<br>
 * X_start Specify x-coordinate of upper left corner (in dots)<br>
 * Y_start Specify y-coordinate of upper left corner (in dots)<br>
 * diameter Specify the diameter of the circle (in dots)<br>
 * thickness Thickness of the circle (in dots)<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Circle implements TSPLCommand<byte[]> {

    /**
     * x-coordinate of upper left corner (in dots)
     */
    private Integer xStart;

    /**
     * y-coordinate of upper left corner (in dots)
     */
    private Integer yStart;

    /**
     * the diameter of the circle (in dots)
     */
    private Integer diameter;

    /**
     * Thickness of the circle (in dots)
     */
    private Integer thickness;

    @Override
    public byte[] getCommand() {
        return (CIRCLE.name() + " "
                + xStart + ","
                + yStart + ","
                + diameter + ","
                + thickness + "\n").getBytes(US_ASCII);
    }
}
