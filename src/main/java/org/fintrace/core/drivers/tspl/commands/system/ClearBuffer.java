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
