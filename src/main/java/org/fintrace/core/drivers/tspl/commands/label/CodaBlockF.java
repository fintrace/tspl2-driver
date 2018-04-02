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

import static org.fintrace.core.drivers.tspl.DriverConstants.*;

/**
 * This command draws CODABLOCK F mode barcode.
 * <br><br>
 * <b>Syntax</b><br>
 * CODABLOCK x,y,rotation,[row height,]module width,] "content"
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class CodaBlockF implements TSPLCommand {
    /**
     * x-coordinate of codabar on the label
     */
    private Integer xCoordinate;

    /**
     * y-coordinate of codabar on the label
     */
    private Integer yCoordinate;

    /**
     * rotation
     */
    @Builder.Default
    private BarcodeRotation rotation = BarcodeRotation.NO_ROTATION;

    /**
     * Row height (in dots).<br>
     * The height of individual row equals to row height x module width (Default is 8)
     */
    private Integer rowHeight;

    /**
     * Width of narrow element of CODABLOCK in dots (Default is 2)
     */
    private Integer moduleWidth;

    /**
     * content of CODABLOCK bar code
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        StringBuilder command = new StringBuilder(LabelFormatCommand.CODABLOCK.name());
        command.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(rotation.getRotation()).append(COMMA);

        if (rowHeight != null) {
            command.append(rowHeight).append(COMMA);
        }

        if (moduleWidth != null) {
            command.append(moduleWidth).append(COMMA);
        }

        command.append(EMPTY_SPACE)
                .append(ESCAPED_DOUBLE_QUOTE).append(content).append(ESCAPED_DOUBLE_QUOTE)
                .append(NEW_LINE_FEED);

        return command.toString();
    }
}
