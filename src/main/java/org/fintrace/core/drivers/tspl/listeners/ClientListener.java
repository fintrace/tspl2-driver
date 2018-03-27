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

/**
 * This listener provide a callback structure or listener pattern template for
 * monitoring the {@linkplain TSPLConnectionClient} statuses. The caller of any
 * method in this interface must be from an independent Thread.
 *
 * @author Venkaiah Chowdary Koneru
 */
public interface ClientListener {

    /**
     * This method will be invoked by client when a connection has successfully
     * established.
     *
     * @param client The client that has established the connection.
     */
    void connectionEstablished(TSPLConnectionClient client);

    /**
     * This method will be invoked by client when a connection has been dropped
     * successfully, intentionally or not.
     *
     * @param client The client that has dropped the connection.
     */
    void connectionLost(TSPLConnectionClient client);

    /**
     * This method will be invoked by client if it has experience IO exception
     * that is not recoverable. For example when network is down or USB removal, Client must
     * treat this as connection down and thus, it need to re-establish
     * connection.
     *
     * @param client The client that failed the connection.
     * @param e      Exception that it has encounter, most likely it will be an
     *               IOException.
     */
    void connectionIsFailing(TSPLConnectionClient client, Exception e);
}
