package com.finium.core.drivers.org.fintrace.core.drivers.tspl.test.commands.device;

import org.fintrace.core.drivers.tspl.commands.device.Counter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class CounterTest {

    @Test
    public void testSetCounterIncrement() {
        Counter counter = Counter.builder().counterNumber(1).step(0).build();

        Assertions.assertEquals(1, counter.getCounterNumber().intValue());
        Assertions.assertEquals(0, counter.getStep().intValue());

        Assertions.assertEquals("SET COUNTER @1 +0\n", counter.getCommand());
    }

    @Test
    public void testSetCounterDecrement() {
        Counter counter = Counter.builder().counterNumber(2).step(-1).build();

        Assertions.assertEquals(2, counter.getCounterNumber().intValue());
        Assertions.assertEquals(-1, counter.getStep().intValue());

        Assertions.assertEquals("SET COUNTER @2 -1\n", counter.getCommand());
    }

    @Test
    public void testSetCounterIncrement_2() {
        Counter counter = Counter.builder().counterNumber(2).step(1).build();

        Assertions.assertEquals(2, counter.getCounterNumber().intValue());
        Assertions.assertEquals(1, counter.getStep().intValue());

        Assertions.assertEquals("SET COUNTER @2 +1\n", counter.getCommand());
    }

    @Test
    public void testSetCounterIncrement_3() {
        Counter counter = Counter.builder().counterNumber(2).step(+1).build();

        Assertions.assertEquals(2, counter.getCounterNumber().intValue());
        Assertions.assertEquals(1, counter.getStep().intValue());

        Assertions.assertEquals("SET COUNTER @2 +1\n", counter.getCommand());
    }
}
