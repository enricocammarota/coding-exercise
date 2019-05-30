package com.connectgroup;

import java.util.List;

public class AverageCalculator {

    public static long calculateAverage(List<Log> logs) {
        long sum = 0;
        if(logs.size() > 0) {
            for (Log log : logs) {
                sum += log.getResponseTime();
            }
            return sum/logs.size();
        }
        return 0;
    }
}
