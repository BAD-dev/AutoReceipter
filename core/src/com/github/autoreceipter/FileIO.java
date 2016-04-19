package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Julian on 4/18/2016.
 */
public class FileIO {

    private static FileHandle file;
    private static String backup;
    public enum STORAGE {LOCAL, EXTERNAL, INTERNAL}

    public FileIO(String fpath) {
        if(Gdx.files.isLocalStorageAvailable()) {
            file = Gdx.files.local(fpath);
            System.out.println("Local path: " + file.path());
            if(!file.exists())
                file.writeString("", false);
        }

        else if(Gdx.files.isExternalStorageAvailable()) {
            System.out.println("External path: " + Gdx.files.getExternalStoragePath());
            file = Gdx.files.external(fpath);
            if(!file.exists())
                file.writeString("", false);
        }

        else {
            file = null;
            System.out.println("No storage available!");
        }
    }

    // Same constructor except with storage type defined. For testing purposes
    public FileIO() {

    }

    public void changeFilePath(String fpath, STORAGE storage) {
        if(storage == STORAGE.LOCAL && Gdx.files.isLocalStorageAvailable()) {
            file = Gdx.files.local(fpath);
            System.out.println("Local path: " + file.path());
            if(!file.exists())
                file.writeString("", false);
        }

        else if(storage == STORAGE.EXTERNAL && Gdx.files.isExternalStorageAvailable()) {
            System.out.println("External path: " + Gdx.files.getExternalStoragePath());
            file = Gdx.files.external(fpath);
            if(!file.exists())
                file.writeString("", false);
        }

        else if(storage == STORAGE.INTERNAL) {
            file = Gdx.files.internal(fpath);
            if(!file.exists())
                file.writeString("", false);
        }

        else {
            file = null;
            System.out.println("No storage available!");
        }
    }

    public String getFilePath() {
        if(file.exists())
            return file.path();
        else
            return "File does not exist!";
    }

    // Saves list of passed items to file
    public void saveItems(ArrayList<FridgeItem> items) {
        if(file.exists())
            backup = readFile();

        String strToWrite = "[INVENTORY]\n";
        for(FridgeItem o : items) {
            strToWrite += "item_name \"" + o.name + "\"\n";
            strToWrite += "quantity " + o.quantity + "\n";
            strToWrite += "cost " + o.cost + "\n";
            strToWrite += "total_quantity " + o.totalQuantity + "\n";
            strToWrite += "last_purchased " + o.getLastPurchased() + "\n";
            // add more fields
            // ...
            strToWrite += "END\n";
        }

        writeFile(strToWrite, false);
    }

    //
    public ArrayList<FridgeItem> loadItems(final AutoReceipter app) {
        ArrayList<FridgeItem> temp = new ArrayList<FridgeItem>();
        String strToRead = readFile();
        Scanner in = new Scanner(strToRead);
        int breakCount = 0;

        FridgeItem o = new FridgeItem();
        o.skin = app.skin;

        String str;
        boolean newItem = false;
        while(in.hasNext()) {
            str = in.next();
            System.out.println(str);

            if(++breakCount == 1000)
                break;

            if(newItem) {
                o = new FridgeItem();
                o.skin = app.skin;
                newItem = false;
            }

            if(str.equalsIgnoreCase("END")) {
                o.setWidget();
                temp.add(o);
                newItem = true;
                continue;
            }

            if(str.equalsIgnoreCase("[INVENTORY]")) {
                continue;
            }

            else if(str.equalsIgnoreCase("item_name")) {
                String name = findName(in);
                o.setItemName(name);
            }

            else if(str.equalsIgnoreCase("quantity")) {
                int q = in.nextInt();
                o.setQuantity(q);
            }

            else if(str.equalsIgnoreCase("cost")) {
                double cost = in.nextDouble();
                o.setCost(cost);
            }

            else if(str.equalsIgnoreCase("total_quantity")) {
                int tq = in.nextInt();
                o.setTotalQuantity(tq);
            }

            else if(str.equalsIgnoreCase("last_purchased")) {
                String date = in.next();
                Date d;
                try {
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    d = format.parse(date);
                } catch(ParseException e) {
                    throw new IllegalArgumentException();
                }
                o.setDate(d);
            }

            else {
                System.out.println("Unknown input: " + str);
            }
        }

        return temp;
    }

    private String findName(Scanner in) {
        String res = "";
        String cur = in.next();
        if(cur.startsWith("\"") && cur.length() > 1)
            cur = cur.substring(1);

        while(!cur.endsWith("\"")) {
            res += cur + " ";
            cur = in.next();
        }

        return res + cur.substring(0, cur.length()-1);
    }

    public String readFile() {
        if(file.exists())
            return file.readString();
        else
            return "File does not exist!";
    }
    
    public void writeFile(String stringToWrite, boolean append) {
        if(file.exists())
            file.writeString(stringToWrite, append);
        else
            System.out.println("File does not exist!");
    }
}
