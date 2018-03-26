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
package org.fintrace.core.drivers.tspl.commands.values;

import org.fintrace.core.drivers.tspl.commands.device.DeviceConfigCommand;

/**
 * PARTIAL_CUTTER command values
 *
 * @author Venkaiah Chowdary Koneru
 * @see DeviceConfigCommand#PARTIAL_CUTTER
 */
public enum PartialCutterValues implements CommandValues<String> {
    /**
     * Disable cutter function.
     */
    OFF,

    /**
     * Set printer to cut label at the end of printing job.
     */
    BATCH,

    /**
     * Set number of printing labels per cut. 0<= pieces <=65535.
     */
    Pieces;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandValue() {
        if (this == Pieces) {
            return "";
        }

        return this.name();
    }
}
