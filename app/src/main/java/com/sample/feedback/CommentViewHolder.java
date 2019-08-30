package com.sample.feedback;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

class CommentViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    EditText commentBox;
    SwitchCompat switchButton;
    CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        commentBox = itemView.findViewById(R.id.comment_box);
        switchButton = itemView.findViewById(R.id.switch_button);
    }
}
