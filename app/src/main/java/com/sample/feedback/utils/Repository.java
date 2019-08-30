package com.sample.feedback.utils;

import android.content.Context;

import com.sample.feedback.model.FeedbackListRepository;

/**
 * Repository is access point for all the data sources
 * Data source might be
 * 1. Network
 * 2. Database
 * 3. Assets
 */

public class Repository {

   public static FeedbackListRepository createFeedbackListRepository() {
        return FeedbackListRepository.getInstance();
   }
}
