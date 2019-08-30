package com.sample.feedback.model;

import java.util.List;

public class FeedbackList {

    private List<FeedbackItem> feedbackItemList;

    FeedbackList(List<FeedbackItem> feedbackItemList) {
        this.feedbackItemList = feedbackItemList;
    }

    public List<FeedbackItem> getFeedbackItemList() {
        return feedbackItemList;
    }

    public void setFeedbackItemList(List<FeedbackItem> feedbackItemList) {
        this.feedbackItemList = feedbackItemList;
    }
}
