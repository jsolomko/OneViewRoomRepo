package com.example.oneviewroomapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.databinding.ActivityCustomPushBinding;
import com.example.oneviewroomapp.db.WordViewModel;
import com.example.oneviewroomapp.entities.Word;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomPullActivity extends DrawerBaseActivity {
    ActivityCustomPushBinding activityCustomPushBinding;
    private WordViewModel mWordViewModel;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    int count = 1;
    EditText edCustomPull, edCustomRep;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCustomPushBinding = ActivityCustomPushBinding.inflate(getLayoutInflater());
        setContentView(activityCustomPushBinding.getRoot());
        allocateActivityTitle("CUSTOM PUSH");
        edCustomPull = findViewById(R.id.ed_CustomPull);
        edCustomRep = findViewById(R.id.ed_CustomRep);
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

    }

    public void loadtoDBandShow(View view) {
        String wordToSend = edCustomPull.getText().toString();
        String temp = edCustomPull.getText().toString();
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
}