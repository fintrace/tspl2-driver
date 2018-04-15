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

import org.fintrace.core.drivers.tspl.commands.label.AztecBarcode;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class AztecBarcodeTest {
    private AztecBarcode aztecBarcode;

    @BeforeEach
    public void initialize() {
        this.aztecBarcode = AztecBarcode.builder().build();
    }

    @Test
    public void testDefault() {
        LabelParserException exception = Assertions.assertThrows(LabelParserException.class,
                () -> aztecBarcode.getCommand());

        Assertions.assertEquals("AZTEC: x and y positions are required",
                exception.getMessage());
    }
}
