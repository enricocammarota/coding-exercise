package com.connectgroup;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class AverageCalculatorTest {

    @Test
    public void shouldReturnTheAverageForAGivenListOfLogObjects() throws FileNotFoundException {
        List<Log> logList = new ArrayList<>();
        Log log1 = new Log(new Random().nextLong(), "GB", 100);
        Log log2 = new Log(new Random().nextLong(), "GB", 200);
        Log log3 = new Log(new Random().nextLong(), "GB", 300);

        logList.add(log1);
        logList.add(log2);
        logList.add(log3);
        assertEquals(200, AverageCalculator.calculateAverage(logList));
    }

    @Test
    public void shouldReturnZeroIfTheInputListIsEmpty() throws FileNotFoundException {
        List<Log> logList = new ArrayList<>();
        assertEquals(0, AverageCalculator.calculateAverage(logList));
    }
}
