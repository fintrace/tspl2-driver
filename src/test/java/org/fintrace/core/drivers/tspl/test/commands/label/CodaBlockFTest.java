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

import org.fintrace.core.drivers.tspl.commands.label.BarcodeRotation;
import org.fintrace.core.drivers.tspl.commands.label.CodaBlockF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class CodaBlockFTest {

    @Test
    public void testDefault() {
        CodaBlockF codaBlockF = CodaBlockF.builder().build();

        Assertions.assertNull(codaBlockF.getRowHeight());
        Assertions.assertNull(codaBlockF.getModuleWidth());
        Assertions.assertNull(codaBlockF.getContent());
        Assertions.assertNull(codaBlockF.getXCoordinate());
        Assertions.assertNull(codaBlockF.getYCoordinate());
        Assertions.assertNull(codaBlockF.getRotation());
    }

    @Test
    public void testCommand() {
        String command = "CODABLOCK 10,50,0, \"We stand behind our products.\"\n";
        String content = "We stand behind our products.";
        CodaBlockF codaBlockF = CodaBlockF.builder()
                .content(content)
                .rotation(BarcodeRotation.NO_ROTATION)
                .xCoordinate(10f)
                .yCoordinate(50f)
                .build();

        Assertions.assertNull(codaBlockF.getRowHeight());
        Assertions.assertNull(codaBlockF.getModuleWidth());
        Assertions.assertEquals(content, codaBlockF.getContent());
        Assertions.assertEquals(10, codaBlockF.getXCoordinate().intValue());
        Assertions.assertEquals(50, codaBlockF.getYCoordinate().intValue());
        Assertions.assertEquals(BarcodeRotation.NO_ROTATION, codaBlockF.getRotation());

        Assertions.assertEquals(command, new String(codaBlockF.getCommand()));
    }
}
