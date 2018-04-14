package org.fintrace.core.drivers.tspl.commands.system;

import lombok.Builder;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static org.fintrace.core.drivers.tspl.DriverConstants.LF;
import static org.fintrace.core.drivers.tspl.commands.system.SystemCommand.CUT;

/**
 * This command activates the cutter to immediately cut the labels without back feeding the label.
 * <p>
 * <b><i>Syntax</i></b><br>
 * CUT
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Builder
public class Cut implements TSPLCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        return CUT.name() + LF;
    }
}
