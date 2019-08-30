package com.sample.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentTransaction;

import com.sample.feedback.constants.StringConstants;
import com.sample.feedback.model.FeedbackItem;
import com.sample.feedback.model.FeedbackItemType;
import com.sample.feedback.utils.DiskImageUtils;

import java.io.File;
import java.util.Objects;

public class FeedbackActivity extends AppCompatActivity implements FeedbackListFragment.OnListItemClickListener {

    private static final String TAG = FeedbackActivity.class.getSimpleName();
    private static final String LIST_FRAGMENT_TAG = "list_fragment_tag";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private FeedbackItem currentFeedbackItem;
    private int currentPosition;
    private String currentImagePath;

    private FeedbackListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        listFragment = FeedbackListFragment.newInstance();
        fragmentTransaction.add(R.id.root_container, listFragment, LIST_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feeback_menu, menu);
        return true;
    }

    @Override
    public void onListItemClicked(FeedbackItem item, FeedbackItemType itemType, int position) {
        if (itemType == FeedbackItemType.TYPE_PHOTO) {
            currentFeedbackItem = item;
            currentPosition = position;
            if (item.getImageUri() != null) {
                dispatchViewPictureIntent();
            } else {
                dispatchTakePictureIntent();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File imgFile = new  File(currentImagePath);
            if(imgFile.exists())            {
                currentFeedbackItem.setImageUri(Uri.fromFile(imgFile));
                currentFeedbackItem.setBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
                listFragment.refreshItemInList(currentPosition);
            }
        }
    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            // Create the File where the photo should go
            File photoFile = null;
            photoFile = DiskImageUtils.createImageFileName(Objects.requireNonNull(getExternalFilesDir(Environment.DIRECTORY_PICTURES)));

            // Continue only if the File was successfully created
            if (photoFile != null) {
                currentImagePath = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.sample.feedback.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void dispatchViewPictureIntent() {
        Intent viewPictureIntent = new Intent(this, PictureViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.BUNDLE_IMAGE_URI, currentFeedbackItem.getImageUri().getPath());
        bundle.putString(StringConstants.BUNDLE_SCREEN_TITLE, currentFeedbackItem.getTitle());
        viewPictureIntent.putExtras(bundle);
        startActivity(viewPictureIntent);
    }

    public void onSubmitClicked(MenuItem item) {
        Log.d(TAG, "onSubmitClicked:");
        listFragment.submitForm();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.form_submitted_alert));
        builder.setMessage(getResources().getString(R.string.alert_message));
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
