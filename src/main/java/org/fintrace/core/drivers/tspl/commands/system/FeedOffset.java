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
package org.fintrace.core.drivers.tspl.commands.system;

import lombok.NoArgsConstructor;
import org.fintrace.core.drivers.tspl.commands.TSPLStringCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;

import static org.fintrace.core.drivers.tspl.DriverConstants.*;

/**
 * This command defines the selective, extra label feeding length
 * each form feed takes, which, especially in peel-off mode and cutter
 * mode, is used to adjust label stop position, so as for label to
 * register at proper places for the intended purposes. The printer back
 * tracks the extra feeding length before the next run of printing.
 *
 * @author Venkaiah Chowdary Koneru
 */
@NoArgsConstructor
public class FeedOffset extends TSPLStringCommand {
    /**
     * The offset distance (mm).<br>
     * <b>CAUTION: </b>Impropriety offset value may cause paper jam.
     */
    private Integer offsetDistance;

    /**
     * @param offsetDistance The offset distance (mm).
     *                       Impropriety offset value may cause paper jam.
     * @throws LabelParserException if offsetDistance is null
     */
    public FeedOffset(Integer offsetDistance) {
        if (offsetDistance == null) {
            throw new LabelParserException("ParseException OFFSET Command: offset can't be empty");
        }

        this.offsetDistance = offsetDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (offsetDistance == null) {
            throw new LabelParserException("ParseException OFFSET Command: offset can't be empty");
        }

        StringBuilder commandBuilder = new StringBuilder(SystemCommand.OFFSET.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(offsetDistance).append(EMPTY_SPACE).append(UNIT_MM)
                .append(NEW_LINE_FEED);

        return commandBuilder.toString();
    }
}
