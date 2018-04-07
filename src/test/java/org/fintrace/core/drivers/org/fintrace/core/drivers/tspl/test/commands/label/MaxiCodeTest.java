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
package org.fintrace.core.drivers.org.fintrace.core.drivers.tspl.test.commands.label;

import org.fintrace.core.drivers.tspl.commands.label.MaxiCode;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class MaxiCodeTest {

    @Test
    public void maxicodeTestMode2() {
        String maxiCodeStr = "MAXICODE 110,100,2,300,840,06810,7317,\"DEMO 2 FOR USA MAXICODE\"\n";

        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(2)
                .serviceClass(300)
                .countryCode(840)
                .postCode("06810,7317")
                .content("DEMO 2 FOR USA MAXICODE").build();

        Assertions.assertEquals(maxiCodeStr, maxiCode.getCommand());
    }

    @Test
    public void maxicodeTestMode3() {
        String maxiCodeStr = "MAXICODE 110,100,3,300,863,\"107317\",\"DEMO 3 FOR CANADA MAXICODE\"\n";

        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(3)
                .serviceClass(300)
                .countryCode(863)
                .postCode("107317")
                .content("DEMO 3 FOR CANADA MAXICODE").build();

        Assertions.assertEquals(maxiCodeStr, maxiCode.getCommand());
    }

    @Test
    public void maxicodeTestMode4() {
        String maxiCodeStr = "MAXICODE 110,100,4,\"DEMO 4 FOR MAXICODE\"\n";

        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(4)
                .content("DEMO 4 FOR MAXICODE").build();

        Assertions.assertEquals(maxiCodeStr, maxiCode.getCommand());
    }

    @Test
    public void maxicodeTestMode4_2() {
        String maxiCodeStr = "MAXICODE 600,100,4,L19,DEMO 4 FOR MAXICODE\n";

        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(600)
                .yCoordinate(100)
                .mode(4)
                .expressionLength(19)
                .content("DEMO 4 FOR MAXICODE").build();

        Assertions.assertEquals(maxiCodeStr, maxiCode.getCommand());
    }

    @Test
    public void maxicodeTestMode5() {
        String maxicodeStr = "MAXICODE 110,100,5,\"DEMO 5 FOR MAXICODE\"\n";
        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(5)
                .content("DEMO 5 FOR MAXICODE").build();

        Assertions.assertEquals(maxicodeStr, maxiCode.getCommand());
    }

    @Test
    public void maxicodeTestMode5_2() {
        String maxicodeStr = "MAXICODE 600,100,5,L19,DEMO 5 FOR MAXICODE\n";
        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(600)
                .yCoordinate(100)
                .mode(5)
                .expressionLength(19)
                .content("DEMO 5 FOR MAXICODE").build();

        Assertions.assertEquals(maxicodeStr, maxiCode.getCommand());
    }

    @Test
    public void maxicodeTest_2() {
        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(2)
                .serviceClass(300)
                .countryCode(840)
                .postCode("068109")
                .content("DEMO 2 FOR USA MAXICODE").build();

        Throwable exception = Assertions.assertThrows(LabelParserException.class, () -> {
            maxiCode.getCommand();
        });
        Assertions.assertEquals("MAXICODE: post for USA is in 99999,9999 format", exception.getMessage());
    }

    @Test
    public void maxicodeTest_3() {
        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(6)
                .build();

        Throwable exception = Assertions.assertThrows(LabelParserException.class, () -> {
            maxiCode.getCommand();
        });
        Assertions.assertEquals("MAXICODE: mode must be 2,3,4 or 5", exception.getMessage());
    }

    @Test
    public void maxicodeTest_4() {
        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(3)
                .build();

        Throwable exception = Assertions.assertThrows(LabelParserException.class, () -> {
            maxiCode.getCommand();
        });
        Assertions.assertEquals("MAXICODE: class, country and "
                + "post must be specified for mode 2 and 3", exception.getMessage());
    }

    @Test
    public void maxicodeTest_5() {
        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(2)
                .serviceClass(300)
                .build();

        Throwable exception = Assertions.assertThrows(LabelParserException.class, () -> {
            maxiCode.getCommand();
        });
        Assertions.assertEquals("MAXICODE: class, country and "
                + "post must be specified for mode 2 and 3", exception.getMessage());
    }

    @Test
    public void maxicodeTest_6() {
        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(2)
                .serviceClass(300)
                .countryCode(840)
                .build();

        Throwable exception = Assertions.assertThrows(LabelParserException.class, () -> {
            maxiCode.getCommand();
        });
        Assertions.assertEquals("MAXICODE: class, country and "
                + "post must be specified for mode 2 and 3", exception.getMessage());
    }

    @Test
    public void maxicodeTest_7() {
        MaxiCode maxiCode = MaxiCode.builder().xCoordinate(110)
                .yCoordinate(100)
                .mode(3)
                .serviceClass(300)
                .countryCode(863)
                .postCode("23242")
                .build();

        Throwable exception = Assertions.assertThrows(LabelParserException.class, () -> {
            maxiCode.getCommand();
        });
        Assertions.assertEquals("MAXICODE: post for mode is 6 alphanumeric", exception.getMessage());
    }
}
