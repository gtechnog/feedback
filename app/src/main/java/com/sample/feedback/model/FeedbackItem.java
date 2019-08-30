package com.sample.feedback.model;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class FeedbackItem {

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("dataMap")
    private DataMap dataMap;

    private Bitmap bitmap;
    private Uri imageUri;
    private CharSequence selectedChoice;
    private boolean isProvidingComment;
    private CharSequence comment;

    public FeedbackItem(String type, String id, String title, DataMap dataMap) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.dataMap = dataMap;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri image) {
        this.imageUri = image;
    }

    public CharSequence getSelectedChoice() {
        return selectedChoice;
    }

    public void setSelectedChoice(CharSequence selectedChoice) {
        this.selectedChoice = selectedChoice;
    }

    public boolean isProvidingComment() {
        return isProvidingComment;
    }

    public void setProvidingComment(boolean providingComment) {
        isProvidingComment = providingComment;
    }

    public CharSequence getComment() {
        return comment;
    }

    public void setComment(CharSequence comment) {
        this.comment = comment;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackItem that = (FeedbackItem) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(dataMap, that.dataMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, title, dataMap);
    }
}
