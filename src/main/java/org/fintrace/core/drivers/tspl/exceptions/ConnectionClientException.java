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
package org.fintrace.core.drivers.tspl.exceptions;

/**
 * Exception that will be thrown by the internal process of TSPL device client.
 *
 * @author Venkaiah Chowdary Koneru
 */
public class ConnectionClientException extends Exception {
    private static final long serialVersionUID = 2031498887869905758L;

    /**
     * Default constructor
     */
    public ConnectionClientException() {
        super();
    }

    /**
     * Constructor with message and possible causing exception
     *
     * @param arg0 Additional message regarding the error.
     * @param arg1 The exception/throwable that causing the error.
     */
    public ConnectionClientException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * Constructor with message.
     *
     * @param arg0 The message about causing error.
     */
    public ConnectionClientException(String arg0) {
        super(arg0);
    }

    /**
     * Constructor with causing exception.
     *
     * @param arg0 The exception/throwable that causing the error.
     */
    public ConnectionClientException(Throwable arg0) {
        super(arg0);
    }
}
