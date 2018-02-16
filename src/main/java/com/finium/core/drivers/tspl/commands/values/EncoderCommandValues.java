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
package com.finium.core.drivers.tspl.commands.values;

import com.finium.core.drivers.tspl.commands.device.DeviceConfigCommand;

/**
 * ENCODER command values
 *
 * @author Venkaiah Chowdary Koneru
 * @see DeviceConfigCommand#ENCODER
 */
public enum EncoderCommandValues implements CommandValues<String> {
    /**
     * Disable ribbon encoder sensor.
     */
    OFF,

    /**
     * Enable ribbon encoder sensor.
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
