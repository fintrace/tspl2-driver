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
import static org.fintrace.core.drivers.tspl.commands.label.TSPLLabelUtils.hasFloatDecimals;

/**
 * This command prints text on label.
 * <p>
 * <b>Syntax</b><br>
 * <code>TEXT x,y, "font",rotation,x-multiplication,y-multiplication,[alignment,] "content"</code>
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Text implements TSPLCommand {

    /**
     * The x-coordinate of the text
     */
    private Integer xCoordinate;

    /**
     * The y-coordinate of the text
     */
    private Integer yCoordinate;

    /**
     * Font name.
     * <table border="1">
     * <tr>
     * <td>0</td>
     * <td>Monotye CG Triumvirate Bold Condensed, font width and height is stretchable</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>8 x 12 fixed pitch dot font</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>12 x 20 fixed pitch dot font</td>
     * </tr>
     * <tr>
     * <td>3</td>
     * <td>16 x 24 fixed pitch dot font</td>
     * </tr>
     * <tr>
     * <td>4</td>
     * <td>24 x 32 fixed pitch dot font</td>
     * </tr>
     * <tr>
     * <td>5</td>
     * <td>32 x 48 dot fixed pitch font</td>
     * </tr>
     * <tr>
     * <td>6</td>
     * <td>14 x 19 dot fixed pitch font OCR-B</td>
     * </tr>
     * <tr>
     * <td>7</td>
     * <td>21 x 27 dot fixed pitch font OCR-B</td>
     * </tr>
     * <tr>
     * <td>8</td>
     * <td>14 x25 dot fixed pitch font OCR-A</td>
     * </tr>
     * <tr>
     * <td>ROMAN.TTF</td>
     * <td>Monotye CG Triumvirate Bold Condensed, font width and height proportion is fixed.</td>
     * </tr>
     * <tr>
     * <td colspan="2">
     * <b>Following fonts were supported since V6.80 EZ.</b>
     * </td>
     * </tr>
     * <tr>
     * <td>1.EFT</td>
     * <td>EPL2 font 1</td>
     * </tr>
     * <tr>
     * <td>2.EFT</td>
     * <td>EPL2 font 2</td>
     * </tr>
     * <tr>
     * <td>3.EFT</td>
     * <td>EPL2 font 3</td>
     * </tr>
     * <tr>
     * <td>4.EFT</td>
     * <td>EPL2 font 4</td>
     * </tr>
     * <tr>
     * <td>5.EFT</td>
     * <td>EPL2 font 5</td>
     * </tr>
     * <tr>
     * <td>A.EFT</td>
     * <td>ZPL2 font A</td>
     * </tr>
     * <tr>
     * <td>B.EFT</td>
     * <td>ZPL2 font B</td>
     * </tr>
     * <tr>
     * <td>D.EFT</td>
     * <td>ZPL2 font D</td>
     * </tr>
     * <tr>
     * <td>E8.EFT</td>
     * <td>ZPL2 font E8</td>
     * </tr>
     * <tr>
     * <td>F.EFT</td>
     * <td>ZPL2 font F</td>
     * </tr>
     * <tr>
     * <td>G.EFT</td>
     * <td>ZPL2 font G</td>
     * </tr>
     * <tr>
     * <td>H8.EFT</td>
     * <td>ZPL2 font H8</td>
     * </tr>
     * <tr>
     * <td>GS.EFT</td>
     * <td>ZPL2 font GS</td>
     * </tr>
     * </table>
     */
    private String fontName;

    /**
     * The rotation angle of text.<br>
     * 0 : No rotation<br>
     * 90: degrees, in clockwise direction<br>
     * 180 : degrees, in clockwise direction<br>
     * 270 : degrees, in clockwise direction<br>
     */
    private BarcodeRotation rotation;

    /**
     * Horizontal multiplication, up to 10x<br>
     * Available factors: 1~10<br>
     * For "ROMAN.TTF" true type font, this parameter is ignored.<br>
     * For font "0", this parameter is used to specify the width (point) of true type
     * font. 1 point=1/72 inch<br>
     */
    private Float xMultiplicationFactor;

    /**
     * Vertical multiplication, up to 10x<br>
     * Available factors: 1~10<br>
     * For true type font, this parameter is used to specify the height (point) of
     * true type font. 1 point=1/72 inch.<br>
     * For *.TTF font, x-multiplication and y-multiplication support floating value.
     * (V6.91 EZ)
     */
    private Float yMultiplicationFactor;

    /**
     * Optional. Specify the alignment of text. (V6.73 EZ)<br>
     * 0 : Default (Left)<br>
     * 1 : Left<br>
     * 2 : Center<br>
     * 3 : Right<br>
     */
    private BarcodeAlignment alignment;

    /**
     * Content of text string
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("TEXT: x and y positions are required");
        }

        //TODO: check for font

        if (xMultiplicationFactor != null && (xMultiplicationFactor <= 0
                || xMultiplicationFactor > 10)) {
            throw new LabelParserException("TEXT: Available xMultiplication factors: 1~10");
        }

        if (yMultiplicationFactor != null && (yMultiplicationFactor <= 0
                || yMultiplicationFactor > 10)) {
            throw new LabelParserException("TEXT: Available yMultiplication factors: 1~10");
        }

        if (content == null) {
            throw new LabelParserException("TEXT: content is required");
        }

        StringBuilder commandBuilder = new StringBuilder(LabelFormatCommand.TEXT.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(ESCAPED_DOUBLE_QUOTE).append(fontName).append(ESCAPED_DOUBLE_QUOTE)
                .append(COMMA);

        if (rotation != null) {
            commandBuilder.append(rotation.getRotation()).append(COMMA);
        }

        // For "ROMAN.TTF" true type font, xMultiplicationFactor parameter is ignored.
       
        if (!hasFloatDecimals(xMultiplicationFactor)) {
            commandBuilder.append(xMultiplicationFactor.intValue());
        } else {
            commandBuilder.append(xMultiplicationFactor);
        }

        commandBuilder.append(COMMA);



        if (!hasFloatDecimals(yMultiplicationFactor)) {
            commandBuilder.append(yMultiplicationFactor.intValue());
        } else {
            commandBuilder.append(yMultiplicationFactor);
        }

        commandBuilder.append(COMMA);

        if (alignment != null) {
            commandBuilder.append(alignment.getAlignment()).append(COMMA);
        }

        commandBuilder.append(ESCAPED_DOUBLE_QUOTE).append(content).append(ESCAPED_DOUBLE_QUOTE)
                .append(LF);

        return commandBuilder.toString();
    }
}
