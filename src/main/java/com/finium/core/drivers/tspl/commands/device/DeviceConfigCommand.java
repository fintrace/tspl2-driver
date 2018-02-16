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
package com.finium.core.drivers.tspl.commands.device;

/**
 * High-level Device Reconfiguration Commands.
 * <p>
 * Shall not be used this directly.
 *
 * @author Venkaiah Chowdary Koneru
 * @see TSPLDeviceConfigurationCommands
 */
public enum DeviceConfigCommand {
    /**
     * This setting activates or deactivates the cutter and defines how
     * many printed labels is to be cut at one time. This setting will
     * be saved in printer memory after turning off the power. This function
     * prevents label back feeding after a cut.
     * <p>
     * <b>Syntax</b><br>
     * SET PARTIAL_CUTTER OFF/BATCH/Pieces
     */
    PARTIAL_CUTTER,

    /**
     * This setting activates or deactivates the cutter and defines how many
     * printed labels is to be cut at one time. This setting will
     * be saved in printer memory after turning off the power.
     * <p>
     * <b>Syntax</b><br>
     * SET CUTTER OFF/BATCH/Pieces
     */
    CUTTER,

    /**
     * This setting is used after SET CUTTER function. This function
     * prevents label backfeeding after a cut.
     * <p>
     * <b>Syntax</b><br>
     * SET BACK OFF/ON
     */
    BACK,

    /**
     * This setting is used to enable/disable the self-peeling function. The
     * default setting for this function is off. When this function is set on,
     * the printer stops after each label printing, and does not print the
     * next label until the peeled label is taken away. This setting will
     * be saved in printer memory when turning off the power.
     * <p>
     * <b>Syntax</b><br>
     * SET PEEL OFF/ON
     */
    PEEL,

    /**
     * This setting is used to enable/disable head open sensor. If the
     * head open sensor is turned off, an open printer head will not
     * return an error message. This setting will be saved in printer memory.
     * This command is only available for TSPL2 printers.
     * <p>
     * <b>Syntax</b><br>
     * SET HEAD ON /OFF
     */
    HEAD,

    /**
     * This setting is used to enable/disable ribbon encoder sensor detection.
     * <p>
     * <b>Syntax</b><br>
     * SET ENCODER ON /OFF
     */
    ENCODER
}
