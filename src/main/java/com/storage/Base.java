package com.storage;

public class Base {
    public static String generateTestData(String startData) {
        long unixTime = System.currentTimeMillis();
        return startData + "_" + unixTime;
    }
}
