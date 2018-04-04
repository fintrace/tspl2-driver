package org.fintrace.core.drivers.tspl.commands.device;

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;

import static org.fintrace.core.drivers.tspl.DriverConstants.*;

/**
 * <p>
 * <b>Syntax</b><br>
 * <code>@n= "Expression "</code>
 * </p>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class CounterExpression implements TSPLCommand {

    /**
     * There are 61 counters available (@0 ~ @60) in the printer.
     * <br>@0 to @50 will be cleared while restarting the printer. @51 to @60
     * will be stored in printer until the printer is restored to factory default.
     * <br>
     * <b>@51~@55 were supported since V6.37 EZ.</b>
     * <b>@56~@60 were supported since V6.74 EZ.</b>
     */
    private Integer counterNumber;

    /**
     * Initial string. String length is 101 bytes
     */
    private String expression;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (counterNumber == null) {
            throw new LabelParserException("@n \"expression\": counter number is required");
        }

        if (counterNumber < 0 || counterNumber > 60) {
            throw new LabelParserException("@n \"expression\": Counters are only available from 0 - 60.");
        }

        StringBuilder commandBuilder = new StringBuilder("@");
        commandBuilder.append(counterNumber)
                .append("=")
                .append(ESCAPED_DOUBLE_QUOTE).append(expression).append(ESCAPED_DOUBLE_QUOTE)
                .append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
