package de.Palkuchen.algorythm;

import java.util.ArrayList;

public interface ICrypt {
    public ArrayList<Byte> encrypt(String input, String startKey, int rounds);
    public ArrayList<Byte> decipher(String input, String startKey, int rounds);
}
