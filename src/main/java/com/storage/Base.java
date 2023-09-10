package com.storage;

public class Base {
    private static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    public static String getBaseUrl(){
        return BASE_URL;
    }

    public static String getContentType(){
        return CONTENT_TYPE;
    }

    public static String getApplicationJson(){
        return APPLICATION_JSON;
    }

    public static String generateTestData(String startData) {
        long unixTime = System.currentTimeMillis();
        return startData + "_" + unixTime;
    }
}
