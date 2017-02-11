/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * Barcode.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.label;

import com.finium.core.drivers.tspl.commands.TSPLCommand;

import java.io.UnsupportedEncodingException;

/**
 * This command prints 1D barcodes. The available barcodes are listed below:<br>
 * <pre>
 * <b>Code Type Description Max. data length</b>
 * 128 Code 128, switching code subset automatically.
 * 128M Code 128, switching code subset manually.
 * EAN128 EAN128, switching code subset automatically.
 * 25 Interleaved 2 of 5. Length is even
 * 25C Interleaved 2 of 5 with check digit. Length is odd
 * 39 Code 39, switching standard and full ASCII mode automatically.
 * 39C Code 39 with check digit.
 * 93 Code 93.
 * EAN13 EAN 13. 12
 * EAN13+2 EAN 13 with 2 digits add-on. 14
 * EAN13+5 EAN 13 with 5 digits add-on. 17
 * EAN8 EAN 8. 7
 * EAN8+2 EAN 8 with 2 digits add-on. 9
 * EAN8+5 EAN 8 with 5 digits add-on. 12
 * CODA Codabar.
 * POST Postnet. 5, 9, 11
 * UPCA UPC-A. 11
 * UPCA+2 UPC-A with 2 digits add-on. 13
 * UPA+5 UPC-A with 5 digits add-on. 16
 * UPCE UPC-E. 6
 * UPCE+2 UPC-E with 2 digits add-on. 8
 * UPE+5 UPC-E with 5 digits add-on. 11
 * MSI MSI.
 * MSIC MSI with check digit.
 * PLESSEY PLESSEY.
 * CPOST China post.
 * ITF14 ITF14.  13
 * EAN14 EAN14.  13
 * 11 Code 11.
 * TELEPEN Telepen.
 * TELEPENN Telepen number.
 * PLANET Planet.
 * CODE49 Code 49.
 * DPI Deutsche Post Identcode. 11
 * DPL Deutsche Post Leitcode. 13
 * LOGMARS A special use of Code 39.
 * </pre>
 * <b>Syntax</b><br>
 * BARCODE X,Y, "code type",height,human readable,rotation,narrow,wide,[alignment,] "content "<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
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
     * human readable
     */
    private BarcodeHRCAlignment hrcAlignment;

    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        return new byte[0];
    }
}
