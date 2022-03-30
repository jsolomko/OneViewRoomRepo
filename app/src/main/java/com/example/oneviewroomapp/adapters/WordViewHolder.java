package com.example.oneviewroomapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneviewroomapp.R;

public class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView, repItem, counter,date;

    public WordViewHolder(@NonNull View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.tv_Word);
        repItem = itemView.findViewById(R.id.tv_rep);
        counter = itemView.findViewById(R.id.tv_rep_counter);
        date = itemView.findViewById(R.id.tv_date);
    }


    public void bind(String text, int rep, int count, String date1) {
        wordItemView.setText(text);
        repItem.setText(String.valueOf(rep));
        counter.setText(String.valueOf(count));
        date.setText(date1);
    }

    static WordViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new WordViewHolder(view);
    }

    static WordViewHolder createOption(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item2, parent, false);
        return new WordViewHolder(view);
    }

}
