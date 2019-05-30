package com.connectgroup;

public class Log {

    private long requestTimestamp;
    private String countryCode;
    private long responseTime;

    public Log(long requestTimestamp, String countryCode, long responseTime) {
        this.requestTimestamp = requestTimestamp;
        this.countryCode = countryCode;
        this.responseTime = responseTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public long getResponseTime() {
        return responseTime;
    }

    @Override
    public String toString() {
        return requestTimestamp + ", " + countryCode + ", " + responseTime + "";
    }
}
