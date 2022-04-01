package com.example.oneviewroomapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.adapters.NoteAdapter;
import com.example.oneviewroomapp.databinding.ActivityNoteBinding;
import com.example.oneviewroomapp.db.WordViewModel;
import com.example.oneviewroomapp.entities.Note;

public class NoteActivity extends DrawerBaseActivity {

    WordViewModel wordViewModel;
    ActivityNoteBinding activityNoteBinding;
    RecyclerView recyclerView;
    public static EditText edFormDate, edFormNote;
    public static Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNoteBinding = ActivityNoteBinding.inflate(getLayoutInflater());
        setContentView(activityNoteBinding.getRoot());

        edFormDate = findViewById(R.id.ed_From_Date);
        edFormNote = findViewById(R.id.ed_From_Note);

        recyclerView = findViewById(R.id.rv_Note);
        final NoteAdapter adapter = new NoteAdapter(new NoteAdapter.NoteDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getListNote().observe(this, notes -> {
            adapter.submitList(notes);
        });


    }

    public void onTabClick(View view) {
        NoteActivity.NoteFragment noteFragment = new NoteFragment();
        noteFragment.show(getSupportFragmentManager(), "");
    }

    public static class NoteFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder.setTitle("Dialog")
                    .setView(R.layout.form_item_layout)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setNegativeButton("cancel", null)
                    .create();
        }
    }

    public void insert(){
        note = new Note(edFormDate.getText().toString(), edFormNote.getText().toString());
        wordViewModel.insert(note);
    }

}