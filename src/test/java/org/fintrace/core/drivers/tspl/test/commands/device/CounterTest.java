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
package org.fintrace.core.drivers.tspl.test.commands.device;

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
