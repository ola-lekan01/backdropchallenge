package com.backdrop.utils;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class StringUtils {

    private static final LevenshteinDistance distance = new LevenshteinDistance();

    public static int getLevenshteinDistance(String accountName, String payStackName){
        return distance.apply(accountName, payStackName);
    }
}