package com.example.oneviewroomapp.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.databinding.ActivityMainBinding;
import com.example.oneviewroomapp.entities.Word;
import com.example.oneviewroomapp.adapters.WordListAdapter;
import com.example.oneviewroomapp.db.WordViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends DrawerBaseActivity {
    ActivityMainBinding activityMainBinding;
    private WordViewModel mWordViewModel;
    EditText ed_Word, ed_Rep;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        allocateActivityTitle("ПОДТЯГИВАНИЯ");
//        setContentView(R.layout.activity_main);
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


    //МЕНЮ
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    //Листнер нажатий в меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_push:
                Intent intent = new Intent(MainActivity.this, PushActivity.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
                return true;
            case R.id.item3:
                Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                mWordViewModel.delete();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void load(View view) {
        String wordToSend = ed_Word.getText().toString();
        String temp = ed_Rep.getText().toString();
        boolean isDate = false;
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
                    isDate = true;
                }
            }
            if (!isDate) {
                Word wordTemp = new Word(wordToSend, Integer.parseInt(temp), format.format(new Date()));
                wordTemp.setCounter(count);
                mWordViewModel.insert(wordTemp);
            }
        }
    }

    public void delete(View view) {
        mWordViewModel.delete();
    }




}