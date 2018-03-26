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
package org.fintrace.core.drivers.tspl.commands.label;

/**
 * @author Venkaiah Chowdary Koneru
 */
public enum LabelCommand {

    /**
     * barcode
     */
    BAR,

    /**
     * 1D barcodes.
     */
    BARCODE,

    /**
     * to draw rectangles on the label.
     */
    BOX,

    /**
     * to draw circle on the label.
     */
    CIRCLE,

    /**
     * to draw an ellipse on the label.
     */
    ELLIPSE,

    /**
     * DataMatrix 2D bar code
     */
    DMATRIX
}
