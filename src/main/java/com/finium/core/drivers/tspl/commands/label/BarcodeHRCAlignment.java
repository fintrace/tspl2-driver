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
 * Human readable code alignment<br>
 * <p>
 * 0: not readable<br>
 * 1: human readable aligns to left<br>
 * 2: human readable aligns to center<br>
 * 3: human readable aligns to right<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum BarcodeHRCAlignment {
    /**
     * not readable
     */
    NO_HRC_DISPLAY(0),

    /**
     * human readable aligns to left
     */
    HRC_ALIGN_LEFT(1),

    /**
     * human readable aligns to center
     */
    HRC_ALIGN_CENTER(2),

    /**
     * human readable aligns to right
     */
    HRC_ALIGN_RIGHT(3);

    private int alignment;

    /**
     * @param alignment
     */
    private BarcodeHRCAlignment(int alignment) {
        this.alignment = alignment;
    }

    /**
     * @return
     */
    public int getAlignment() {
        return alignment;
    }
}
