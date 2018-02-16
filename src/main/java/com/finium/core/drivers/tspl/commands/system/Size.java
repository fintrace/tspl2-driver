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
package com.finium.core.drivers.tspl.commands.system;

import com.finium.core.drivers.tspl.commands.TSPLCommand;
import com.finium.core.drivers.tspl.exceptions.LabelParserException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.US_ASCII;


/**
 * This command defines the label width and length<br>
 * <p>
 * <b>Syntax</b><br>
 * English system (inch): SIZE m,n <br>
 * Metric system (mm): SIZE m mm,n mm <br>
 * Dot measurement: SIZE m dot,n dot <br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@NoArgsConstructor
@Data
public class Size implements TSPLCommand<byte[]> {
    /**
     * Label width (mm)
     */
    private Integer labelWidth;

    /**
     * Label length (mm)
     */
    private Integer labelLength;

    /**
     * @param labelWidth
     * @param labelLength
     */
    public Size(Integer labelWidth, Integer labelLength) {
        if (labelWidth == null || labelLength == null) {
            throw new LabelParserException("ParseException SIZE Command: label width and label length should be specified");
        }

        this.labelWidth = labelWidth;
        this.labelLength = labelLength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (labelWidth == null || labelLength == null) {
            throw new LabelParserException("ParseException SIZE Command: label width and label length should be specified");
        }

        return (SystemCommand.SIZE.name() + " "
                + labelWidth + " mm," + labelLength
                + " mm\n").getBytes(US_ASCII);
    }
}
