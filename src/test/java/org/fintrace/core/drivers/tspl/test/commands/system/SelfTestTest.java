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
package org.fintrace.core.drivers.tspl.test.commands.system;

import org.fintrace.core.drivers.tspl.commands.system.SelfTest;
import org.fintrace.core.drivers.tspl.commands.system.SelfTestPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class SelfTestTest {
    private SelfTest selfTest;

    @BeforeEach
    public void initialize() {
        selfTest = SelfTest.builder().build();
    }

    @Test
    public void testDefaultInfo() {
        Assertions.assertEquals("SELFTEST\n", selfTest.getCommand());
    }

    @Test
    public void testPattern() {
        selfTest.setPage(SelfTestPage.PATTERN);
        Assertions.assertEquals("SELFTEST PATTERN\n", selfTest.getCommand());
    }

    @Test
    public void testEthernet() {
        selfTest.setPage(SelfTestPage.ETHERNET);
        Assertions.assertEquals("SELFTEST ETHERNET\n", selfTest.getCommand());
    }

    @Test
    public void testRS232() {
        selfTest.setPage(SelfTestPage.RS232);
        Assertions.assertEquals("SELFTEST RS232\n", selfTest.getCommand());
    }

    @Test
    public void testSystem() {
        selfTest.setPage(SelfTestPage.SYSTEM);
        Assertions.assertEquals("SELFTEST SYSTEM\n", selfTest.getCommand());
    }

    @Test
    public void testWLan() {
        selfTest.setPage(SelfTestPage.WLAN);
        Assertions.assertEquals("SELFTEST WLAN\n", selfTest.getCommand());
    }

    @Test
    public void testZ() {
        selfTest.setPage(SelfTestPage.Z);
        Assertions.assertEquals("SELFTEST Z\n", selfTest.getCommand());
    }
}
