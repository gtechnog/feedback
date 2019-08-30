package com.sample.feedback;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class PhotoViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView thumbnail;
    ImageView delete;
    PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        thumbnail = itemView.findViewById(R.id.photo_image);
        delete = itemView.findViewById(R.id.delete);
    }
}
