package org.fintrace.core.drivers.tspl.commands.label;

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command draws CODABLOCK F mode barcode.
 * <br><br>
 * <b>Syntax</b><br>
 *     CODABLOCK x,y,rotation,[row height,]module width,] "content"
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class CodaBlockF implements TSPLCommand<byte[]> {
    /**
     * x-coordinate of codabar on the label
     */
    private int xCoordinate;

    /**
     * y-coordinate of codabar on the label
     */
    private int yCoordinate;

    /**
     * rotation
     */
    private BarcodeRotation rotation;

    /**
     * Row height (in dots).<br>
     * The height of individual row equals to row height x module width (Default is 8)
     */
    private int rowHeight = 8;

    /**
     * Width of narrow element of CODABLOCK in dots (Default is 2)
     */
    private int moduleWidth = 2;

    /**
     * content of CODABLOCK bar code
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        return (LabelCommand.CODABLOCK.name() + " " + xCoordinate
                + "," + yCoordinate + "," + rotation + ","+ rowHeight
                + "," + moduleWidth + ", \"" + content + "\"").getBytes(US_ASCII);
    }
}
