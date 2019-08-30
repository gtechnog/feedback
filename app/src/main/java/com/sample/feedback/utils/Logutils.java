package com.sample.feedback.utils;

import android.util.Log;

import com.sample.feedback.constants.StringConstants;
import com.sample.feedback.model.FeedbackItem;
import com.sample.feedback.model.FeedbackList;

import java.util.List;

public class Logutils {

    private static final String TAG = "LogUtils";

    public static void logDetailsOfsubmittedForm(FeedbackList feedbackList) {
        if (feedbackList != null && feedbackList.getFeedbackItemList() != null && feedbackList.getFeedbackItemList().size() != 0) {
            List<FeedbackItem> list = feedbackList.getFeedbackItemList();
            for (int i = 0; i < list.size(); i++) {
                FeedbackItem item = list.get(i);
                if (item.getType().equals(StringConstants.FEEDBACK_ITEM_TYPE_PHOTO)){
                    Log.d(TAG, "id: " + item.getId() + ", image: " + item.getImageUri());
                } else if (item.getType().equals(StringConstants.FEEDBACK_ITEM_TYPE_SINGLE_CHOICE)) {
                    Log.d(TAG, "id: " + item.getId() + ", choice: " + item.getSelectedChoice());
                } else if (item.getType().equals(StringConstants.FEEDBACK_ITEM_TYPE_COMMENT)) {
                    Log.d(TAG, "id: " + item.getId() + ", comment: " + item.getComment());
                }
            }
        }
    }
}
