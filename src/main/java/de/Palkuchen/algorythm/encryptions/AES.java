package de.Palkuchen.algorythm.encryptions;

import de.Palkuchen.algorythm.ICrypt;
import de.Palkuchen.algorythm.keyGeneration.IGenerator;
import de.Palkuchen.algorythm.keyGeneration.generator.JeditsGenerator;
import de.Palkuchen.algorythm.Encryption;

import java.util.ArrayList;

public class AES extends Encryption {

    public AES(IGenerator generator) {
        super(generator);
    }

    public ArrayList<Byte> encrypt(String input, String startKey, int rounds) {
        ArrayList<Byte> list = getByteList(input);
        ArrayList<ArrayList<Byte>> keys =
                generator.getKeys(getByteList(startKey), list.size(), rounds);
        for (int i = 0; i < rounds; i++) {
            list = xOrByteList(list, keys.get(i));
        }
        return list;
    }

    @Override
    public ArrayList<Byte> decipher(String input, String startKey, int rounds) {
        ArrayList<Byte> list = getByteList(input);
        ArrayList<ArrayList<Byte>> keys =
                generator.getKeys(getByteList(startKey), list.size(), rounds);
        for (int i = rounds-1; i >= 0; i--) {
            list = xOrByteList(list, keys.get(i));
        }
        return list;
    }

}

