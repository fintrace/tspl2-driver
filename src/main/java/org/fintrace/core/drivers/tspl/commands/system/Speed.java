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

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;

import static org.fintrace.core.drivers.tspl.DriverConstants.EMPTY_SPACE;
import static org.fintrace.core.drivers.tspl.DriverConstants.NEW_LINE_FEED;

/**
 * This command defines the print speed.<br>
 * <p>
 * <b>Syntax</b><br>
 * SPEED n<br>
 * <blockquote><i>n Printing speed in inch per second</i></blockquote>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Speed implements TSPLCommand {

    /**
     * Printing speed in inch per second
     */
    private Double printSpeed;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (printSpeed == null) {
            throw new LabelParserException("ParseException SPEED Command: speed can't be empty");
        }

        return SystemCommand.SPEED.name()
                + EMPTY_SPACE
                + printSpeed
                + NEW_LINE_FEED;
    }
}
