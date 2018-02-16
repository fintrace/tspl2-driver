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
 * alignment of barcode<br>
 * <p>
 * 0 : default (Left)<br>
 * 1 : Left<br>
 * 2 : Center<br>
 * 3 : Right<br>
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum BarcodeAlignment {
    /**
     * default (Left)
     */
    DEFAULT_LEFT(0),

    /**
     * Left
     */
    LEFT(1),

    /**
     * Center
     */
    CENTER(2),

    /**
     * Right
     */
    RIGHT(3);

    private int alignment;

    /**
     * @param alignment
     */
    private BarcodeAlignment(int alignment) {
        this.alignment = alignment;
    }

    /**
     * @return
     */
    public int getAlignment() {
        return alignment;
    }
}
