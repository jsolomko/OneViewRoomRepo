package com.example.oneviewroomapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.adapters.WordListAdapter;
import com.example.oneviewroomapp.databinding.ActivityCustomPullBinding;
import com.example.oneviewroomapp.db.WordViewModel;
import com.example.oneviewroomapp.entities.Word;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CustomPullActivity extends DrawerBaseActivity {
    ActivityCustomPullBinding activityCustomPullBinding;
    private WordViewModel mWordViewModel;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    int count = 1;
    EditText edCustomPull, edCustomRep;
    TextView tvCustomPull, tvCustomRep, tvCustomDate;
    int i = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCustomPullBinding = ActivityCustomPullBinding.inflate(getLayoutInflater());
        setContentView(activityCustomPullBinding.getRoot());

        allocateActivityTitle("СЕГОДНЯШНАЯ ТРЕНЯ");

        edCustomPull = findViewById(R.id.ed_CustomPull);
        edCustomRep = findViewById(R.id.ed_CustomRep);


        RecyclerView recyclerView = findViewById(R.id.recycler);
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getListAWord().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            //
            adapter.submitList(words);
        });
    }


    public void loadtoDBandShow(View view) {
        String reps = edCustomRep.getText().toString();
        String wordToSend = edCustomPull.getText().toString();

        boolean isDate = false;
        if (Objects.requireNonNull(mWordViewModel.getListAWord().getValue()).isEmpty()) {
            Word words = new Word(wordToSend, Integer.parseInt(reps), format.format(new Date()));
            mWordViewModel.insert(words);
            words.setCounter(count);
        } else {
            for (Word word : mWordViewModel.getListAWord().getValue()) {
                if (word.getDate().equals(format.format(new Date()))) {
                    int x = word.getRep();
                    int y = Integer.parseInt(reps);
                    int sum = x + y;
                    int c = word.getCounter();
                    int counter = count + c;
                    mWordViewModel.customUpdate(format.format(new Date()), sum, counter,wordToSend);
                    isDate = true;
                }
            }
            if (!isDate) {
                Word wordTemp = new Word(wordToSend, Integer.parseInt(reps), format.format(new Date()));
                wordTemp.setCounter(count);
                mWordViewModel.insert(wordTemp);
            }
        }
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

            case R.id.item4:
                mWordViewModel.delete();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}