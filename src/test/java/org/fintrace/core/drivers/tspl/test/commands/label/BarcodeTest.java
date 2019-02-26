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
package org.fintrace.core.drivers.tspl.test.commands.label;

import org.fintrace.core.drivers.tspl.commands.label.Barcode;
import org.fintrace.core.drivers.tspl.commands.label.BarcodeAlignment;
import org.fintrace.core.drivers.tspl.commands.label.BarcodeHRCAlignment;
import org.fintrace.core.drivers.tspl.commands.label.BarcodeType;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class BarcodeTest {
    private Barcode barcode;

    @BeforeEach
    public void initialize() {
        this.barcode = Barcode.builder().build();
    }

    @Test
    public void testDefault() {
        LabelParserException exception = Assertions.assertThrows(LabelParserException.class,
                () -> barcode.getCommand());
        Assertions.assertEquals("BARCODE: x and y positions are required", exception.getMessage());
    }

    @Test
    public void testNullY() {
        barcode.setXCoordinate(80F);
        LabelParserException exception = Assertions.assertThrows(LabelParserException.class,
                () -> barcode.getCommand());
        Assertions.assertEquals("BARCODE: x and y positions are required", exception.getMessage());
    }

    @Test
    public void testBarcodeWithDefaults() {
        barcode.setXCoordinate(380F);
        barcode.setYCoordinate(220F);
        barcode.setCodeType(BarcodeType.CODE_128);
        barcode.setHeight(100);
        barcode.setNarrow(1);
        barcode.setWide(1);
        barcode.setContent("123456");
        Assertions.assertEquals("BARCODE 380,220, \"128\",100,0,0,1,1,\"123456\"\n", barcode.getCommand());
    }

    @Test
    public void testBarcode() {
        barcode.setXCoordinate(380F);
        barcode.setYCoordinate(220F);
        barcode.setCodeType(BarcodeType.CODE_128);
        barcode.setHrcAlignment(BarcodeHRCAlignment.HRC_ALIGN_CENTER);
        barcode.setHeight(100);
        barcode.setNarrow(1);
        barcode.setWide(1);
        barcode.setAlignment(BarcodeAlignment.RIGHT);
        barcode.setContent("123456");
        Assertions.assertEquals("BARCODE 380,220, \"128\",100,2,0,1,1,3,\"123456\"\n", barcode.getCommand());
    }
}
