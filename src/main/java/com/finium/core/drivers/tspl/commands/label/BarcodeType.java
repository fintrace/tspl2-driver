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

/**
 * code type<br>
 * <p>
 * <b><i>Note:</i></b><br>
 * TDP-643 Plus , TTP-243, TTP-342, TTP-244ME and TTP-342M models are
 * not supported MSI, MSIC, PLESSY, ITF14,
 * EAN14 and 11.<br>
 * TTP-248M model are not supported MSIC and 11.
 *
 * @author Venkaiah Chowdary Koneru
 */
public enum BarcodeType {

    /**
     * Code 128, switching code subset A, B, C automatically
     */
    CODE_128("128"),

    /**
     * Code 128, switching code subset A, B, C manually<br>
     * <p>
     * <b>Control code A B C</b>
     * 096 FNC3 FNC3 NONE<br>
     * 097 FNC2 FNC2 NONE<br>
     * 098 SHIFT SHIFT NONE<br>
     * 099 CODE C CODE C NONE<br>
     * 100 CODE B FNC4 CODE B<br>
     * 101 FNC4 CODE A CODE A<br>
     * 102 FNC1 FNC1 FNC1<br>
     * 103 Start (CODE A)<br>
     * 104 Start (CODE B)<br>
     * 105 Start (CODE C)<br>
     * Use “!” as a starting character for the control code followed
     * by three control codes. If the start subset
     * is not set, the default starting subset is B.
     */
    CODE_128M("128M"),

    /**
     * Code 128, switching code subset A, B, C automatically
     */
    EAN128("EAN128"),

    /**
     * Interleaved 2 of 5
     */
    CODE_25("25"),

    /**
     * Interleaved 2 of 5 with check digits
     */
    CODE_25C("25C"),

    /**
     * Code 39 full ASCII for TSPL2 printers<br>
     * Code 39 standard for TSPL printers<br>
     * Auto switch full ASCII and standard code 39 for PLUS models
     */
    CODE_39("39"),

    /**
     * Code 39 full ASCII with check digit for TSPL2 printers<br>
     * Code 39 standard with check digit for TSPL printers<br>
     * Auto switch full ASCII and standard code 39 for PLUS models
     */
    CODE_39C("39C"),

    /**
     * Code 39 standard for TSPL2 printers
     */
    CODE_39S("39S"),

    /**
     * Code 93
     */
    CODE_93("93"),

    /**
     * EAN 13
     */
    EAN13("EAN13"),

    /**
     * EAN 13 with 2 digits add-on
     */
    EAN13_2("EAN13+2"),

    /**
     * EAN 13 with 5 digits add-on
     */
    EAN13_5("EAN13+5"),

    /**
     * EAN 8
     */
    EAN8("EAN8"),

    /**
     * EAN 8 with 2 digits add-on
     */
    EAN8_2("EAN8+2"),

    /**
     * EAN 8 with 5 digits add-on
     */
    EAN8_5("EAN8+5"),

    /**
     * Codabar
     */
    CODA("CODA"),

    /**
     * Postnet
     */
    POST("POST"),

    /**
     * UPC-A
     */
    UPCA("UPCA"),

    /**
     * UPC-A with 2 digits add-on
     */
    UPCA_2("UPCA+2"),

    /**
     * UPC-A with 5 digits add-on
     */
    UPCA_5("UPCA+5"),

    /**
     * UPC-E
     */
    UPCE("UPCE"),

    /**
     * UPC-E with 2 digits add-on
     */
    UPCE_2("UPCE+2"),

    /**
     * UPC-E with 5 digits add-on
     */
    UPCE_5("UPCE+5"),

    /**
     * China post code
     */
    CPOST("CPOST"),

    /**
     * MSI code
     */
    MSI("MSI"),

    /**
     * MSI with check digit
     */
    MSIC("MSIC"),

    /**
     * PLESSEY code
     */
    PLESSEY("PLESSEY"),

    /**
     * ITF 14 code
     */
    ITF14("ITF14"),

    /**
     * EAN 14 code
     */
    EAN14("EAN14"),

    /**
     * 11
     */
    CODE11("11"),

    /**
     * Telepen code
     */
    TELEPEN("TELEPEN"),

    /**
     * Telepen code. Number only
     */
    TELEPENN("TELEPENN"),

    /**
     * Planet code
     */
    PLANET("PLANET"),

    /**
     * Code 49
     */
    CODE49("CODE49"),

    /**
     * Deutsche Post Identcode
     */
    DPI("DPI"),

    /**
     * Deutsche Post Leitcode
     */
    DPL("DPL");

    /**
     * barcode type.
     */
    private String codeType;

    /**
     * @param codeType
     */
    BarcodeType(String codeType) {
        this.codeType = codeType;
    }

    /**
     * @return
     */
    public String getCodeType() {
        return codeType;
    }
}
