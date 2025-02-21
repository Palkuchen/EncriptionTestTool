package de.Palkuchen.algorythm.keyGeneration.generator;

import de.Palkuchen.algorythm.keyGeneration.IGenerator;

import java.util.ArrayList;
import java.util.Random;

public class MoGenerator implements IGenerator {

    public MoGenerator() {}

    @Override
    public ArrayList<ArrayList<Byte>> getKeys(ArrayList<Byte> key, int keyLength, int rounds) {
        ArrayList<ArrayList<Byte>> result = new ArrayList<>();
        for (int i = 0; i < rounds; i++) {
            ArrayList<Byte> list = generateKey(key, keyLength, i);
            result.add(list);
        }
        return result;
    }

    public ArrayList<Byte> generateKey(ArrayList<Byte> key, int keyLength, int round) {
       ArrayList<Byte> expandedKey = new ArrayList<>();
       while (expandedKey.size()<keyLength) {
           for (Byte by : key) {
               expandedKey.add((byte) (by*round));
           }
       }
       return expandedKey;
    }
}
