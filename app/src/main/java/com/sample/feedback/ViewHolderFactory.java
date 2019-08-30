package com.sample.feedback;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import static com.sample.feedback.model.FeedbackItemType.TYPE_COMMENT;
import static com.sample.feedback.model.FeedbackItemType.TYPE_PHOTO;
import static com.sample.feedback.model.FeedbackItemType.TYPE_SINGLE_CHOICE;

class ViewHolderFactory {

    static RecyclerView.ViewHolder createViewHolder(int viewType, View view) {

        if (viewType == TYPE_PHOTO.getType()) {
            return new PhotoViewHolder(view);
        } else if (viewType == TYPE_SINGLE_CHOICE.getType()) {
            return new SingleChoiceViewHolder(view);
        } else if (viewType == TYPE_COMMENT.getType()) {
            return new CommentViewHolder(view);
        } else {
            return new UnknownTypeViewHolder(view);
        }
    }
}
