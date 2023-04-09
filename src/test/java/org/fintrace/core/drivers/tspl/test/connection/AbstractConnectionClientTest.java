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
package org.fintrace.core.drivers.tspl.test.connection;

import lombok.extern.slf4j.Slf4j;
import org.fintrace.core.drivers.tspl.connection.TSPLConnectionClient;
import org.fintrace.core.drivers.tspl.connection.USBConnectionClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j public class AbstractConnectionClientTest {
    short vendorId;
    byte[] bytes;

    TSPLConnectionClient client = new USBConnectionClient(vendorId) {
        @Override protected void send(byte[] bs) { bytes = bs; }
    };

    @Test public void checkCharset(){
        Assertions.assertEquals(US_ASCII, client.getCharset());
        client.setCharset(UTF_8);
        Assertions.assertEquals(UTF_8, client.getCharset());
        client.send("ë");
        Assertions.assertArrayEquals(bytes, new byte[]{(byte) 0xC3, (byte) 0xAB});
        String recover = new String(bytes, UTF_8);
        Assertions.assertEquals(recover, "ë");
    }

}
