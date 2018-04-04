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
 * This command prints 1D barcodes.
 * <p>
 * <b>Syntax</b><br>
 * BARCODE X,Y, "code type",height,human readable,rotation,narrow,wide,[alignment,] "content"
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Barcode implements TSPLCommand {
    /**
     * x-coordinate of bar code on the label
     */
    private Float xCoordinate;

    /**
     * y-coordinate of bar code on the label
     */
    private Float yCoordinate;

    /**
     * Bar code height (in dots)
     */
    private Integer height;

    /**
     * code type. The available code types are listed below:<br>
     * <table valign="top" border="1">
     * <tr>
     * <th>Code Type Description </th>
     * <th>Max. data length</th>
     * </tr>
     * <tr>
     * <td>128</td>
     * <td>Code 128, switching code subset A, B, C automatically.
     * <table valign="top" border="1">
     * <tr>
     * <th>Control code</th>
     * <th>A</th>
     * <th>B</th>
     * <th>C</th>
     * </tr>
     * <tr>
     * <td>096</td>
     * <td>FNC3</td>
     * <td>FNC3></td>
     * <td>NONE</td>
     * </tr>
     * <tr>
     * <td>097</td>
     * <td>FNC2</td>
     * <td>FNC2</td>
     * <td>NONE</td>
     * </tr>
     * <tr>
     * <td>098</td>
     * <td>SHIFT</td>
     * <td>SHIFT</td>
     * <td>NONE</td>
     * </tr>
     * <tr>
     * <td>099</td>
     * <td>CODE C</td>
     * <td>CODE C</td>
     * <td>NONE</td>
     * </tr>
     * <tr>
     * <td>100</td>
     * <td>CODE B</td>
     * <td>FNC 4</td>
     * <td>CODE B</td>
     * </tr>
     * <tr>
     * <td>101</td>
     * <td>FNC 4</td>
     * <td>CODE A</td>
     * <td>CODE A</td>
     * </tr>
     * <tr>
     * <td>102</td>
     * <td>FNC 1</td>
     * <td>FNC 1</td>
     * <td>FNC 1</td>
     * </tr>
     * <tr>
     * <td>103</td>
     * <td colspan="3">Start (CODE A)</td>
     * </tr>
     * <tr>
     * <td>104</td>
     * <td colspan="3">Start (CODE B)</td>
     * </tr>
     * <tr>
     * <td>105</td>
     * <td colspan="3">Start (CODE C)</td>
     * </tr>
     * </table>
     * <br><b><i>Use "!" as a starting character for the control code followed by three control codes.<br>
     * If the start subset is not set, the default starting subset is B.</i></b>
     * </td>
     * </tr>
     * <tr>
     * <td>128M</td>
     * <td>Code 128, switching code subset manually</td>
     * </tr>
     * <tr>
     * <td>EAN128</td>
     * <td>EAN128, switching code subset automatically</td>
     * </tr>
     * <tr>
     * <td>EAN128M</td>
     * <td>EAN128M, switching code subset manually</td>
     * </tr>
     * <tr>
     * <td>25</td>
     * <td>Interleaved 2 of 5</td>
     * </tr>
     * <tr>
     * <td>25C</td>
     * <td>Interleaved 2 of 5 with check digit</td>
     * </tr>
     * <tr>
     * <td>25C</td>
     * <td>Interleaved 2 of 5 with check digit</td>
     * </tr>
     * <tr>
     * <td>39</td>
     * <td>Code 39 full ASCII for TSPL2 printers<br>
     * Code 39 standard for TSPL printers<br>
     * Auto switch full ASCII and standard code 39 for PLUS models</td>
     * </tr>
     * <tr>
     * <td>39C</td>
     * <td>Code 39 full ASCII with check digit for TSPL2 printers<br>
     * Code 39 standard with check digit for TSPL printers<br>
     * Auto switch full ASCII and standard code 39 for PLUS models</td>
     * </tr>
     * <tr>
     * <td>39S</td>
     * <td>Code 39 standard for TSPL2 printers</td>
     * </tr>
     * <tr>
     * <td>93</td>
     * <td>Code 93</td>
     * </tr>
     * <tr>
     * <td>EAN13</td>
     * <td>EAN 13</td>
     * </tr>
     * <tr>
     * <td>EAN13+2</td>
     * <td>EAN 13 with 2 digits add-on</td>
     * </tr>
     * <tr>
     * <td>EAN13+5</td>
     * <td>EAN 13 with 5 digits add-on</td>
     * </tr>
     * <tr>
     * <td>EAN8</td>
     * <td>EAN 8</td>
     * </tr>
     * <tr>
     * <td>EAN8+2</td>
     * <td>EAN 8 with 2 digits add-on</td>
     * </tr>
     * <tr>
     * <td>EAN8+5</td>
     * <td>EAN 8 with 5 digits add-on</td>
     * </tr>
     * <tr>
     * <td>CODA</td>
     * <td>Codabar</td>
     * </tr>
     * <tr>
     * <td>POST</td>
     * <td>Postnet</td>
     * </tr>
     * <tr>
     * <td>UPCA</td>
     * <td>UPC-A</td>
     * </tr>
     * <tr>
     * <td>UPCA+2</td>
     * <td>UPC-A with 2 digits add-on</td>
     * </tr>
     * <tr>
     * <td>UPCA+5</td>
     * <td>UPC-A with 5 digits add-on</td>
     * </tr>
     * <tr>
     * <td>UPCE</td>
     * <td>UPC-E</td>
     * </tr>
     * <tr>
     * <td>UPCE+2</td>
     * <td>UPC-E with 2 digits add-on</td>
     * </tr>
     * <tr>
     * <td>UPCE+5</td>
     * <td>UPC-E with 5 digits add-on</td>
     * </tr>
     * <tr>
     * <td>CPOST</td>
     * <td>China post code</td>
     * </tr>
     * <tr>
     * <td>MSI</td>
     * <td>MSI code</td>
     * </tr>
     * <tr>
     * <td>MSIC</td>
     * <td>MSI with check digit</td>
     * </tr>
     * <tr>
     * <td>PLESSEY</td>
     * <td>PLESSEY code</td>
     * </tr>
     * <tr>
     * <td>ITF14</td>
     * <td>ITF 14 code</td>
     * </tr>
     * <tr>
     * <td>EAN14</td>
     * <td>EAN14 code</td>
     * </tr>
     * <tr>
     * <td>11</td>
     * <td>Code 11</td>
     * </tr>
     * <tr>
     * <td>TELEPEN</td>
     * <td>Telepen code</td>
     * </tr>
     * <tr>
     * <td>TELEPENN</td>
     * <td>Telepen code. Number only</td>
     * </tr>
     * <tr>
     * <td>PLANET</td>
     * <td>Planet code</td>
     * </tr>
     * <tr>
     * <td>CODE49</td>
     * <td>Code 49</td>
     * </tr>
     * <tr>
     * <td>DPI</td>
     * <td>Deutsche Post Identcode</td>
     * </tr>
     * <tr>
     * <td>DPL</td>
     * <td>Deutsche Post Leitcode</td>
     * </tr>
     * </table>
     * Note:<br>
     * TDP-643 Plus , TTP-243, TTP-342, TTP-244ME and TTP-342M models are not supported MSI, MSIC, PLESSY, ITF14,
     * EAN14 and 11.<br>
     * TTP-248M model are not supported MSIC and 11<br>
     */
    private BarcodeType codeType;

    /**
     * human readable.<br>
     * 0: not readable<br>
     * 1: human readable aligns to left<br>
     * 2: human readable aligns to center<br>
     * 3: human readable aligns to right
     */
    private BarcodeHRCAlignment hrcAlignment;

    /**
     * rotation.<br>
     * 0 : No rotation<br>
     * 90 : Rotate 90 degrees clockwise<br>
     * 180 : Rotate 180 degrees clockwise<br>
     * 270 : Rotate 270 degrees clockwise<br>
     */
    private BarcodeRotation rotation;

    /**
     * Width of narrow element (in dots)
     */
    private Integer narrow;

    /**
     * Width of wide element (in dots)
     */
    private Integer wide;

    /**
     * alignment of barcode. Default is (0) : left.<br>
     * 0 : default (Left)<br>
     * 1 : Left<br>
     * 2 : Center<br>
     * 3 : Right<br>
     */
    private BarcodeAlignment alignment;

    /**
     * content of the barcode.<br>
     * <b><i>Please note that the maximum number of digits of bar code content.</i></b>
     * <table valign="top" border="1">
     * <tr>
     * <th>Code Type</th>
     * <th>Character sets</th>
     * <th>Max. data length</th>
     * </tr>
     * <tr>
     * <td>128</td>
     * <td>See Character set for CODE128.</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>128M</td>
     * <td>See Character set for CODE128.</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>EAN128</td>
     * <td>See Character set for CODE128.</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>EAN128M</td>
     * <td>See Character set for CODE128.</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>25</td>
     * <td>0123456789</td>
     * <td>Length is even.</td>
     * </tr>
     * <tr>
     * <td>25C</td>
     * <td>0123456789</td>
     * <td>Length is odd.</td>
     * </tr>
     * <tr>
     * <td>39 I</td>
     * <td>0123456789[Space]ABCDEFGHIJKLMNOPQRSTUVWXYZ-.$/+%</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>39 I Full ASCII</td>
     * <td>0123456789[Space]ABCDEFGHIJKLMNOPQRSTUVWXYZ<br>
     * !#$%&’()*+,-./:;?@[\]^_`abcdefghijklmnopqrstuvwxy<br>
     * z{|}~</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>93</td>
     * <td>0123456789[Space]ABCDEFGHIJKLMNOPQRSTUVWXYZ<br>
     * !#$%&’()*+,-./:;?@[\]^_`abcdefghijklmnopqrstuvwxy<br>
     * z{|}~</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>EAN13</td>
     * <td>0123456789</td>
     * <td>12</td>
     * </tr>
     * <tr>
     * <td>EAN13+2</td>
     * <td>0123456789</td>
     * <td>14</td>
     * </tr>
     * <tr>
     * <td>EAN13+5</td>
     * <td>0123456789</td>
     * <td>17</td>
     * </tr>
     * <tr>
     * <td></td>
     * <td>0123456789</td>
     * <td></td>
     * </tr>
     * <tr>
     * <td>EAN8</td>
     * <td>0123456789</td>
     * <td>7</td>
     * </tr>
     * <tr>
     * <td>EAN8+2</td>
     * <td>0123456789</td>
     * <td>9</td>
     * </tr>
     * <tr>
     * <td>EAN8+5</td>
     * <td>0123456789</td>
     * <td>12</td>
     * </tr>
     * <tr>
     * <td>CODA</td>
     * <td>0123456789-$:/.+</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>POST</td>
     * <td>0123456789</td>
     * <td>5,9,11</td>
     * </tr>
     * <tr>
     * <td>UPCA</td>
     * <td>0123456789</td>
     * <td>11</td>
     * </tr>
     * <tr>
     * <td>UPCA+2</td>
     * <td>0123456789</td>
     * <td>13</td>
     * </tr>
     * <tr>
     * <td>UPCA+5</td>
     * <td>0123456789</td>
     * <td>16</td>
     * </tr>
     * <tr>
     * <td>UPCE</td>
     * <td>0123456789</td>
     * <td>6</td>
     * </tr>
     * <tr>
     * <td>UPCE+2</td>
     * <td>0123456789</td>
     * <td>8</td>
     * </tr>
     * <tr>
     * <td>UPCE+5</td>
     * <td>0123456789</td>
     * <td>11</td>
     * </tr>
     * <tr>
     * <td>MSI</td>
     * <td>0123456789</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>MSIC</td>
     * <td>0123456789</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>PLESSEY</td>
     * <td>0123456789</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>CPOST</td>
     * <td>0123456789</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>ITF14</td>
     * <td>0123456789</td>
     * <td>13</td>
     * </tr>
     * <tr>
     * <td>EAN14</td>
     * <td>0123456789</td>
     * <td>13</td>
     * </tr>
     * <tr>
     * <td>11</td>
     * <td>0123456789-</td>
     * <td>-</td>
     * </tr>
     * <tr>
     * <td>TELEPEN</td>
     * <td>ASCII 0 to 127</td>
     * <td>30</td>
     * </tr>
     * <tr>
     * <td>TELEPENN</td>
     * <td>0123456789</td>
     * <td>60</td>
     * </tr>
     * <tr>
     * <td>PLANET</td>
     * <td>0123456789</td>
     * <td>38</td>
     * </tr>
     * <tr>
     * <td>CODE 49</td>
     * <td>ASCII 0 to 127</td>
     * <td>81</td>
     * </tr>
     * <tr>
     * <td>DPI</td>
     * <td>0123456789</td>
     * <td>11</td>
     * </tr>
     * <tr>
     * <td>DPL</td>
     * <td>0123456789</td>
     * <td>13</td>
     * </tr>
     * <tr>
     * <td>LOGMARS</td>
     * <td>0123456789[Space]ABCDEFGHIJKLMNOPQRSTUVWXYZ<br>
     * -.$/+%</td>
     * <td>-</td>
     * </tr>
     * </table>
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("BARCODE: x and y positions are required");
        }

        StringBuilder commandBuilder = new StringBuilder(LabelFormatCommand.BARCODE.name());
        commandBuilder.append(EMPTY_SPACE);

        if (!hasFloatDecimals(xCoordinate)) {
            commandBuilder.append(xCoordinate.intValue());
        } else {
            commandBuilder.append(xCoordinate);
        }

        commandBuilder.append(COMMA);

        if (!hasFloatDecimals(yCoordinate)) {
            commandBuilder.append(yCoordinate.intValue());
        } else {
            commandBuilder.append(yCoordinate);
        }

        commandBuilder.append(COMMA)
                .append(EMPTY_SPACE)
                .append(ESCAPED_DOUBLE_QUOTE)
                .append(codeType.getCodeType()).append(ESCAPED_DOUBLE_QUOTE).append(COMMA)
                .append(height).append(COMMA)
                .append(hrcAlignment.getAlignment()).append(COMMA)
                .append(rotation).append(COMMA)
                .append(narrow).append(COMMA)
                .append(wide).append(COMMA);

        if (alignment != null) {
            commandBuilder.append(alignment.getAlignment()).append(COMMA);
        }

        commandBuilder.append(ESCAPED_DOUBLE_QUOTE).append(content).append(ESCAPED_DOUBLE_QUOTE)
                .append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
