package com.sample.feedback;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.feedback.constants.StringConstants;

import java.util.Objects;

public class PictureViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);

        String imagePath = Objects.requireNonNull(getIntent().getExtras()).getString(StringConstants.BUNDLE_IMAGE_URI);
        String title = getIntent().getExtras().getString(StringConstants.BUNDLE_SCREEN_TITLE);
        Uri imageUri = null;
        if (imagePath != null && !imagePath.isEmpty()) {
            imageUri = Uri.parse(imagePath);
        }

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageURI(imageUri);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
