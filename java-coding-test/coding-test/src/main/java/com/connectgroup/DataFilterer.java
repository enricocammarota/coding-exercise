package com.connectgroup;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DataFilterer {

    public static Collection<?> filterByCountry(Reader source, String country) {

        List<Log> logsList = generateLogList(source);

        if(country != "") {
            return logsList.stream()
                    .filter(log -> log.getCountryCode().equals(country)).collect(Collectors.toList());
        } else {
            return logsList;
        }
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {

        List<Log> logsList = generateLogList(source);
        final List<Log> logs = logsList.stream()
                .filter(log -> log.getCountryCode().equals(country))
                .filter(log -> log.getResponseTime() > limit)
                .collect(Collectors.toList());

        return logs;
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {

        List<Log> logsList = generateLogList(source);
        long averageTimePerRequest = AverageCalculator.calculateAverage(logsList);

        final List<Log> logs = logsList.stream()
                .filter(log -> log.getResponseTime() > averageTimePerRequest)
                .collect(Collectors.toList());

        return logs;
    }

    private static List<Log> generateLogList(Reader source) {

        List<Log> logsList = new ArrayList<>();
        File tempFile = null;
        int intValueOfChar;

        try {

            // Create a temporary file to allow me the usage of Java streams
            tempFile = File.createTempFile("logs", ".tmp");
            Writer targetFileWriter = new FileWriter(tempFile.getAbsolutePath());

            StringBuilder buffer = new StringBuilder();
            while ((intValueOfChar = source.read()) != -1) {
                buffer.append((char) intValueOfChar);
            }

            // Writing the content of the Reader in the file
            targetFileWriter.write(buffer.toString());
            targetFileWriter.close();

            // Filtering the file content by country and limit and adding the log lines into the final list if the criteria are respected
            Files.lines(Paths.get(tempFile.getAbsolutePath())).forEach((l) -> {
                String[] logLine = l.split(",");
                if(!logLine[0].equals("REQUEST_TIMESTAMP"))
                    logsList.add(new Log(Long.valueOf(logLine[0]), logLine[1], Integer.valueOf(logLine[2])));
            } );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  logsList;
    }
}
