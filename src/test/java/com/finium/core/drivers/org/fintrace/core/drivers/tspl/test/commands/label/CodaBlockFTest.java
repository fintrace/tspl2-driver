package com.finium.core.drivers.org.fintrace.core.drivers.tspl.test.commands.label;

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

        Assertions.assertEquals(8, codaBlockF.getRowHeight());
        Assertions.assertEquals(2, codaBlockF.getModuleWidth());
        Assertions.assertNull(codaBlockF.getContent());
        Assertions.assertNull(codaBlockF.getXCoordinate());
        Assertions.assertNull(codaBlockF.getYCoordinate());
        Assertions.assertEquals(BarcodeRotation.NO_ROTATION, codaBlockF.getRotation());
    }

    @Test
    public void testCommand() {
        String command = "CODABLOCK 10,50,0,8,2, \"We stand behind our products.\"\n";
        String content = "We stand behind our products.";
        CodaBlockF codaBlockF = CodaBlockF.builder()
                .content(content)
                .rotation(BarcodeRotation.NO_ROTATION)
                .xCoordinate(10)
                .yCoordinate(50)
                .build();

        Assertions.assertEquals(8, codaBlockF.getRowHeight());
        Assertions.assertEquals(2, codaBlockF.getModuleWidth());
        Assertions.assertEquals(content, codaBlockF.getContent());
        Assertions.assertEquals(10, codaBlockF.getXCoordinate());
        Assertions.assertEquals(50, codaBlockF.getYCoordinate());
        Assertions.assertEquals(BarcodeRotation.NO_ROTATION, codaBlockF.getRotation());

        Assertions.assertEquals(command, new String(codaBlockF.getCommand()));
    }
}
