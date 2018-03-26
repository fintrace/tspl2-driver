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
 * PEEL command values
 *
 * @author Venkaiah Chowdary Koneru
 * @see DeviceConfigCommand#PEEL
 */
public enum PeelCommandValues implements CommandValues<String> {
    /**
     * Disable the self-peeing function
     */
    OFF,

    /**
     * Enable the self-peeling function
     */
    ON;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandValue() {
        return this.name();
    }
}
