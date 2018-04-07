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
 * This command prints QR code.<br>
 * <b>Syntax</b><br>
 * QRCODE x,y,ECC Level,cell width,mode,rotation,[justification,]model,]mask,]area] "content"<br>
 * <p>
 * <i>Note:<br>
 * TDP-643 Plus, TTP-243, TTP-342, TTP-244ME, TTP-342M and TTP-248M series are not supported
 * this QRCODE command.
 * </i>
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Builder
@Data
public class QRCode implements TSPLCommand {
    /**
     * The upper left corner x-coordinate of the QR code
     */
    private Integer xCoordinate;

    /**
     * The upper left corner y-coordinate of the QR code
     */
    private Integer yCoordinate;

    /**
     * Error correction recovery level<br>
     * L : 7%<br>
     * M : 15%<br>
     * Q : 25%<br>
     * H : 30%<br>
     */
    private ErrorCorrectionLevel errorCorrectionLevel;

    /**
     * 1~10
     */
    private Integer cellWidth;

    /**
     * Auto / manual encode<br>
     * A : Auto<br>
     * M : Manual
     */
    private QREncodeMode mode;

    /**
     * 0 : 0 degree<br>
     * 90 : 90 degree<br>
     * 180 : 180 degree<br>
     * 270 : 270 degree<br>
     */
    private BarcodeRotation rotation;

    /**
     * Barcode justification (J1 to J9 valid; refer to "Sample code" example
     * below); since version A1.97 firmware.
     */
    private Integer justification;

    /**
     * M1: (default), original version<br>
     * M2: enhanced version (Almost smart phone is supported by this version.)
     */
    private QRModel model;

    /**
     * S0~S8, default is S7
     */
    private QRMask mask;

    /**
     * Maximum size of barcode area (Xdots; ex: X100);<br>
     * since version A1.97 firmware
     */
    private Integer area;

    /**
     * content.<br>
     * The encodable character set is described as below,<br>
     * Encodable character set:<br>
     * <ol>
     * <li>Numeric data: (digits 0~9)
     * </li>
     * <li>Alphanumeric data<br>
     * Digits 0-9<br>
     * Upper case letters A-Z<br>
     * Nine other characters: space, $ % * + - . / : )<br>
     * </li>
     * <li>
     * 8-bit byte data<br>
     * JIS 8-bit character set (Latin and Kana) in accordance with JIS X 0201
     * </li>
     * <li>
     * Kanji characters<br>
     * Shift JIS values 8140 HEX –9FFC HEX and E040 HEX –EAA4 HEX. These are<br>
     * values shifted from those of JIS X 0208. Refer to JIS X 0208 Annex 1<br>
     * Shift Coded Representation for detail
     * </li>
     * </ol>
     * Data characters per symbol (for maximum symbol size):<br>
     * <table valign="top" border="1">
     * <tr>
     * <th></th>
     * <th>Model 1 (Version 14-L)</th>
     * <th>Model 2 (Version 40-L)</th>
     * </tr>
     * <tr>
     * <td>Numeric data</td>
     * <td>1,167 characters</td>
     * <td>7,089 characters</td>
     * </tr>
     * <tr>
     * <td>Alphanumeric data</td>
     * <td>707 characters</td>
     * <td>4,296 characters</td>
     * </tr>
     * <tr>
     * <td>8-bit byte data</td>
     * <td>486 characters</td>
     * <td>2,953 characters</td>
     * </tr>
     * <tr>
     * <td>Kanji data</td>
     * <td>299 characters</td>
     * <td>1,817 characters</td>
     * </tr>
     * </table>
     * * If "A" is the first character in the data string, then the following data
     * after"A" is alphanumeric data.<br>
     * * If "N" is the first character in the data string, then the following data
     * after "N" is numeric data.<br>
     * * If "B" is the first character in the data string, then the following 4 digits
     * after "B" is used to specify numbers of data. After the 4 digits is the
     * number of bytes of binary data to be encoded.<br>
     * * If "K" is the first character in the data string, then the following data
     * after "K" is Kanji data.<br>
     * * If "!" is in the data string and follows by "N", "A", "B", "K" then it will be
     * switched to specified encodable character set.<br>
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {

        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("QRCODE: x and y positions are required");
        }

        if (errorCorrectionLevel == null) {
            throw new LabelParserException("QRCODE: please specify error correction level");
        }

        if (cellWidth == null) {
            throw new LabelParserException("QRCODE: please specify cell width in dots");
        }

        if (mode == null) {
            throw new LabelParserException("QRCODE: please specify encoding mode");
        }

        if (rotation == null) {
            throw new LabelParserException("QRCODE: please specify rotation");
        }

        StringBuilder commandBuilder = new StringBuilder(LabelFormatCommand.QRCODE.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(errorCorrectionLevel.name()).append(COMMA)
                .append(cellWidth).append(COMMA)
                .append(mode.name()).append(COMMA)
                .append(rotation.getRotation()).append(COMMA);

        if (justification != null) {
            commandBuilder.append("J").append(justification).append(COMMA);
        }

        if (model != null) {
            commandBuilder.append(model.name()).append(COMMA);
        }

        if (mask != null) {
            commandBuilder.append(mask.name()).append(COMMA);
        }

        if (area != null) {
            commandBuilder.append("X").append(area).append(COMMA);
        }

        commandBuilder.append(ESCAPED_DOUBLE_QUOTE)
                .append(content).append(ESCAPED_DOUBLE_QUOTE)
                .append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
