package com.example.oneviewroomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.oneviewroomapp.db.Word;
import com.example.oneviewroomapp.db.WordDao;
import com.example.oneviewroomapp.db.WordDataBase;
import com.example.oneviewroomapp.db.WordListAdapter;
import com.example.oneviewroomapp.db.WordViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;
    EditText ed_Word, ed_Rep;
    int counter = 0;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_Word = findViewById(R.id.ed_Word);
        ed_Rep = findViewById(R.id.ed_rep);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            //
            adapter.submitList(words);
        });
    }

    public void load(View view) {

        counter++;
        String wordToSend = ed_Word.getText().toString();
        String temp = ed_Rep.getText().toString();
        if (wordToSend.equals("A")) {
           // Word word = new Word(wordToSend, Integer.parseInt(temp));

            mWordViewModel.customUpdate(79,Integer.parseInt(temp), counter);
            Log.d("MyLog", format.format(new Date()));

        } else {
            Word word = new Word(wordToSend, Integer.parseInt(temp));
            mWordViewModel.insert(word);
        }
    }

    public void delete(View view) {
        mWordViewModel.delete();

    }
}