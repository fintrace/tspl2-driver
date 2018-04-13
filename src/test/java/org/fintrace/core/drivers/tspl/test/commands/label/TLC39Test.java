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
import org.fintrace.core.drivers.tspl.commands.label.TLC39;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class TLC39Test {

    @Test
    public void testTlc39() {
        String stringLabel = "TLC39 10,50,0,\"123456,SN00000001,00601,01501\"\n";

        TLC39 tlc39Command = TLC39.builder()
                .xCoordinate(10).yCoordinate(50)
                .rotation(BarcodeRotation.NO_ROTATION)
                .eciNumber(123456)
                .serialNumber("SN00000001")
                .additionalData("00601,01501").build();

        Assertions.assertNull(tlc39Command.getHeight());
        Assertions.assertEquals(stringLabel, tlc39Command.getCommand());
    }

    @Test
    public void testTlc39_2() {
        String stringLabel = "TLC39 310,50,0,80,3,6,3,4,\"123456,SN00000001,00601,01501\"\n";

        TLC39 tlc39Command = TLC39.builder()
                .xCoordinate(310).yCoordinate(50)
                .rotation(BarcodeRotation.NO_ROTATION)
                .height(80).narrow(3)
                .wide(6)
                .cellWidth(3).cellHeight(4)
                .eciNumber(123456)
                .serialNumber("SN00000001")
                .additionalData("00601,01501").build();

        Assertions.assertEquals(310, tlc39Command.getXCoordinate().intValue());
        Assertions.assertEquals(50, tlc39Command.getYCoordinate().intValue());
        Assertions.assertEquals(0, tlc39Command.getRotation().getRotation());
        Assertions.assertEquals(80, tlc39Command.getHeight().intValue());
        Assertions.assertEquals(3, tlc39Command.getNarrow().intValue());
        Assertions.assertEquals(6, tlc39Command.getWide().intValue());
        Assertions.assertEquals(3, tlc39Command.getCellWidth().intValue());
        Assertions.assertEquals(4, tlc39Command.getCellHeight().intValue());
        Assertions.assertEquals(123456, tlc39Command.getEciNumber().intValue());
        Assertions.assertEquals("SN00000001", tlc39Command.getSerialNumber());

        Assertions.assertEquals(stringLabel, tlc39Command.getCommand());
    }
}
