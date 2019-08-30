package com.sample.feedback.managers;

import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AssetsManager {

    private static final String FEEDBACK_FORM_FILE_NAME = "feedback_form.json";
    private AssetManager mAssetManager;

    public AssetsManager(@NonNull AssetManager assetManager) {
        mAssetManager = assetManager;
    }

    public String getFeedbackString() {
        String jsonString = "";
        try {
            InputStream inputStream = mAssetManager.open(FEEDBACK_FORM_FILE_NAME);
            int size = inputStream.available();
            byte[] buffer = new byte[size];

            if (inputStream.read(buffer) != -1) {
                inputStream.close();
                jsonString = new String(buffer, StandardCharsets.UTF_8);
            } else {
                inputStream.close();
                throw new Exception(" No data read from file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
