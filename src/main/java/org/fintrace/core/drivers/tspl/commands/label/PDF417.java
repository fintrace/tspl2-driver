package org.fintrace.core.drivers.tspl.commands.label;

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;

import java.util.Map;

import static org.fintrace.core.drivers.tspl.DriverConstants.*;

/**
 * This command defines a PDF417 2D bar code<br>
 * <b>Syntax</b><br>
 * PDF417 x,y,width,height,rotate,[option], "content"<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Builder
@Data
public class PDF417 implements TSPLCommand {
    /**
     * X-coordinate of starting point (in dot)
     */
    private Integer xCoordinate;

    /**
     * Y-coordinate of starting point (in dot)
     */
    private Integer yCoordinate;

    /**
     * Expected width (in dots)
     */
    private Integer width;

    /**
     * Expected height (in dots)
     */
    private Integer height;

    /**
     * Rotation counterclockwise<br>
     * 0 : No rotation<br>
     * 90 : Rotate 90 degrees<br>
     * 180 : Rotate 180 degrees<br>
     * 270 : Rotate 270 degrees<br>
     */
    private BarcodeRotation rotation;

    /**
     * <table valign="top" border="1">
     * <tr>
     * <td>P</td>
     * <td>Data compression method<br>
     * 0: Auto encoding<br>
     * 1: Binary mode</td>
     * </tr>
     * <tr>
     * <td>E</td>
     * <td>Error correction level (Range: 0~8)</td>
     * </tr>
     * <tr>
     * <td>M</td>
     * <td>Center pattern in barcode area<br>
     * 0: The pattern will print upper left justified the area<br>
     * 1: The pattern is printed middle of area</td>
     * </tr>
     * <tr>
     * <td>Ux,y,c</td>
     * <td>Human readable<br>
     * x: Human readable characters in the specified x-coordinate<br>
     * y: Human readable characters in the specified y-coordinate<br>
     * c: Maximum characters of human readable character per line</td>
     * </tr>
     * <tr>
     * <td>W</td>
     * <td>Module width in dot (Range: 2~9)</td>
     * </tr>
     * <tr>
     * <td>H</td>
     * <td>Bar height in dot (Range: 4~99)</td>
     * </tr>
     * <tr>
     * <td>R</td>
     * <td>Maximum number of rows</td>
     * </tr>
     * <tr>
     * <td>C</td>
     * <td>Maximum number of columns</td>
     * </tr>
     * <tr>
     * <td>T</td>
     * <td>Truncation<br>
     * 0: Not truncated<br>
     * 1: Truncated</td>
     * </tr>
     * <tr>
     * <td>Lm</td>
     * <td>Expression length, 1 <= m <= 2048 (without “ for content)</td>
     * </tr>
     * </table>
     */
    private Map<String, Object> options;

    /**
     * Content of PDF417 2D bar code<br>
     * <b><i>Note:<br>
     * If parameter Lm is used, double quotes (") are unnecessary for content.</i></b>
     */
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (xCoordinate == null || yCoordinate == null) {
            throw new LabelParserException("PDF417: x and y positions are required");
        }

        if (width == null) {
            throw new LabelParserException("PDF417: please specify width");
        }

        if (height == null) {
            throw new LabelParserException("PDF417: please specify height");
        }

        if (rotation == null) {
            throw new LabelParserException("PDF417: please specify rotation");
        }

        if (content == null) {
            throw new LabelParserException("PDF417: please specify content");
        }

        if (content.length() > 2048) {
            throw new LabelParserException("PDF417: content is more than 2048 characters");
        }

        StringBuilder commandBuilder = new StringBuilder(LabelFormatCommand.PDF417.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(xCoordinate).append(COMMA)
                .append(yCoordinate).append(COMMA)
                .append(width).append(COMMA)
                .append(height).append(COMMA)
                .append(rotation.getRotation()).append(COMMA);

        if (options != null) {
            if (options.containsKey("P")) {
                commandBuilder.append("P").append(((Integer)options.get("P")).intValue())
                        .append(COMMA);
            }

            if (options.containsKey("E")) {
                commandBuilder.append("E").append(((Integer)options.get("E")).intValue())
                        .append(COMMA);
            }

            if (options.containsKey("M")) {
                commandBuilder.append("M").append(((Integer)options.get("M")).intValue())
                        .append(COMMA);
            }

            if (options.containsKey("U")) {
                commandBuilder.append("U").append(options.get("U"))
                        .append(COMMA);
            }

            if (options.containsKey("W")) {
                commandBuilder.append("W").append(((Integer)options.get("W")).intValue())
                        .append(COMMA);
            }

            if (options.containsKey("H")) {
                commandBuilder.append("H").append(((Integer)options.get("H")).intValue())
                        .append(COMMA);
            }

            if (options.containsKey("R")) {
                commandBuilder.append("R").append(((Integer)options.get("R")).intValue())
                        .append(COMMA);
            }

            if (options.containsKey("C")) {
                commandBuilder.append("C").append(((Integer)options.get("C")).intValue())
                        .append(COMMA);
            }

            if (options.containsKey("T")) {
                commandBuilder.append("T").append(((Integer)options.get("T")).intValue())
                        .append(COMMA);
            }

            if (options.containsKey("L")) {
                commandBuilder.append("L").append(((Integer)options.get("L")).intValue())
                        .append(COMMA);
            }
        }

        if (options == null || !options.containsKey("L")) {
            commandBuilder.append(ESCAPED_DOUBLE_QUOTE);
        }
        commandBuilder.append(content);

        if (options == null || !options.containsKey("L")) {
            commandBuilder.append(ESCAPED_DOUBLE_QUOTE);
        }

        commandBuilder.append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
