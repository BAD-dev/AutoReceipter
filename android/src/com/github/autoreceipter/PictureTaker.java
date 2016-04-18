package com.github.autoreceipter;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.badlogic.gdx.Gdx;
import com.github.autoreceipter.ocr.CameraController;
import com.github.autoreceipter.ocr.ImageProcessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Georg on 4/16/2016.
 */
public class PictureTaker extends CameraController {

    AndroidLauncher launcher;
    public static Uri asdfasdf;

    public PictureTaker(AndroidLauncher lnchr) {
        launcher = lnchr;
    }

    @Override
    public void TakePicture() {
        dispatchTakePictureIntent();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(launcher.getPackageManager()) != null) {

            File scannedPic = null;
            try {
                scannedPic = createImageFile();
            } catch (Exception e){
                e.printStackTrace();
                return;
            }

            if(scannedPic != null) {

                Uri pic = Uri.fromFile(scannedPic);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, pic);

                this.scannedImagePath = pic.getPath();

                launcher.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }

        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "ScannerCapture333.jpeg";

        File image = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile(), imageFileName);
       // File storageDir = launcher.getCacheDir();
       // File image = File.createTempFile(
       //         imageFileName,  /* prefix */
       //         ".png",         /* suffix */
       //         storageDir      /* directory */
        //);

        return image;
    }
}
