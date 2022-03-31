package com.example.oneviewroomapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.adapters.NoteAdapter;
import com.example.oneviewroomapp.databinding.ActivityNoteBinding;
import com.example.oneviewroomapp.db.WordViewModel;
import com.example.oneviewroomapp.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends DrawerBaseActivity {

    ActivityNoteBinding activityNoteBinding;
    RecyclerView recyclerView;
    List<Note> noteList = new ArrayList<>();
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNoteBinding = ActivityNoteBinding.inflate(getLayoutInflater());
        setContentView(activityNoteBinding.getRoot());

        Note note = new Note("11", "mynote");
        Note note1 = new Note("11", "mynote");
        noteList.add(note);
        noteList.add(note1);

        recyclerView = findViewById(R.id.rv_Note);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoteAdapter adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getListNote().observe(this, notes -> {
            adapter.notifyDataSetChanged();
        });

    }

    public void testAddNote(View view) {
        Note note3 = new Note("11", "mynote");
        noteList.add(note3);
    }
}