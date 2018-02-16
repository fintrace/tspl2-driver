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
package com.finium.core.drivers.tspl.commands.label;

/**
 * 0 : No rotation<br>
 * 90 : Rotate 90 degrees clockwise<br>
 * 180 : Rotate 180 degrees clockwise<br>
 * 270 : Rotate 270 degrees clockwise<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum BarcodeRotation {
    /**
     * No rotation
     */
    NO_ROTATION(0),

    /**
     * Rotate 90 degrees clockwise
     */
    DEGREES_90(90),

    /**
     * Rotate 180 degrees clockwise
     */
    DEGREES_180(180),

    /**
     * Rotate 270 degrees clockwise
     */
    DEGREES_270(270);

    /**
     * rotation value
     */
    private int rotation;

    /**
     * @param rotation
     */
    BarcodeRotation(int rotation) {
        this.rotation = rotation;
    }

    /**
     * @return
     */
    public int getRotation() {
        return rotation;
    }
}
