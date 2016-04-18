package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

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
