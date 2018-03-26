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
package org.fintrace.core.drivers.tspl.exceptions;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class LabelParserException extends RuntimeException {
    private static final long serialVersionUID = -7032802716798268348L;
    private String message;

    /**
     *
     */
    public LabelParserException() {
        super();
    }

    /**
     * @param message
     */
    public LabelParserException(String message) {
        super(message);
        this.message = message;
    }
}
