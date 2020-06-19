package com.example.android.stutern_movieapp.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.stutern_movieapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView categoryImage;
    TextView categoryTitle;


    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        categoryTitle = itemView.findViewById(R.id.categoryTitle);
        categoryImage = itemView.findViewById(R.id.categoryImage);
    }



    @Override
    public void onClick(View v) {

    }
}
