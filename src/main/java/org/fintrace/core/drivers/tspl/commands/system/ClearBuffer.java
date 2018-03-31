package org.fintrace.core.drivers.tspl.commands.system;

import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command clears the image buffer.
 * <p>
 * <b>Syntax:</b><br>
 * CLS
 * </p>
 * <p>
 * <i>NOTE:</i><br>
 * This command must be placed after SIZE command.<br>
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
public class ClearBuffer implements TSPLCommand<byte[]> {

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() {
        return (SystemCommand.CLS.name() + "\n").getBytes(US_ASCII);
    }
}
