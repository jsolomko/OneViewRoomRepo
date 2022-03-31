package com.example.oneviewroomapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.entities.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    TextView tvDate, tvNote;
    List<Note> noteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_Note_Date);
            tvNote = itemView.findViewById(R.id.tv_Note_aNote);
        }
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_layout, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        tvDate.setText(noteList.get(position).getDate());
        tvNote.setText(noteList.get(position).getNote());
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }


}
