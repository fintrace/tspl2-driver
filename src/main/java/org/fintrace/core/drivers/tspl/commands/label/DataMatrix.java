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
import static org.fintrace.core.drivers.tspl.commands.label.LabelFormatCommand.DMATRIX;

/**
 * This command defines a DataMatrix 2D bar code. Currently, only
 * <b>ECC200</b> error correction is supported.<br>
 * <p>
 * <b>Syntax</b><br>
 * DMATRIX x,y,width,height,[c#,x#,r#,a#,row,col,] "content"<br>
 * <p>
 * x Horizontal start position (in dots)<br>
 * y Vertical start position (in dots)<br>
 * width The expected width of barcode area (in dots)<br>
 * height The expected height of barcode area (in dots)<br>
 * c# Escape sequence control character (decimal digit)<br>
 * X# Module size (in dots)<br>
 * r# Rotation<br>
 * a# shape (rectangle or square)<br>
 * row Symbol size of row: 10 to 144<br>
 * col Symbol size of col: 10 to 144<br>
 * content Content of DataMatrix 2D bar code<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class DataMatrix implements TSPLCommand {
    /**
     * Horizontal start position (in dots)
     */
    private Integer xCoordinate;

    /**
     * Vertical start position (in dots)
     */
    private Integer yCoordinate;

    /**
     * expected width of barcode area (in dots)
     */
    private Integer width;

    /**
     * expected height of barcode area (in dots)
     */
    private Integer height;

    /**
     * (Optional)<br>
     * Escape sequence control character (decimal digit)<br>
     * <p>
     * Ex. C126 means ~<br>
     * (1) ~X is shift character for control characters.<br>
     * <blockquote>
     * <b>~X Hex ASCII</b><br>
     * ~@ 00 NUL<br>
     * ~A 01 SOH<br>
     * ~B 02 STX<br>
     * ~C 03 ETX<br>
     * ~D 04 EOT<br>
     * ~E 05 ENQ<br>
     * ~F 06 ACK<br>
     * ~G 07 BEL<br>
     * ~H 08 BS<br>
     * ~I 09 HT<br>
     * ~J 0A LF<br>
     * ~K 0B VT<br>
     * ~L 0C FF<br>
     * ~M 0D CR<br>
     * ~N 0E SO<br>
     * ~O 0F SI<br>
     * ~P 10 DLE<br>
     * ~Q 11 DC1<br>
     * ~R 12 DC2<br>
     * ~S 13 DC3<br>
     * ~T 14 DC4<br>
     * ~U 15 NAK<br>
     * ~V 16 SYN<br>
     * ~W 17 ETB<br>
     * ~X 18 CAN<br>
     * ~Y 19 EM<br>
     * ~Z 1A SUB<br>
     * ~[ 1B ESC<br>
     * ~\ 1C FS<br>
     * ~] 1D GS<br>
     * ~^ 1E RS<br>
     * ~_ 1F US<br>
     * <p>
     * </blockquote>
     * (2) ~1 means FNC1.<br>
     * (3) ~dNNN creates ASCII decimal value NNN for a codeword. Must be 3
     * digits. 000 ~ 255.<br>
     * (4) ~ in data is encoded by ~~.<br>
     */
    private Integer escapeSequenceCharacter;

    /**
     * (Optional)<br>
     * Module size (in dots)
     */
    private Integer moduleSize;

    /**
     * Rotation<br>
     * <p>
     * 0 : No rotation<br>
     * 90 : Rotate 90 degrees clockwise<br>
     * 180 : Rotate 180 degrees clockwise<br>
     * 270 : Rotate 270 degrees clockwise<br>
     */
    private BarcodeRotation rotation;

    /**
     * Shape<br>
     * 0 : Square (default)
     * 1 : Rectangle
     */
    private Boolean isRectangle;

    /**
     * size of row: 10 to 144
     */
    private Integer nbRows;

    /**
     * size of col: 10 to 144
     */
    private Integer nbCols;

    /**
     * Datamatrix content
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("DMATRIX: x and y co-ordinates are required");
        }

        if (width == null) {
            throw new LabelParserException("DMATRIX: please specify the width");
        }

        if (height == null) {
            throw new LabelParserException("DMATRIX: please specify height");
        }

        if (content == null) {
            throw new LabelParserException("DMATRIX: content is required");
        }

        StringBuilder dataMatrix = new StringBuilder(DMATRIX.name());
        dataMatrix.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(width).append(COMMA)
                .append(height).append(COMMA);
        if (escapeSequenceCharacter != null) {
            dataMatrix.append("c").append(escapeSequenceCharacter).append(COMMA);
        }

        if (moduleSize != null) {
            dataMatrix.append("x").append(moduleSize).append(COMMA);
        }

        if (rotation != null) {
            dataMatrix.append("r").append(rotation.getRotation()).append(COMMA);
        }

        if (isRectangle != null) {
            dataMatrix.append("a").append(isRectangle ? 1 : 0).append(COMMA);
        }

        if (nbRows != null) {
            dataMatrix.append(nbRows).append(COMMA);
        }

        if (nbCols != null) {
            dataMatrix.append(nbCols).append(COMMA);
        }

        dataMatrix.append(EMPTY_SPACE)
                .append(ESCAPED_DOUBLE_QUOTE).append(content).append(ESCAPED_DOUBLE_QUOTE)
                .append(NEW_LINE_FEED);

        return dataMatrix.toString();
    }
}
