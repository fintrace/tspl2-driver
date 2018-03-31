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
     * @param msg Additional message regarding the error.
     * @param t   The exception/throwable that causing the error.
     */
    public ConnectionClientException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructor with message.
     *
     * @param msg The message about causing error.
     */
    public ConnectionClientException(String msg) {
        super(msg);
    }

    /**
     * Constructor with causing exception.
     *
     * @param t The exception/throwable that causing the error.
     */
    public ConnectionClientException(Throwable t) {
        super(t);
    }
}
