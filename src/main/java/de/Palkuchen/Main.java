package de.Palkuchen;

import de.Palkuchen.algorythm.encryptions.AES;
import de.Palkuchen.algorythm.encryptions.ShiftAES;
import de.Palkuchen.algorythm.keyGeneration.generator.JeditsGenerator;
import de.Palkuchen.algorythm.keyGeneration.generator.MoGenerator;
import de.Palkuchen.testing.Tester;

public class Main {
    public static void main(String[] args) {
        String text = "Whalla Krise Kinder essen Menschen";
        Tester tester = new Tester(text);
        // tester.testTimeEfficency(new ShiftAES());
        // tester.testTimeEfficency(new AES());
        tester.testBruteforceabillity(new AES(new MoGenerator()));
        // tester.test(new ShiftAES(new MoGenerator()));
    }
}