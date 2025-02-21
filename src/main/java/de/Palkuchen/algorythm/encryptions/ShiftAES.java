package de.Palkuchen.algorythm.encryptions;

import de.Palkuchen.algorythm.keyGeneration.IGenerator;
import de.Palkuchen.algorythm.keyGeneration.generator.JeditsGenerator;
import de.Palkuchen.algorythm.Encryption;

import java.util.ArrayList;

public class ShiftAES extends Encryption {

    public ShiftAES(IGenerator generator) {
        super(generator);
    }

    @Override
    public ArrayList<Byte> encrypt(String input, String startKey, int rounds) {
        ArrayList<Byte> list = getByteList(input);
        ArrayList<ArrayList<Byte>> keys = generator.getKeys(getByteList(startKey), list.size(), rounds);
        for (int i = 0; i < rounds; i++) {
            list = xOrByteList(list, keys.get(i));
            list = shiftList(list);
        }
        return list;
    }

    @Override
    public ArrayList<Byte> decipher(String input, String startKey, int rounds) {
        ArrayList<Byte> list = getByteList(input);
        try {
            ArrayList<ArrayList<Byte>> keys = generator.getKeys(getByteList(startKey), list.size(), rounds);
            for (int i = rounds-1; i >= 0; i--) {
                list = shiftList(list);
                list = xOrByteList(list, keys.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Byte> shiftList(ArrayList<Byte> list) {
        ArrayList<Byte> result = new ArrayList<>();
        for (int i = list.size()/2; i < list.size(); i++) {
            result.add(0, list.get(i));
        }
        for (int i = 0; i < list.size()/2; i++) {
            result.add(0, list.get(i));
        }
        return result;
    }
}

