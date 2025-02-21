package de.Palkuchen.testing;

import de.Palkuchen.algorythm.Encryption;
import de.Palkuchen.util.Timer;

import java.util.ArrayList;

import static de.Palkuchen.util.Utilities.getRandomPlainText;
import static de.Palkuchen.util.Utilities.getRandomPlainTexts;

public class Tester {

    public static final int RUNS = 100000;
    public static final int BYTES = 5; // Test Key length
    public static final int ROUNDS = 8; // encryption rounds
    String testKey;
    String testText;

    public Tester(String testText) {
        testKey = getRandomPlainText(BYTES);
        this.testText = testText;
    }

    public void test(Encryption encryption) {
        System.out.println("Original Text: " + testText);
        ArrayList<Byte> encrypted = encryption.encrypt(testText, testKey, ROUNDS);
        String encryptedText = encryption.getTextFromByteList(encrypted);
        System.out.println("Encrypted Text: " + encryptedText);
        ArrayList<Byte> decrypted = encryption.decipher(encryptedText, testKey, ROUNDS);
        String decryptedText = encryption.getTextFromByteList(decrypted);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public void testBruteforceabillity(Encryption encryption) {
        ArrayList<Byte> cryptedBytes = encryption.encrypt(testText, testKey, ROUNDS);
        String cryptedText = encryption.getTextFromByteList(cryptedBytes);
        Bruteforce bruteforce = new Bruteforce(BYTES,ROUNDS,cryptedText, testText, testKey);
        bruteforce.bruteForce(encryption);
    }

    public void testTimeEfficency(Encryption encryption) {
        ArrayList<String> plainTexts = getRandomPlainTexts(RUNS, BYTES);
        Thread thread = new Thread(() -> {
            Timer timer = new Timer().run();
            for (String text : plainTexts) {
                encryption.encrypt(text, testKey, ROUNDS);
            }
            double timePerRound = timer.getStartDiff()/(double)plainTexts.size();
            System.out.println("Die benötigte Zeit betrug:" + timer.getStartDiff() +
                "Milliseconds" +timePerRound +  "ms/run)"+ encryption.getClass().getName());
        });
        thread.start();
    }
}
