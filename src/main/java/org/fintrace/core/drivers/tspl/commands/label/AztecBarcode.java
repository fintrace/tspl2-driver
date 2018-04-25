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
import static org.fintrace.core.drivers.tspl.commands.label.LabelFormatCommand.AZTEC;

/**
 * This command defines a AZTEC 2D bar code.<br>
 * <b><i>Syntax</i></b><br>
 * AZTEC x,y,rotate,[size,]ecp,]flg,]menu,]multi,]rev,] "content"<br>
 * AZTEC x,y,rotate,size,ecp,flg,menu,multi,rev,bytes,content<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Builder
@Data
public class AztecBarcode implements TSPLCommand {

    /**
     * Horizontal start position (in dots)
     */
    private Integer xCoordinate;

    /**
     * Vertical start position (in dots)
     */
    private Integer yCoordinate;

    /**
     * Rotation<br>
     * 0 : No rotation<br>
     * 90 : Rotate 90 degrees<br>
     * 180 : Rotate 180 degrees<br>
     * 270 : Rotate 270 degrees<br>
     */
    private BarcodeRotation rotation;

    /**
     * Element module size (1 to 20), default is 6
     */
    private Integer moduleSize;

    /**
     * Error control (& symbol size/type) parameter<br>
     * 0 : default error correction level<br>
     * 1 to 99 : minimum error correction percentage<br>
     * 101 to 104 : 1 to 4-layer Compact symbol<br>
     * 201 to 232 : 1 to 32-layer Full-Range symbol<br>
     * 300 : a simple Aztec “Rune”<br>
     */
    private Integer errorControl;

    /**
     * false : input message is straight bytes
     * true : input uses "<Esc>n" for FLG(n), "<Esc><Esc>" for "<Esc>"
     */
    private Boolean escapeFlag;

    /**
     * Menu symbol. default is false
     */
    private Boolean menu;

    /**
     * Number of symbols (1 to 26), default is 6
     */
    private Integer multi;

    /**
     * Output to be reversed. default is false
     */
    private Boolean rev;

    /**
     * Length of content
     */
    private Integer bytes;

    /**
     * Content of AZTEC 2D bar code<br>
     * <b><i>Note:<br>
     * If parameter bytes is used, double quotes (") are unnecessary.</i></b>
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("AZTEC: x and y positions are required");
        }

        if (rotation == null) {
            throw new LabelParserException("AZTEC: rotation is required");
        }

        if (moduleSize != null && (moduleSize < 1 || moduleSize > 20)) {
            throw new LabelParserException("AZTEC: invalid module size value");
        }

        if (errorControl != null && (errorControl == 100
                || (errorControl > 104 && errorControl <= 200)
                || (errorControl > 232 && errorControl < 300)
                || (errorControl > 300))) {
            throw new LabelParserException("AZTEC: invalid error control parameter");
        }

        StringBuilder commandBuilder = new StringBuilder(AZTEC.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(rotation.getRotation()).append(COMMA);

        if (moduleSize == null) {
            commandBuilder.append("6").append(COMMA);
        } else {
            commandBuilder.append(moduleSize).append(COMMA);
        }

        if (errorControl == null) {
            commandBuilder.append("0").append(COMMA);
        } else {
            commandBuilder.append(errorControl).append(COMMA);
        }

        commandBuilder.append(escapeFlag).append(COMMA);

        if (menu == null) {
            commandBuilder.append("0").append(COMMA);
        } else {
            commandBuilder.append(menu ? "1" : "0").append(COMMA);
        }

        if (multi == null) {
            commandBuilder.append("6").append(COMMA);
        } else {
            commandBuilder.append(multi).append(COMMA);
        }

        if (rev == null) {
            commandBuilder.append("0").append(COMMA);
        } else {
            commandBuilder.append(rev ? "1" : "0").append(COMMA);
        }

        if (bytes != null) {
            commandBuilder.append(bytes).append(COMMA)
                    .append("\"");
        }

        commandBuilder.append(content);

        if (bytes != null) {
            commandBuilder.append("\"");
        }

        commandBuilder.append(LF);
        return commandBuilder.toString();
    }
}
