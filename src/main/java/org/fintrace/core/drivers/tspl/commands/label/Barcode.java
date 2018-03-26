/*
 * Copyright 2017 Finium Solutions
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

import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import lombok.Builder;
import lombok.Data;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command prints 1D barcodes. The available barcodes are listed below:<br>
 * <pre>
 * <b>Code Type Description Max. data length</b><br>
 * 128 Code 128, switching code subset automatically.<br>
 * 128M Code 128, switching code subset manually.<br>
 * EAN128 EAN128, switching code subset automatically.<br>
 * 25 Interleaved 2 of 5. Length is even<br>
 * 25C Interleaved 2 of 5 with check digit. Length is odd<br>
 * 39 Code 39, switching standard and full ASCII mode automatically.<br>
 * 39C Code 39 with check digit.<br>
 * 93 Code 93.<br>
 * EAN13 EAN 13. 12<br>
 * EAN13+2 EAN 13 with 2 digits add-on. 14<br>
 * EAN13+5 EAN 13 with 5 digits add-on. 17<br>
 * EAN8 EAN 8. 7<br>
 * EAN8+2 EAN 8 with 2 digits add-on. 9<br>
 * EAN8+5 EAN 8 with 5 digits add-on. 12<br>
 * CODA Codabar.<br>
 * POST Postnet. 5, 9, 11<br>
 * UPCA UPC-A. 11<br>
 * UPCA+2 UPC-A with 2 digits add-on. 13<br>
 * UPA+5 UPC-A with 5 digits add-on. 16<br>
 * UPCE UPC-E. 6<br>
 * UPCE+2 UPC-E with 2 digits add-on. 8<br>
 * UPE+5 UPC-E with 5 digits add-on. 11<br>
 * MSI MSI.<br>
 * MSIC MSI with check digit.<br>
 * PLESSEY PLESSEY.<br>
 * CPOST China post.<br>
 * ITF14 ITF14.  13<br>
 * EAN14 EAN14.  13<br>
 * 11 Code 11.<br>
 * TELEPEN Telepen.<br>
 * TELEPENN Telepen number.<br>
 * PLANET Planet.<br>
 * CODE49 Code 49.<br>
 * DPI Deutsche Post Identcode. 11<br>
 * DPL Deutsche Post Leitcode. 13<br>
 * LOGMARS A special use of Code 39.<br>
 * </pre>
 * <b>Syntax</b><br>
 * BARCODE X,Y, "code type",height,human readable,rotation,narrow,wide,[alignment,] "content "<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Barcode implements TSPLCommand<byte[]> {
    /**
     * x-coordinate of bar code on the label
     */
    private int xCoordinate;

    /**
     * y-coordinate of bar code on the label
     */
    private int yCoordinate;

    /**
     * Bar code height (in dots)
     */
    private int height;

    /**
     * code type
     */
    private BarcodeType codeType;

    /**
     * human readable
     */
    private BarcodeHRCAlignment hrcAlignment;

    /**
     * rotation
     */
    private BarcodeRotation rotation;

    /**
     * Width of narrow element (in dots)
     */
    private int narrow;

    /**
     * Width of wide element (in dots)
     */
    private int wide;

    /**
     * alignment of barcode. Default is (0) : left
     */
    private BarcodeAlignment alignment = BarcodeAlignment.DEFAULT_LEFT;

    /**
     * content of the barcode
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        return (LabelCommand.BARCODE.name() + " " + xCoordinate + ","
                + yCoordinate + ","
                + "\"" + codeType.getCodeType() + "\"" + ","
                + height + ","
                + hrcAlignment.getAlignment() + ","
                + rotation + ","
                + narrow + ","
                + wide + ","
                + alignment.getAlignment() + ","
                + "\"" + content + "\"\n").getBytes(US_ASCII);
    }
}
