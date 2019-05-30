package com.connectgroup;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataFiltererTest {

    @Test
    public void willReturnEmptyListFromEmptyFile() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }

    @Test
    public void willReturnWholeSingleLineFileIfCountryFilterNotProvided() throws FileNotFoundException {
        String[] myArray = { "1431592497", "GB", "200" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "").size() == 1);
        assertEquals(expected, DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), ""));
    }

    @Test
    public void willReturnWholeMultiLineFileIfCountryFilterNotProvided() throws FileNotFoundException {
        String[] myArray = { "1433190845", "US", "539", "1432917066", "GB", "37", "1433666287", "US", "789", "1432484176", "US", "850", "1432364076", "DE", "415" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "").size() > 1);
        assertEquals(expected, DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), ""));
    }

    @Test
    public void willReturnRowsFromAMultilineFileFilteredByCountry() throws FileNotFoundException {
        String[] myArray = { "1433190845", "US", "539", "1433666287", "US", "789", "1432484176", "US", "850" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));;
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "US").size() > 1);
        assertEquals(expected, DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "US"));
    }

    @Test
    public void willReturnRowsFromSingleLineFileFilteredByCountry() throws FileNotFoundException {
        String[] myArray = { "1431592497", "GB", "200" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "GB").size() == 1);
        assertEquals(expected, DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "GB"));
    }

    @Test
    public void willReturnEmptyListIfCountryNotListedInLogsFromSingleLineFile() throws FileNotFoundException  {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "US").isEmpty());
    }

    @Test
    public void willReturnEmptyListIfCountryNotListedInLogsFromMultiLineFile() throws FileNotFoundException  {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "IT").isEmpty());
    }

    @Test
    public void willReturnEmptyListFromEmptyLogFileFilteredByCountryAndResponseTimeLimit() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/empty"), "GB", 10).isEmpty());
    }

    @Test
    public void willReturnRowsFromMultiLineFileFilteredByCountryAndResponseTimeLimit() throws FileNotFoundException {
        String[] myArray = { "1433666287", "US", "789", "1432484176", "US", "850" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));
        assertEquals(expected, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 550));
    }

    @Test
    public void willReturnRowsFromSingleLineFileFilteredByCountryAndResponseTimeLimit() throws FileNotFoundException {
        String[] myArray = { "1431592497", "GB", "200" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));
        assertEquals(expected, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "GB", 150));
    }

    @Test
    public void  willReturnTheWholeLogFileFromSingleLineFileFilteredByCountryAndResponseTimeLimitZero() throws FileNotFoundException {
        String[] myArray = { "1431592497", "GB", "200" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));
        assertEquals(expected, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "GB", 0));
    }

    @Test
    public void willReturnRowsFromMultiLineFileFilteredByCountryAndResponseTimeZero() throws FileNotFoundException {
        String[] myArray = { "1433190845", "US","539", "1433666287", "US", "789", "1432484176", "US", "850" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));
        assertEquals(expected, DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 0));
    }

    @Test
    public void willReturnEmptyListFromSingleFileFilteredByCountryWithResponseTimeAboveLimit() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "GB", 200).isEmpty());
    }

    @Test
    public void willReturnEmptyListFromMultiLineFileFilteredByCountryWithResponseTimeAboveLimit() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "GB", 850).isEmpty());
    }

    @Test
    public void willReturnEmptyListFromEmptyFileFilteredByCountryAndResponseTimeAboveAverage() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/empty")).isEmpty());
    }

    @Test
    public void willReturnRowsFromMultiLineFileFilteredByCountryAndResponseTimeAboveAverage() throws FileNotFoundException {
        String[] myArray = { "1433190845", "US", "539", "1433666287", "US", "789", "1432484176", "US", "850" };
        Collection<?> expected = new ArrayList<>(Arrays.asList(myArray));
        assertEquals(expected,DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines")));
    }

    @Test
    public void willReturnEmptyListFromSingleLineFileFilteredByCountryAndResponseTimeAboveAverage() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/single-line")).isEmpty());
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
}
