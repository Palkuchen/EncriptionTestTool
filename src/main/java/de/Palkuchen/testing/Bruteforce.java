package de.Palkuchen.testing;

import de.Palkuchen.algorythm.Encryption;
import de.Palkuchen.util.Timer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Bruteforce {

    private DecimalFormat df = new DecimalFormat("###,###,###");
    private int keyLength;
    private int rounds;
    private String input;
    private String toSearch;
    private String originalKey;
    private static List<String> combinations;

    public Bruteforce(int keyLength, int rounds, String input, String toSearch, String originalKey) {
        this.keyLength = keyLength;
        this.rounds = rounds;
        this.input = input;
        this.toSearch = toSearch;
        this.originalKey = originalKey;

        if (combinations == null) {
            char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z'};
            combinations = generateCombinations(chars, keyLength);
            System.out.println("Key Loaded: " + combinations.size());
            System.out.println("Estimated Time: " + (combinations.size()/5E4) + "s");
        }
    }

    public void bruteForce(Encryption encryption) {
        startMessage(encryption);
        Thread thread = new Thread(() -> {
            ArrayList<String> success = new ArrayList<>();
            Timer timer = new Timer().run();
            int count = 0;
            for (String key : combinations) {
                String decrypted = encryption.getTextFromByteList(encryption.decipher(input, key, rounds));
                if (decrypted.equals(toSearch)) {
                    successMessage(count, timer, key, decrypted, success.size());
                    success.add(key);
                }
                count++;
            }
            System.out.println("All keys found: ");
            for (String key : success) {
                System.out.println(key);
            }
            System.out.println("Keys found: " + success.size());
        });
        thread.start();
    }

    public static List<String> generateCombinations(char[] chars, int length) {
        List<String> combinations = new ArrayList<>();
        int charakters = chars.length;
        int[] indices = new int[length];

        while (true) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(chars[indices[i]]);
            }
            combinations.add(sb.toString());

            int position = length - 1;
            while (position >= 0 && indices[position] == charakters - 1) {
                position--;
            }

            if (position < 0) {
                break;
            }

            indices[position]++;
            for (int i = position + 1; i < length; i++) {
                indices[i] = 0;
            }
        }

        return combinations;
    }

    public long avgTries() {
        double possibilities = Math.pow(26, keyLength);
        return Math.round(possibilities/2);
    }

    public void startMessage(Encryption encryption) {
        System.out.println("\nBruteforce started...");
        System.out.println("Generator used: " + encryption.getGenerator().getClass().getName());
        System.out.println("Text to find: " + toSearch);
        System.out.println("Key to be found: " + originalKey);
        System.out.println("Avg tries to solve: " + df.format(avgTries()));
    }

    public void successMessage(int count, Timer timer, String key, String text, int alreadyFound) {
        System.out.println("\nGEFUNDEN (" + alreadyFound + ")");
        System.out.println("Rounds: " + df.format(count));
        System.out.println("Time needed: " + timer.getStartDiff() + "ms");
        System.out.println("Estimated time till end: " + getEstimatedTimeTillEnd(count, timer) + "s");
        System.out.println("Avg. tries per ms: " + getTriesPerMs(count, timer));
        System.out.println("Key: " + key);
        System.out.println("Text: " + text);
        System.out.println();
    }

    public int getEstimatedTimeTillEnd(int count, Timer timer) {
        return (int) ((combinations.size()-count)/getTriesPerMs(count,timer)/1000);
    }

    public double getTriesPerMs(int count, Timer timer) {
        return (count/timer.getStartDiff());
    }

}
