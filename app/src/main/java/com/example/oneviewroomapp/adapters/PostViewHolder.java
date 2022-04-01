package com.example.oneviewroomapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneviewroomapp.R;

import java.lang.invoke.LambdaConversionException;

public class PostViewHolder extends RecyclerView.ViewHolder {


    private final TextView tvDate, tvNote;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvDate = itemView.findViewById(R.id.tv_Note_Date);
        this.tvNote = itemView.findViewById(R.id.tv_Note_aNote);
    }

    public void bind(String date, String note) {
        tvDate.setText(date);
        tvDate.setText(note);
    }

    static PostViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item_layout, parent, false);
        return new PostViewHolder(view);
    }
}
