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
 * This command draws TLC39, TCIF Linked Bar Code 3 of 9, barcode.<br>
 * <b>Syntax</b><br>
 * TLC39 x,y,rotation,[height,]narrow,]wide,]cellwidth,]cellheight,] "ECI number,
 * Serial number & additional data"
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class TLC39 implements TSPLCommand {

    /**
     * the x-coordinate
     */
    private Integer xCoordinate;

    /**
     * the y-coordinate
     */
    private Integer yCoordinate;

    /**
     * 0 : No rotation<br>
     * 90 : Rotate 90 degrees clockwise<br>
     * 180 : Rotate 180 degrees clockwise<br>
     * 270 : Rotate 270 degrees clockwise<br>
     */
    private BarcodeRotation rotation;

    /**
     * Height of Code39 in dots (Default is 40)
     */
    private Integer height;

    /**
     * Width of narrow element of Code39 in dots (Default is 2)
     */
    private Integer narrow;

    /**
     * Width of wide element of Code39 in dots (Default is 4)
     */
    private Integer wide;

    /**
     * Width of cell of MicroPDF417 in dots (Default is 2)
     */
    private Integer cellWidth;

    /**
     * Height of cell of MicroPDF417 in dots (Default is 4)
     */
    private Integer cellHeight;

    /**
     * Must be 6 digits which is used to generate Code39
     */
    private Integer eciNumber;

    /**
     * Serial number. Alphanumeric is for Micro-PDF417
     */
    private String serialNumber;

    /**
     * Alphanumeric is for Micro-PDF417
     */
    private String additionalData;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {

        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("TLC39: x and y positions are required");
        }

        if (rotation == null) {
            throw new LabelParserException("TLC39: please specify rotation");
        }

        if (narrow != null && height == null) {
            throw new LabelParserException("TLC39: height must be specified with narrow");
        }

        if (wide != null && (height == null || narrow == null)) {
            throw new LabelParserException("TLC39: height and narrow must be specified with wide");
        }

        if (cellWidth != null && (wide == null || height == null
                || narrow == null)) {
            throw new LabelParserException("TLC39: height, narrow and wide must be "
                    + "specified with cellWidth");
        }

        if (cellHeight != null && (cellWidth == null || wide == null
                || height == null || narrow == null)) {
            throw new LabelParserException("TLC39: height, narrow, wide and cellWidth "
                    + "must be specified with cellHeight");
        }

        if (eciNumber == null || eciNumber.toString().length() != 6) {
            throw new LabelParserException("TLC39: ECI number is required and must be 6 digits");
        }

        if (serialNumber == null) {
            throw new LabelParserException("TLC39: Serial number is required");
        }

        StringBuilder commandBuilder = new StringBuilder(LabelFormatCommand.TLC39.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(rotation.getRotation()).append(COMMA);

        if (height != null) {
            commandBuilder.append(height).append(COMMA);
        }

        if (narrow != null) {
            commandBuilder.append(narrow).append(COMMA);
        }

        if (wide != null) {
            commandBuilder.append(wide).append(COMMA);
        }

        if (cellWidth != null) {
            commandBuilder.append(cellWidth).append(COMMA);
        }

        if (cellHeight != null) {
            commandBuilder.append(cellHeight).append(COMMA);
        }

        commandBuilder.append(ESCAPED_DOUBLE_QUOTE)
                .append(eciNumber).append(COMMA)
                .append(serialNumber);

        if (additionalData != null) {
            commandBuilder.append(COMMA)
                    .append(additionalData);
        }

        commandBuilder.append(ESCAPED_DOUBLE_QUOTE)
                .append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
