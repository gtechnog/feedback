package com.sample.feedback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import static com.sample.feedback.model.FeedbackItemType.TYPE_COMMENT;
import static com.sample.feedback.model.FeedbackItemType.TYPE_PHOTO;
import static com.sample.feedback.model.FeedbackItemType.TYPE_SINGLE_CHOICE;

class InflatedViewFactory {

    static View inflateViewForViewType(int viewType, @NonNull ViewGroup parent) {
        if (viewType == TYPE_PHOTO.getType()) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item_photo, parent, false);
        } else if (viewType == TYPE_SINGLE_CHOICE.getType()) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item_single_choice, parent, false);
        } else if (viewType == TYPE_COMMENT.getType()) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item_comment, parent, false);
        } else {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item_unknown, parent, false);
        }
    }
}
