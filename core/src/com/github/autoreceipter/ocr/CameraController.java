package com.github.autoreceipter.ocr;

/**
 * Created by Georg on 4/16/2016.
 *
 * Used to Activate camera between subdirectories
 * with different permissions
 */
public abstract class CameraController {

   public String scannedImagePath;

   public abstract void TakePicture();
}
