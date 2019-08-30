package com.sample.feedback;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.feedback.model.FeedbackItem;
import com.sample.feedback.model.FeedbackItemType;
import com.sample.feedback.model.FeedbackList;
import com.sample.feedback.utils.Logutils;
import com.sample.feedback.utils.Repository;

import java.util.Objects;

/**
 * A fragment representing a list of item in feedback form
 */
public class FeedbackListFragment extends Fragment {

    private static String TAG = FeedbackListFragment.class.getSimpleName();
    private OnListItemClickListener mInterationListener;
    private FeedbackListAdapter feedbackListAdapter;
    private FeedbackList feedbackList;

    public FeedbackListFragment() {
    }

    static FeedbackListFragment newInstance() {
        return  new FeedbackListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListItemClickListener) {
            mInterationListener = (OnListItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mInterationListener = null;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        hideKeyboard();
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
            feedbackList = Repository.createFeedbackListRepository().getFeedbackList(Objects.requireNonNull(getContext()));
            feedbackListAdapter = new FeedbackListAdapter(feedbackList, mInterationListener);
            recyclerView.setAdapter(feedbackListAdapter);
        }
        return view;
    }

    void refreshItemInList(int currentPosition) {
        feedbackListAdapter.notifyItemChanged(currentPosition);
    }

    void submitForm() {
        Logutils.logDetailsOfsubmittedForm(feedbackList);
        feedbackList = Repository.createFeedbackListRepository().getFeedbackList(Objects.requireNonNull(getContext()));
        feedbackListAdapter.refreshList(feedbackList);
    }

    private void hideKeyboard() {
        View view = Objects.requireNonNull(getActivity()).getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(getContext());
        }
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     *  Interface should be implemented by hosting activity
     */
    public interface OnListItemClickListener {

        void onListItemClicked(FeedbackItem item, FeedbackItemType itemType, int position);
    }
}
