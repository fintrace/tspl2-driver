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

import static org.fintrace.core.drivers.tspl.DriverConstants.*;
import static org.fintrace.core.drivers.tspl.commands.system.SystemCommand.DIRECTION;

/**
 * This command defines the printout direction and mirror image. This will be stored in the printer
 * memory. <br>
 * <p>
 * <b>Syntax</b><br>
 * DIRECTION n[,m]<br>
 * <blockquote>n 0 or 1.<br>
 * m 0: Print normal image. 1: Print mirror image</blockquote>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class Direction implements TSPLCommand {
    private Boolean printPositionAsFeed;
    private Boolean printMirrorImage;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        if (printPositionAsFeed == null) {
            throw new LabelParserException("ParseException Direction Command: print "
                    + "position must be set");
        }

        StringBuilder commandBuilder = new StringBuilder(DIRECTION.name());
        commandBuilder.append(EMPTY_SPACE)
                .append(printPositionAsFeed ? "1" : "0");

        if (printMirrorImage != null) {
            commandBuilder.append(COMMA)
                    .append(printMirrorImage ? "1" : "0");
        }

        commandBuilder.append(LF);

        return commandBuilder.toString();
    }
}
