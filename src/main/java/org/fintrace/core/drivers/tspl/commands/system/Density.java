/*
 * Copyright 2017 Finium Solutions
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
package org.fintrace.core.drivers.tspl.commands.system;

import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * This command sets the printing darkness.<br>
 * <p>
 * <b>Syntax</b><br>
 * DENSITY n<br>
 * <blockquote>n 0~15<br>
 * 0: specifies the lightest level<br>
 * 15: specifies the darkest leve</blockquote>
 * <p>
 * <b><i>NOTE: </i></b>Default printer DENSITY setting is 8.
 *
 * @author Venkaiah Chowdary Koneru
 */
@NoArgsConstructor
@Data
public class Density implements TSPLCommand<byte[]> {

    /**
     * printer darkness
     */
    private Integer darkness;

    /**
     * @param darkness printer darkness
     */
    public Density(Integer darkness) {
        if (darkness == null) {
            throw new LabelParserException("ParseException DENSITY Command: "
                    + "darkness can't be empty");
        }
        this.darkness = darkness;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (darkness == null) {
            throw new LabelParserException("ParseException DENSITY Command: "
                    + "darkness can't be empty");
        }

        return (SystemCommand.DENSITY.name() + " " + darkness + "\n").getBytes(US_ASCII);
    }
}
