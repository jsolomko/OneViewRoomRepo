package com.example.oneviewroomapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.entities.Note;

import java.util.List;

public class NoteAdapter extends ListAdapter<Note, PostViewHolder> {


    public NoteAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PostViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Note currentPost = getItem(position);
        holder.bind(currentPost.getDate(), currentPost.getNote());
    }

    public static class NoteDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getDate().equals(newItem.getDate());
        }
    }
}
