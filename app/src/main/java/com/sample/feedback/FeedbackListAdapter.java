package com.sample.feedback;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.feedback.customviews.CustomRadioGroup;
import com.sample.feedback.model.FeedbackItem;
import com.sample.feedback.model.FeedbackList;
import com.sample.feedback.utils.DiskImageUtils;

import java.util.Objects;

import static com.sample.feedback.constants.StringConstants.FEEDBACK_ITEM_TYPE_COMMENT;
import static com.sample.feedback.constants.StringConstants.FEEDBACK_ITEM_TYPE_PHOTO;
import static com.sample.feedback.constants.StringConstants.FEEDBACK_ITEM_TYPE_SINGLE_CHOICE;
import static com.sample.feedback.model.FeedbackItemType.TYPE_COMMENT;
import static com.sample.feedback.model.FeedbackItemType.TYPE_PHOTO;
import static com.sample.feedback.model.FeedbackItemType.TYPE_SINGLE_CHOICE;
import static com.sample.feedback.model.FeedbackItemType.TYPE_UNKNOWN;

public class FeedbackListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = FeedbackListAdapter.class.getSimpleName();
    private FeedbackList mFeedbackList;
    private FeedbackListFragment.OnListItemClickListener mListener;

    FeedbackListAdapter(@Nullable FeedbackList feedbackList, @NonNull FeedbackListFragment.OnListItemClickListener listener) {
        mFeedbackList = feedbackList;
        mListener = listener;
    }

    void refreshList(FeedbackList feedbackList) {
        mFeedbackList = feedbackList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = InflatedViewFactory.inflateViewForViewType(viewType, parent);
        return ViewHolderFactory.createViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final FeedbackItem feedbackItem = mFeedbackList.getFeedbackItemList().get(position);
        switch (feedbackItem.getType()) {
            case FEEDBACK_ITEM_TYPE_PHOTO:
                bindPhotoTypeItem((PhotoViewHolder) holder, position);
                break;
            case FEEDBACK_ITEM_TYPE_SINGLE_CHOICE:
                bindSingleChoiceTypeItem((SingleChoiceViewHolder) holder, position);
                break;
            case FEEDBACK_ITEM_TYPE_COMMENT:
                bindCommentTypeItem((CommentViewHolder) holder, position);
                break;
            default:
                break;
        }
    }

    private void bindPhotoTypeItem(@NonNull final PhotoViewHolder holder, final int position) {
        final FeedbackItem feedbackItem = mFeedbackList.getFeedbackItemList().get(position);
        holder.title.setText(feedbackItem.getTitle());

        if (feedbackItem.getImageUri() != null) {
            holder.thumbnail.setImageBitmap(feedbackItem.getBitmap());
            holder.delete.setVisibility(View.VISIBLE);
        } else {
            holder.thumbnail.setImageResource(android.R.drawable.ic_menu_camera);
            holder.delete.setVisibility(View.GONE);
        }

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListItemClicked(feedbackItem, TYPE_PHOTO, position);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiskImageUtils.deleteFileFromDisk(Objects.requireNonNull(feedbackItem.getImageUri().getPath()));

                // clear the image URI and bitmap from item
                feedbackItem.setImageUri(null);
                feedbackItem.setBitmap(null);
                holder.delete.setVisibility(View.GONE);
                holder.thumbnail.setImageResource(android.R.drawable.ic_menu_camera);
            }
        });
    }

    private void bindSingleChoiceTypeItem(@NonNull final SingleChoiceViewHolder holder, final int position) {
        final FeedbackItem feedbackItem = mFeedbackList.getFeedbackItemList().get(position);

        holder.title.setText(feedbackItem.getTitle());
        CustomRadioGroup radioGroup = holder.radioGroup;
        radioGroup.setChoiceModel(feedbackItem.getDataMap());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = group.findViewById(checkedId);
                feedbackItem.setSelectedChoice(button.getText());
                Log.d(TAG, "selected choice: " + feedbackItem.getSelectedChoice());
            }
        });
    }

    private void bindCommentTypeItem(@NonNull final CommentViewHolder holder, final int position) {
        final FeedbackItem feedbackItem = mFeedbackList.getFeedbackItemList().get(position);
        if (feedbackItem.isProvidingComment()) {
            holder.switchButton.setChecked(true);
            holder.commentBox.setVisibility(View.VISIBLE);
            holder.commentBox.setText(feedbackItem.getComment());
        } else {
            holder.switchButton.setChecked(false);
            holder.commentBox.setVisibility(View.GONE);
            holder.commentBox.setText(null);
        }


        holder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.commentBox.setVisibility(View.VISIBLE);
                } else {
                    holder.commentBox.setVisibility(View.GONE);
                }
                feedbackItem.setProvidingComment(isChecked);
            }
        });

        // Store the comment as user types in the editable field.
        holder.commentBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                feedbackItem.setComment(s);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        FeedbackItem item = mFeedbackList.getFeedbackItemList().get(position);
        switch (item.getType()) {
            case FEEDBACK_ITEM_TYPE_PHOTO:
                return TYPE_PHOTO.getType();
            case FEEDBACK_ITEM_TYPE_SINGLE_CHOICE:
                return TYPE_SINGLE_CHOICE.getType();
            case FEEDBACK_ITEM_TYPE_COMMENT:
                return TYPE_COMMENT.getType();
                default:
                    return TYPE_UNKNOWN.getType();
        }
    }

    @Override
    public int getItemCount() {
        if (mFeedbackList == null) {
            return 0;
        } else if (mFeedbackList.getFeedbackItemList() == null) {
            return 0;
        } else {
            return mFeedbackList.getFeedbackItemList().size();
        }
    }

}
