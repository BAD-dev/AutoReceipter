package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Julian on 4/18/2016.
 */
public class FileIO {

    private static FileHandle file;
    public enum STORAGE {LOCAL, EXTERNAL}

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
    public FileIO(String fpath, STORAGE storage) {
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
        String backup;
        if(file.exists())
            backup = readFile();

        String strToWrite = "[INVENTORY]\n";
        for(FridgeItem o : items) {
            strToWrite += "item_name " + o.name + "\n";
            strToWrite += "quantity " + o.quantity + "\n";
            strToWrite += "cost " + o.cost + "\n";
            strToWrite += "total_quantity " + o.totalQuantity + "\n";
        }

        strToWrite += "END";

        writeFile(strToWrite, false);
    }

    //
    public ArrayList<FridgeItem> loadItems() {
        ArrayList<FridgeItem> temp = new ArrayList<FridgeItem>();
        String strToRead = readFile();
        Scanner in = new Scanner(strToRead);

        String str = in.next();
        while(!str.equalsIgnoreCase("END")) {
            FridgeItem o = new FridgeItem();
            if(str.equalsIgnoreCase("[INVENTORY]"))
                continue;
            else if(str.equalsIgnoreCase("item_name")) {
                String name = in.next();
                o.setName(name);
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
                Date d = new Date(date);
                o.setDate(d);
            }
            else {
                System.out.println("Unknown input: " +str);
            }
            temp.add(o);
        }

        return temp;
    }

    public String readFile() {
        if(file.exists())
            return file.readString();
        else
            return "File does not exist!";
    }
    
    public void writeFile(String stringToWrite, boolean append) {
        if(file.exists()) {
            file.writeString(stringToWrite, append);
        }
        else {
            System.out.println("File does not exist!");
        }
    }
}
