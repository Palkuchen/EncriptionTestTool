package de.Palkuchen.util;

import java.util.ArrayList;
import java.util.Random;

public class Utilities {
    public static ArrayList<String> getRandomPlainTexts(int amount, int length) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            result.add(getRandomPlainText(length));
        }
        return result;
    }

    public static String getRandomPlainText(int length) {
        String randString = "";
        for (int i = 0; i < length; i++) {
            randString += (char) (new Random().nextInt(26)+97);
        }
        return randString;
    }
}
