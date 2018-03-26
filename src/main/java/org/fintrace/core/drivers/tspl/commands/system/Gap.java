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
package org.fintrace.core.drivers.tspl.commands.system;

import org.fintrace.core.drivers.tspl.commands.TSPLCommand;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * Defines the gap distance between two labels.<br>
 * <p>
 * <b>Syntax</b><br>
 * English system (inch): GAP m,n <br>
 * Metric system (mm): GAP m mm,n mm <br>
 * Dot measurement: GAP m dot,n dot <br>
 *
 * @author Venkaiah Chowdary Koneru
 */
@NoArgsConstructor
@Data
public class Gap implements TSPLCommand<byte[]> {

    /**
     * The gap distance between two labels
     */
    private Integer labelDistance;

    /**
     * The offset distance of the gap
     */
    private Integer labelOffsetDistance;

    /**
     * @param labelDistance
     * @param labelOffsetDistance
     */
    public Gap(Integer labelDistance, Integer labelOffsetDistance) {
        if (labelDistance == null || labelOffsetDistance == null) {
            throw new LabelParserException("ParseException GAP Command: "
                    + "label distance and label offset should be specified");
        }

        this.labelDistance = labelDistance;
        this.labelOffsetDistance = labelOffsetDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getCommand() throws UnsupportedEncodingException {
        if (labelDistance == null && labelOffsetDistance != null) {
            throw new LabelParserException("ParseException GAP Command: "
                    + "label distance and label offset should be specified");
        }

        return (SystemCommand.GAP.name() + " "
                + labelDistance + " mm," + labelOffsetDistance
                + " mm\n").getBytes(US_ASCII);
    }
}
