package de.Palkuchen.algorythm.keyGeneration;

import java.util.ArrayList;

public interface IGenerator {

    public ArrayList<ArrayList<Byte>> getKeys(ArrayList<Byte> key, int keyLength, int rounds);

}
