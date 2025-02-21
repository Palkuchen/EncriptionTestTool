package de.Palkuchen.algorythm;

import de.Palkuchen.algorythm.keyGeneration.IGenerator;

import java.util.ArrayList;
import java.util.List;

public class Encryption implements ICrypt {

    protected IGenerator generator;

    public Encryption(IGenerator generator) {
        this.generator = generator;
    }

    public ArrayList<Byte> xOrByteList(List<Byte> list, ArrayList<Byte> key) {
        ArrayList<Byte> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            byte txt = list.get(i);
            byte keyByte = key.get(i);
            byte crypted = (byte) (txt ^ (keyByte));
            result.add(crypted);
        }
        return result;
    }

    public static ArrayList<Byte> getByteList(String input) {
        ArrayList<Byte> byteList = new ArrayList<>();
        for (char c : input.toCharArray()) {
            byteList.add((byte) c);
        }
        return byteList;
    }

    public String getTextFromByteList(List<Byte> list) {
        String result = "";
        for (Byte b : list) {
            if (b < 0) {
                result += (char) (b+256);
            } else {
                result += (char) (b.byteValue());
            }
        }
        return result;
    }

    @Override
    public ArrayList<Byte> encrypt(String input, String startKey, int rounds) {
        return null;
    }

    @Override
    public ArrayList<Byte> decipher(String input, String startKey, int rounds) {
        return null;
    }

    public IGenerator getGenerator() {
        return generator;
    }
}
