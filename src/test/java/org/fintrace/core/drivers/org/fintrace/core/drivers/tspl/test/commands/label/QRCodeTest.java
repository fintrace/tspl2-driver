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
package org.fintrace.core.drivers.org.fintrace.core.drivers.tspl.test.commands.label;

import org.fintrace.core.drivers.tspl.commands.label.BarcodeRotation;
import org.fintrace.core.drivers.tspl.commands.label.ErrorCorrectionLevel;
import org.fintrace.core.drivers.tspl.commands.label.QRCode;
import org.fintrace.core.drivers.tspl.commands.label.QREncodeMode;
import org.fintrace.core.drivers.tspl.commands.label.QRModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class QRCodeTest {

    @Test
    public void qrCodeTest() {
        String qrStr = "QRCODE 10,10,H,4,A,0,\"ABCabc123\"\n";

        QRCode qrCode = QRCode.builder()
                .xCoordinate(10)
                .yCoordinate(10)
                .errorCorrectionLevel(ErrorCorrectionLevel.H)
                .cellWidth(4)
                .mode(QREncodeMode.A)
                .rotation(BarcodeRotation.NO_ROTATION)
                .content("ABCabc123").build();

        Assertions.assertEquals(qrStr, qrCode.getCommand());
    }

    @Test
    public void qrCodeTest_2() {
        String qrStr = "QRCODE 310,310,M,4,A,0,M2,\"ABCabc123\"\n";
        QRCode qrCode = QRCode.builder()
                .xCoordinate(310)
                .yCoordinate(310)
                .errorCorrectionLevel(ErrorCorrectionLevel.M)
                .cellWidth(4)
                .mode(QREncodeMode.A)
                .rotation(BarcodeRotation.NO_ROTATION)
                .model(QRModel.M2)
                .content("ABCabc123").build();

        Assertions.assertEquals(qrStr, qrCode.getCommand());
    }

    @Test
    public void qrCodeTest_3() {
        String qrStr = "QRCODE 160,120,H,10,A,0,J5,X100,\"123456789\"\n";

        QRCode qrCode = QRCode.builder()
                .xCoordinate(160)
                .yCoordinate(120)
                .errorCorrectionLevel(ErrorCorrectionLevel.H)
                .cellWidth(10)
                .mode(QREncodeMode.A)
                .rotation(BarcodeRotation.NO_ROTATION)
                .justification(5)
                .area(100)
                .content("123456789").build();

        Assertions.assertEquals(qrStr, qrCode.getCommand());
    }
}
