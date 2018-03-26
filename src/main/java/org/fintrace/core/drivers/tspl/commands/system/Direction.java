/*
 * Copyright 2017 Finium Solutions
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

import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

import static org.fintrace.core.drivers.tspl.commands.system.SystemCommand.DIRECTION;
import static java.nio.charset.StandardCharsets.US_ASCII;

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
@NoArgsConstructor
public class Direction implements TSPLCommand<byte[]> {
    private boolean printPositionAsFeed = true;
    private boolean printMirrorImage = false;

    /**
     * @param printPositionAsFeed
     * @param printMirrorImage
     */
    public Direction(boolean printPositionAsFeed, boolean printMirrorImage) {
        this.printPositionAsFeed = printPositionAsFeed;
        this.printMirrorImage = printMirrorImage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        return (DIRECTION.name() + (printPositionAsFeed ? "1" : "0") + ","
                + (printMirrorImage ? "1" : "0") + "\n").getBytes(US_ASCII);
    }
}
