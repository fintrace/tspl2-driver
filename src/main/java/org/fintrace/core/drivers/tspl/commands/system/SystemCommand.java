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

/**
 * Setup and System Commands as part of label formatting
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum SystemCommand {
    /**
     * This command defines the label width and length.
     */
    SIZE,

    /**
     * Defines the gap distance between two labels.
     */
    GAP,

    /**
     * extra label feeding length
     */
    OFFSET,

    /**
     * Printing speed in inch per second
     */
    SPEED,

    /**
     * printing darkness
     */
    DENSITY,

    /**
     * printout direction and mirror image.
     */
    DIRECTION,

    /**
     * clears buffer
     */
    CLS,

    /**
     * prints the label
     */
    PRINT
}
