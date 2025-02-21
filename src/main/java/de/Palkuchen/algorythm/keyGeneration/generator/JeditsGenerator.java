package de.Palkuchen.algorythm.keyGeneration.generator;

import de.Palkuchen.algorythm.keyGeneration.IGenerator;

import java.util.ArrayList;

public class JeditsGenerator implements IGenerator {

    public JeditsGenerator(){}

    public ArrayList<ArrayList<Byte>> getKeys(ArrayList<Byte> key, int keyLength, int rounds) {
        ArrayList<ArrayList<Byte>> keys = new ArrayList<>();
        ArrayList<Byte> subKey = new ArrayList<>();
        for (int i = 0; i < (key.size() - key.get(1) % key.size()); i++) {
            subKey.add((byte) (key.get(i) ^ key.get((key.size()-1) - i)));
        }
        ArrayList<Byte> roundKey = new ArrayList<>();
        for (int i = 0; i < keyLength*(rounds+1); i++) {
            if(i % keyLength == 0 && i != 0) {
                keys.add(roundKey);
                roundKey = new ArrayList<>();
            }
            roundKey.add((byte) (subKey.get(i % subKey.size()) ^ key.get(key.size() - 1 - (i % key.size()))));
        }
        return keys;
    }
}
