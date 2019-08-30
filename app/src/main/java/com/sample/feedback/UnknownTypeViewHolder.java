package com.sample.feedback;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class UnknownTypeViewHolder extends RecyclerView.ViewHolder {

    UnknownTypeViewHolder(@NonNull View itemView) {
        super(itemView);
        TextView errorView = itemView.findViewById(R.id.unknown_type);
    }
}
