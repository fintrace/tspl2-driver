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

import lombok.Builder;
import lombok.Data;
import org.fintrace.core.drivers.tspl.commands.TSPLCommand;

import static org.fintrace.core.drivers.tspl.DriverConstants.EMPTY_SPACE;
import static org.fintrace.core.drivers.tspl.DriverConstants.LF;
import static org.fintrace.core.drivers.tspl.commands.system.SystemCommand.SELFTEST;

/**
 * At this command, the printer will print out the printer information.
 * <p><b><i>Syntax</i></b><br>
 * SELFTEST [page]
 * </p>
 * <table border="0" valign="top">
 * <tr>
 * <th>Parameter</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>Page</td>
 * <td>
 * <b>omitted:</b> Print a self-test page with whole printer information.<br>
 * <b>PATTERN:</b> Print a pattern to check the status of print head heat line.<br>
 * <b>ETHERNET:</b> Print a self-test page with Ethernet settings.<br>
 * <b>WLAN:</b> Print a self-test page with Wi-Fi settings.<br>
 * <b>RS232:</b> Print a self-test page with RS-232 settings.<br>
 * <b>SYSTEM:</b> Print a self-test page with printer settings.<br>
 * <b>Z:</b> Print a self-test page with emulated language settings.<br>
 * </td>
 * </tr>
 * </table>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@Builder
public class SelfTest implements TSPLCommand {

    /**
     * if omitted, prints a self-test page with whole printer information.
     */
    private SelfTestPage page;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        StringBuilder commandBuilder = new StringBuilder(SELFTEST.name());
        if (page != null) {
            commandBuilder.append(EMPTY_SPACE)
                    .append(page.name());
        }

        commandBuilder.append(LF);

        return commandBuilder.toString();
    }
}
