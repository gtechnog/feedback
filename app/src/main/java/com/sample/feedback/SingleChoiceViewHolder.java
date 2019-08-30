package com.sample.feedback;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.feedback.customviews.CustomRadioGroup;

class SingleChoiceViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    CustomRadioGroup radioGroup;
    SingleChoiceViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        radioGroup = itemView.findViewById(R.id.choice_group);
    }
}
