package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Julian on 4/18/2016.
 */
public class FileIO {

    private static FileHandle file;

    public FileIO(String fpath) {
        if(Gdx.files.isLocalStorageAvailable()) {
            file = Gdx.files.local(fpath);
            System.out.println("Local path: " + file.path());
            if(!file.exists())
                file.writeString("Local storage test", false);
        }

        else if(Gdx.files.isExternalStorageAvailable()) {
            System.out.println("External path: " + Gdx.files.getExternalStoragePath());
            file = Gdx.files.external(fpath);
            if(!file.exists())
                file.writeString("External storage test", false);
        }

        else {
            file = null;
            System.out.println("File not found!");
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
