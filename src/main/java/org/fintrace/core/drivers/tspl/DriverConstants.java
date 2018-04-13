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
package org.fintrace.core.drivers.tspl;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * @author Venkaiah Chowdary Koneru
 */
public final class DriverConstants {

    /**
     * SET prefix for the device configuration commands
     */
    public static final String SET_PREFIX = "SET";

    public static final String STATUS_COMMAND_PREFIX = "!";

    public static final String COMMA = ",";

    public static final String EMPTY_SPACE = " ";

    public static final String LF = "\n";

    public static final String CR = "\r";

    public static final String CR_LF = "\r\n";

    public static final String ESCAPED_DOUBLE_QUOTE = "\"";

    public static final String UNIT_MM = "mm";

    public static final byte[] LF_BYTES = LF.getBytes(US_ASCII);
    public static final byte[] CR_BYTES = CR.getBytes(US_ASCII);
    public static final byte[] CR_LF_BYTES = CR_LF.getBytes(US_ASCII);

    /**
     * private to prevent un-necessary instantiation.
     */
    private DriverConstants() {
    }
}
