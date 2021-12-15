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
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;
    EditText ed_Word, ed_Rep;
    int counter = 0;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    int count = 1;

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
        //  counter++;
        String wordToSend = ed_Word.getText().toString();
        String temp = ed_Rep.getText().toString();
        //  Word words = new Word("w", 2, "sd");
        //Пытаюсь здесь получить дату из обьектов в базе
        if (mWordViewModel.getAllWords().getValue().isEmpty()) {
            Word words = new Word(wordToSend, Integer.parseInt(temp), format.format(new Date()));
            mWordViewModel.insert(words);
            words.setCounter(count);
        } else {
            for (Word word : mWordViewModel.getAllWords().getValue()) {
                if (word.getDate().equals(format.format(new Date()))) {
                    int x = word.getRep();
                    int y = Integer.parseInt(temp);
                    int sum = x + y;
                    int c = word.getCounter();
                    int counter = count + c;
                    mWordViewModel.customUpdate(format.format(new Date()), sum, counter);
                    break;
                } else {
                    Word wordTemp = new Word(wordToSend, Integer.parseInt(temp), format.format(new Date()));
                    mWordViewModel.insert(wordTemp);
                    break;
                }
            }
        }


//        if (words.getDate().equals(format.format(new Date()))) {
//            int x = words.getRep();
//            Log.d("MyLog", String.valueOf(words.getRep()));
//            int y = Integer.parseInt(temp);
//            int sum = x + y;
//            Log.d("MyLog", String.valueOf(sum));
//            mWordViewModel.customUpdate(format.format(new Date()), sum, 2);
//        } else {
//            Word wordTemp = new Word(wordToSend, Integer.parseInt(temp), format.format(new Date()));
//            mWordViewModel.insert(wordTemp);
//        }
    }


    public void delete(View view) {
        mWordViewModel.delete();
    }
}