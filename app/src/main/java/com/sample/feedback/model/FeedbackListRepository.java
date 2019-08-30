package com.sample.feedback.model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sample.feedback.managers.AssetsManager;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FeedbackListRepository {


    private static FeedbackListRepository sInstance;
    private FeedbackListRepository() {
    }

    public static FeedbackListRepository getInstance() {
        if (sInstance == null) {
            synchronized (FeedbackListRepository.class) {
                if (sInstance == null) {
                    sInstance = new FeedbackListRepository();
                }
            }
        }
        return sInstance;
    }

    public FeedbackList getFeedbackList(@NonNull Context context) {
        FeedbackList feedbackList = null;
        String feedbackJsonString = new AssetsManager(context.getAssets()).getFeedbackString();
        if (feedbackJsonString != null && !feedbackJsonString.isEmpty()) {
            Gson gson = new GsonBuilder().create();
            Type feedbackListType = new TypeToken<ArrayList<FeedbackItem>>(){}.getType();
            ArrayList<FeedbackItem> feedbackArrayList = gson.fromJson(feedbackJsonString, feedbackListType);
            feedbackList = new FeedbackList(feedbackArrayList);
        }
        return feedbackList;
    }
}
