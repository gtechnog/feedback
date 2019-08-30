package com.sample.feedback.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DiskImageUtils {

    @Nullable
    public static File createImageFileName(@NonNull File storageDir) {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File image = null;
        try {
            image = File.createTempFile(imageFileName,".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static boolean deleteFileFromDisk(@NonNull String path) {
        File file = new File(path);
        return file.delete();
    }
}
