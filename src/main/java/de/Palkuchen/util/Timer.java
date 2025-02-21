package de.Palkuchen.util;

public class Timer {
    long startTime;
    long roundTime;

    public Timer run() {
        startTime = System.currentTimeMillis();
        roundTime = System.currentTimeMillis();
        return this;
    }

    public long getStartDiff() {
        return System.currentTimeMillis()-startTime;
    }

    public long round() {
        long diff = System.currentTimeMillis()-roundTime;
        roundTime = System.currentTimeMillis();
        return diff;
    }
}
