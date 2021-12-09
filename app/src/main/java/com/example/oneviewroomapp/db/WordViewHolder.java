package com.example.oneviewroomapp.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneviewroomapp.R;

public class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView, repItem;

    public WordViewHolder(@NonNull View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.tv_Word);
        repItem = itemView.findViewById(R.id.tv_rep);
    }

    //???
    public void bind(String text, String rep) {
        wordItemView.setText(text);
        repItem.setText(rep);
    }

    static WordViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new WordViewHolder(view);
    }


}
