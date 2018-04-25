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
package org.fintrace.core.drivers.tspl.test.commands.label;

import org.fintrace.core.drivers.tspl.commands.label.Bar;
import org.fintrace.core.drivers.tspl.exceptions.LabelParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class BarTest {
    private Bar bar;

    @BeforeEach
    public void initialize() {
        this.bar = Bar.builder().build();
    }

    @Test
    public void testDefault() {
        LabelParserException exception = Assertions.assertThrows(LabelParserException.class,
                () -> bar.getCommand());
        Assertions.assertEquals("BAR: x and y positions are required", exception.getMessage());
    }

    @Test
    public void testNullY() {
        bar.setXCoordinate(80);
        LabelParserException exception = Assertions.assertThrows(LabelParserException.class,
                () -> bar.getCommand());
        Assertions.assertEquals("BAR: x and y positions are required", exception.getMessage());
    }

    @Test
    public void testNullWidth() {
        bar.setXCoordinate(80);
        bar.setYCoordinate(80);
        LabelParserException exception = Assertions.assertThrows(LabelParserException.class,
                () -> bar.getCommand());
        Assertions.assertEquals("BAR: width is required", exception.getMessage());
    }

    @Test
    public void testNullHeight() {
        bar.setXCoordinate(80);
        bar.setYCoordinate(80);
        bar.setWidth(300);
        LabelParserException exception = Assertions.assertThrows(LabelParserException.class,
                () -> bar.getCommand());
        Assertions.assertEquals("BAR: height is required", exception.getMessage());
    }

    @Test
    public void testBar() {
        bar.setXCoordinate(80);
        bar.setYCoordinate(80);
        bar.setWidth(300);
        bar.setHeight(100);
        Assertions.assertEquals("BAR 80,80,300,100\n", bar.getCommand());
    }
}
