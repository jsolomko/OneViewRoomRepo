package com.example.oneviewroomapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.db.WordListAdapter;
import com.example.oneviewroomapp.db.WordViewModel;

public class SettingActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RecyclerView recyclerView = findViewById(R.id.recycler_setting);
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            //
            adapter.submitList(words);
        });

        //2
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
                Intent intent = new Intent(SettingActivity.this, PushActivity.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent i = new Intent(SettingActivity.this, SettingActivity.class);
                startActivity(i);
                return true;
            case R.id.item3:
                Toast.makeText(this, "PIP", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
