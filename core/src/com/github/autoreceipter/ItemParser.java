package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

/**
 * Created by Julian on 4/18/2016.
 *
 * This is where FridgeItems are parsed from text file
 */
public class ItemParser {

    private AutoReceipter app;
    private static String str;

    public ItemParser(final AutoReceipter app) {
        this.app = app;
    }

    public ArrayList<FridgeItem> parse(String textToConvert) {
        ArrayList<FridgeItem> temp = new ArrayList<FridgeItem>();




        return temp;
    }

    public FridgeItem parseLine(String lineToConvert) {
        FridgeItem temp = new FridgeItem();



        return temp;
    }
}
