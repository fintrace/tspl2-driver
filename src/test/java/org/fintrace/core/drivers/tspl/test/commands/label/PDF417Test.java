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
import org.fintrace.core.drivers.tspl.commands.label.PDF417;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class PDF417Test {

    /**
     * Without options
     */
    @Test
    public void pdf417Test() {
        String pdfStr = "PDF417 50,50,400,200,0,\"Without Options\"\n";

        PDF417 pdf417 = PDF417.builder()
                .xCoordinate(50)
                .yCoordinate(50)
                .width(400)
                .height(200)
                .rotation(BarcodeRotation.NO_ROTATION)
                .content("Without Options").build();

        Assertions.assertEquals(pdfStr, pdf417.getCommand());
    }

    /**
     * OPTION:E3
     */
    @Test
    public void pdf417ETest() {
        String pdfStr = "PDF417 50,50,400,200,0,E3,\"Error correction level:3\"\n";

        PDF417 pdf417 = PDF417.builder()
                .xCoordinate(50)
                .yCoordinate(50)
                .width(400)
                .height(200)
                .rotation(BarcodeRotation.NO_ROTATION)
                .options(Collections.singletonMap("E", 3))
                .content("Error correction level:3").build();

        Assertions.assertEquals(pdfStr, pdf417.getCommand());
    }

    /**
     * OPTION:E4
     */
    @Test
    public void pdf417ETest_2() {
        String pdfStr = "PDF417 50,50,400,200,0,E4,\"Error correction level:4\"\n";

        PDF417 pdf417 = PDF417.builder()
                .xCoordinate(50)
                .yCoordinate(50)
                .width(400)
                .height(200)
                .rotation(BarcodeRotation.NO_ROTATION)
                .options(Collections.singletonMap("E", 4))
                .content("Error correction level:4").build();

        Assertions.assertEquals(pdfStr, pdf417.getCommand());
    }

    /**
     * OPTION:E4 W4
     */
    @Test
    public void pdf417EWTest() {
        String pdfStr = "PDF417 50,50,600,600,0,E4,W4,\"Error correction level:4 module"
                + " width 4 dots\"\n";

        Map<String, Object> options = new HashMap<>();
        options.put("E", 4);
        options.put("W", 4);

        PDF417 pdf417 = PDF417.builder()
                .xCoordinate(50)
                .yCoordinate(50)
                .width(600)
                .height(600)
                .rotation(BarcodeRotation.NO_ROTATION)
                .options(options)
                .content("Error correction level:4 module width 4 dots").build();

        Assertions.assertEquals(pdfStr, pdf417.getCommand());
    }

    /**
     * OPTION:E4 W4 H4
     */
    @Test
    public void pdf417EWHTest() {
        String pdfStr = "PDF417 50,50,600,600,0,E4,W4,H4,\"Error correction level:4 module"
                + " width 4 dots bar height 4 dots\"\n";

        Map<String, Object> options = new HashMap<>();
        options.put("E", 4);
        options.put("W", 4);
        options.put("H", 4);

        PDF417 pdf417 = PDF417.builder()
                .xCoordinate(50)
                .yCoordinate(50)
                .width(600)
                .height(600)
                .rotation(BarcodeRotation.NO_ROTATION)
                .options(options)
                .content("Error correction level:4 module width 4 dots bar height 4 dots").build();

        Assertions.assertEquals(pdfStr, pdf417.getCommand());
    }

    /**
     * OPTION:E4 W4 H4 R40 C4 T1
     */
    @Test
    public void pdf417EWHRCTTest() {
        String pdfStr = "PDF417 50,50,800,800,0,E4,W4,H4,R40,C4,T1,\"Error correction"
                + " level:4"
                + " Module Width 4 dots"
                + " Bar Height 4 dots"
                + " Maximum Number of Rows:5 Rows"
                + " Maximum number of columns:90 Cols"
                + " Truncation:1\"\n";

        Map<String, Object> options = new HashMap<>();
        options.put("E", 4);
        options.put("W", 4);
        options.put("H", 4);
        options.put("R", 40);
        options.put("C", 4);
        options.put("T", 1);

        PDF417 pdf417 = PDF417.builder()
                .xCoordinate(50)
                .yCoordinate(50)
                .width(800)
                .height(800)
                .rotation(BarcodeRotation.NO_ROTATION)
                .options(options)
                .content("Error correction level:4 Module Width 4 dots Bar Height 4 dots"
                        + " Maximum Number of Rows:5 Rows"
                        + " Maximum number of columns:90 Cols"
                        + " Truncation:1").build();

        Assertions.assertEquals(pdfStr, pdf417.getCommand());
    }

    /**
     * OPTION::P1 E4 M1 U50,300,50,W4,H4,R60,C4,T0,L297
     */
    @Test
    public void pdf417PEMUWHRCTLTest() {
        String pdfStr = "PDF417 50,50,900,600,0,P1,E4,M1,U50,300,50,W4,H4,R60,C4,T0,L297,"
                + "Data compression method: P1"
                + " Error correction level: E4"
                + " Center pattern in barcode area: M1"
                + " Human Readable: Yes: U50,300,50"
                + " Module Width 4 dots: W4"
                + " Bar Height 4 dots: H4"
                + " Maximum Number of Rows: 60 Rows: R60"
                + " Maximum number of columns: 4 Cols: C4"
                + " Truncation:1: T0\n"
                + "Expression length:297: L297\"\n";

        Map<String, Object> options = new HashMap<>();
        options.put("P", 1);
        options.put("E", 4);
        options.put("M", 1);
        options.put("U", "50,300,50");
        options.put("W", 4);
        options.put("H", 4);
        options.put("R", 60);
        options.put("C", 4);
        options.put("T", 0);
        options.put("L", 297);

        PDF417 pdf417 = PDF417.builder()
                .xCoordinate(50)
                .yCoordinate(50)
                .width(900)
                .height(600)
                .rotation(BarcodeRotation.NO_ROTATION)
                .options(options)
                .content("Data compression method: P1"
                        + " Error correction level: E4"
                        + " Center pattern in barcode area: M1"
                        + " Human Readable: Yes: U50,300,50"
                        + " Module Width 4 dots: W4"
                        + " Bar Height 4 dots: H4"
                        + " Maximum Number of Rows: 60 Rows: R60"
                        + " Maximum number of columns: 4 Cols: C4"
                        + " Truncation:1: T0\n"
                        + "Expression length:297: L297\"").build();

        Assertions.assertEquals(pdfStr, pdf417.getCommand());
    }
}
