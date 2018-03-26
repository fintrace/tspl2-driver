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
package org.fintrace.core.drivers.tspl.listeners;

import org.fintrace.core.drivers.tspl.connection.TSPLConnectionClient;
import org.fintrace.core.drivers.tspl.exceptions.ConnectionClientException;

/**
 * This listener provide a callback structure or listener pattern template for
 * monitoring the {@linkplain TSPLConnectionClient} data exchanges. The caller of any
 * method in this interface must be from an independent Thread.
 *
 * @author Venkaiah Chowdary Koneru
 */
public interface DataListener {

    /**
     * Invoked when the client have guaranteed a successful sending message to
     * the printer.
     *
     * @param message Message has been sent.
     */
    void messageSent(String message);

    /**
     * Invoked when the client has successfully received a message from the
     * printer.
     *
     * @param message The Message received.
     */
    void messageReceived(String message);

    /**
     * Invoked when client is sure that it has failed to send a message.
     * TsplConnectionClient will not try to 're-send' the data. client must
     * attempt to send them again.
     *
     * @param exception     The exception that causes the message sending failure.
     * @param messageToSend The message to be send when error occurred.
     */
    void messageSendFailed(ConnectionClientException exception, String messageToSend);
}
