package org.fintrace.core.drivers.tspl.test.commands.system;

import org.fintrace.core.drivers.tspl.commands.system.Cut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class CutTest {

    private Cut cut;

    @BeforeEach
    public void initialize() {
        cut = Cut.builder().build();
    }

    @Test
    public void testDefault() {
        Assertions.assertEquals("CUT\n", cut.getCommand());
    }
}
