package com.demoqa.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitDemoTest {

    @Test
    void simpleTest() {
        Assertions.assertTrue(3 > 2);
    }

}
