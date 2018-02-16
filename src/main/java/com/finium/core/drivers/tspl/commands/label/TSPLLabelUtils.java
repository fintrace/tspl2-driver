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
package com.finium.core.drivers.tspl.commands.label;

import java.util.Map;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * @author Venkaiah Chowdary Koneru
 */
public final class TSPLLabelUtils {


    /**
     * private to prevent un-necessary instantiation
     */
    private TSPLLabelUtils() {
    }

    /**
     * parses the content and replaces any <<< >>> occurrences
     *
     * @param labelTemplate
     * @param parameters
     * @return
     */
    public static byte[] parseAndGetLabelContent(String labelTemplate, Map<String, String> parameters) {

        String localTemplate = labelTemplate;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            localTemplate = localTemplate.replaceAll("<<<" + entry.getKey() + ">>>", entry.getValue());
        }

        return localTemplate.getBytes(US_ASCII);
    }
}
