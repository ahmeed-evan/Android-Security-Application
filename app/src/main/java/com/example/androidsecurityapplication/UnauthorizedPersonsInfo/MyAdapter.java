package com.example.androidsecurityapplication.UnauthorizedPersonsInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsecurityapplication.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<PictureItem>pictureItems;

    public MyAdapter(Context context, ArrayList<PictureItem> pictureItems) {
        this.context = context;
        this.pictureItems = pictureItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.picture_itemlist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PictureItem pictureItem=pictureItems.get(position);
        holder.item_date_tv.setText(pictureItem.getDate());
        holder.item_image_view.setImageURI(pictureItem.getUri());

    }

    @Override
    public int getItemCount() {
        return pictureItems.size();
    }
}
