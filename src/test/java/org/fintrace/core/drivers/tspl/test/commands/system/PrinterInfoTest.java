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

import org.fintrace.core.drivers.tspl.commands.system.PrinterInfo;
import org.fintrace.core.drivers.tspl.commands.system.PrinterInfoPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class PrinterInfoTest {
    private PrinterInfo printerInfo;

    @BeforeEach
    public void initialize() {
        printerInfo = PrinterInfo.builder().build();
    }

    @Test
    public void testDefaultInfo() {
        Assertions.assertEquals("SELFTEST\n", printerInfo.getCommand());
    }

    @Test
    public void testPattern() {
        printerInfo.setPage(PrinterInfoPage.PATTERN);
        Assertions.assertEquals("SELFTEST PATTERN\n", printerInfo.getCommand());
    }

    @Test
    public void testEthernet() {
        printerInfo.setPage(PrinterInfoPage.ETHERNET);
        Assertions.assertEquals("SELFTEST ETHERNET\n", printerInfo.getCommand());
    }

    @Test
    public void testRS232() {
        printerInfo.setPage(PrinterInfoPage.RS232);
        Assertions.assertEquals("SELFTEST RS232\n", printerInfo.getCommand());
    }

    @Test
    public void testSystem() {
        printerInfo.setPage(PrinterInfoPage.SYSTEM);
        Assertions.assertEquals("SELFTEST SYSTEM\n", printerInfo.getCommand());
    }

    @Test
    public void testWLan() {
        printerInfo.setPage(PrinterInfoPage.WLAN);
        Assertions.assertEquals("SELFTEST WLAN\n", printerInfo.getCommand());
    }

    @Test
    public void testZ() {
        printerInfo.setPage(PrinterInfoPage.Z);
        Assertions.assertEquals("SELFTEST Z\n", printerInfo.getCommand());
    }
}
