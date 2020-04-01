package com.example.androidsecurityapplication.UnauthorizedPersonsInfo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsecurityapplication.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView item_date_tv;
    ImageView item_image_view;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        this.item_date_tv=itemView.findViewById(R.id.item_date_tv);
        this.item_image_view=itemView.findViewById(R.id.item_image_view);
    }
}
