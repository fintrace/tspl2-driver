package com.finium.core.drivers.org.fintrace.core.drivers.tspl.test.commands.label;

import org.fintrace.core.drivers.tspl.commands.label.Erase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class EraseTest {

    @Test
    public void testEraseCommand() {
        String stringCommand = "ERASE 150,150,200,200\n";

        Erase eraseCommand = Erase.builder().xCoordinate(150).yCoordinate(150)
                .width(200).height(200).build();

        Assertions.assertEquals(stringCommand, eraseCommand.getCommand());
    }

}
