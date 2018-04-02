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
package com.finium.core.drivers.org.fintrace.core.drivers.tspl.test.commands.label;

import org.fintrace.core.drivers.tspl.commands.label.DataMatrix;
import org.fintrace.core.drivers.tspl.commands.label.TscLabel;
import org.fintrace.core.drivers.tspl.commands.system.ClearBuffer;
import org.fintrace.core.drivers.tspl.commands.system.Direction;
import org.fintrace.core.drivers.tspl.commands.system.Gap;
import org.fintrace.core.drivers.tspl.commands.system.Print;
import org.fintrace.core.drivers.tspl.commands.system.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class TscLabelTest {

    @Test
    public void testDataMatrix() {
        String expectedCode = "SIZE 4,3\n" +
                "GAP 0,0\n" +
                "DIRECTION 1\n" +
                "CLS\n" +
                "DMATRIX 10,110,400,400, \"DMATRIX EXAMPLE 1\"\n" +
                "DMATRIX 310,110,400,400,x6, \"DMATRIX EXAMPLE 2\"\n" +
                "DMATRIX 10,310,400,400,x8,18,18, \"DMATRIX EXAMPLE 3\"\n" +
                "PRINT 1,1\n";

        TscLabel tscLabel = TscLabel.builder()
                .element(Size.builder().labelWidth(4).labelLength(3).build())
                .element(Gap.builder().labelDistance(0).labelOffsetDistance(0).build())
                .element(Direction.builder().printPositionAsFeed(Boolean.TRUE).build())
                .element(ClearBuffer.builder().build())
                .element(DataMatrix.builder().xCoordinate(10).yCoordinate(110).width(400)
                        .height(400).content("DMATRIX EXAMPLE 1").build())
                .element(DataMatrix.builder().xCoordinate(310).yCoordinate(110).width(400)
                        .height(400).moduleSize(6).content("DMATRIX EXAMPLE 2").build())
                .element(DataMatrix.builder().xCoordinate(10).yCoordinate(310).width(400)
                        .height(400).moduleSize(8).nbRows(18).nbCols(18)
                        .content("DMATRIX EXAMPLE 3").build())
                .element(Print.builder().nbLabels(1).nbCopies(1).build())
                .build();

        Assertions.assertNotNull(tscLabel.getElements());
        Assertions.assertEquals(8, tscLabel.getElements().size());
        Assertions.assertEquals(expectedCode, tscLabel.getTsplCode());
        Assertions.assertNotNull(tscLabel.getElements());
        Assertions.assertEquals(8, tscLabel.getElements().size());
    }
}
