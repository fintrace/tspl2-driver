/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * DataMatrix.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 11, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.tspl.commands.label;

import com.finium.core.drivers.tspl.commands.TSPLCommand;
import lombok.Builder;
import lombok.Data;

import java.io.UnsupportedEncodingException;

import static com.finium.core.drivers.tspl.commands.label.LabelCommand.DMATRIX;
import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command defines a DataMatrix 2D bar code. Currently, only <b>ECC200</b> error correction is supported.<br>
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
public class DataMatrix implements TSPLCommand<byte[]> {
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
    private boolean isRectangle = false;

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
    public byte[] getCommand() throws UnsupportedEncodingException {
        StringBuilder dataMatrix = new StringBuilder(DMATRIX.name());
        dataMatrix.append(" ")
                .append(xCoordinate).append(",")
                .append(yCoordinate).append(",")
                .append(width).append(",")
                .append(height).append(",");
        if (escapeSequenceCharacter != null) {
            dataMatrix.append("c").append(escapeSequenceCharacter).append(",");
        }

        if (moduleSize != null) {
            dataMatrix.append("x").append(moduleSize).append(",");
        }

        if (rotation != null) {
            dataMatrix.append("r").append(rotation.getRotation()).append(",");
        }

        dataMatrix.append("a").append(isRectangle ? 1 : 0).append(",");

        if (nbRows != null) {
            dataMatrix.append(nbRows).append(",");
        }

        if (nbCols != null) {
            dataMatrix.append(nbCols).append(",");
        }

        dataMatrix.append(" ")
                .append("\"").append(content).append("\"\n");

        return dataMatrix.toString().getBytes(US_ASCII);
    }
}
