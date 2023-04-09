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
