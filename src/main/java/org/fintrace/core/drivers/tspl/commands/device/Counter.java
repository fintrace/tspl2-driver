package org.fintrace.core.drivers.tspl.commands.device;

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.DriverConstants;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;

import static org.fintrace.core.drivers.tspl.DriverConstants.EMPTY_SPACE;
import static org.fintrace.core.drivers.tspl.DriverConstants.NEW_LINE_FEED;

/**
 * To set a particular counter increment.
 * <p>
 * <b>Syntax</b><br>
 * <code>SET COUNTER @n step</code>
 * </p>
 * <p>
 * <b>Description</b><br>
 * Counters can be a real counter or a variable. This setting sets the counter number in the program and
 * its increments. There are three different types of counters: digit (0~9~0), lower case letter (a~z~a) or
 * upper case letter (A~Z~A).
 * </p>
 * refer to {@link CounterExpression} for initializing the counter.
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Counter implements TSPLCommand {

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
     * The increment of the counter, can be positive or negative.
     * -999999999 <= step <= 999999999.<br>
     * If the counter is used as a fixed variable, please set the increment to 0.
     */
    private Integer step;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {

        if (counterNumber == null) {
            throw new LabelParserException("SET COUNTER: counter number is required");
        }

        if (step == null) {
            throw new LabelParserException("SET COUNTER: increment is required");
        }

        if (counterNumber < 0 || counterNumber > 60) {
            throw new LabelParserException("SET COUNTER: Counters are only available from 0 - 60.");
        }

        if (step > 999999999 || step < -999999999) {
            throw new LabelParserException("SET COUNTER: Invalid increment of the counter.");
        }

        StringBuilder commandBuilder = new StringBuilder(DriverConstants.SET_PREFIX);
        commandBuilder.append(EMPTY_SPACE)
                .append("COUNTER")
                .append(EMPTY_SPACE)
                .append("@").append(counterNumber)
                .append(EMPTY_SPACE);

        if (step >= 0) {
            commandBuilder.append("+");
        }

        commandBuilder.append(step)
                .append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
