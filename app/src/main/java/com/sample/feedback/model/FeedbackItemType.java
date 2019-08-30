package com.sample.feedback.model;

public enum FeedbackItemType {
    TYPE_UNKNOWN(0),
    TYPE_PHOTO(1),
    TYPE_SINGLE_CHOICE(2),
    TYPE_COMMENT(3);

    private final int value;
    FeedbackItemType(int value) {
        this.value = value;
    }

    public int getType() {
        return value;
    }
}
