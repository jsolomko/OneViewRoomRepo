package com.example.oneviewroomapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.db.WordViewModel;

import java.util.Locale;

public class PushActivity extends AppCompatActivity {
    EditText edPush;
    TextView tvPush;
    private WordViewModel mWordViewModel;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    //В переменных seconds и running хранится коли-чество прошедших секунд и флаг работы секундомера.
    private int seconds = 0;
    private boolean wasRunning;
    private TextView timeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        init();

        mWordViewModel.getAllPush().observe(this, pushes -> {
            for (Push p : pushes) {
                tvPush.setText("Отжимания: " + p.getPushCount() + "\n" + "Ваше время: " + p.getTime());
            }
        });

        ///Таймер
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();

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
                Intent intent = new Intent(PushActivity.this, PushActivity.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent i = new Intent(PushActivity.this, SettingActivity.class);
                startActivity(i);
                return true;
            case R.id.item3:
                Toast.makeText(this, "PIP", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
        @Override
        public void onSaveInstanceState(Bundle savedInstanceState) {
            super.onSaveInstanceState(savedInstanceState);
            savedInstanceState.putInt("seconds", seconds);
            savedInstanceState.putBoolean("running", running);
            savedInstanceState.putBoolean("wasRunning", wasRunning);
        }


        @Override
        protected void onPause() {
            super.onPause();
            wasRunning = running;
            running = false;
        }

        @Override
        protected void onResume() {
            super.onResume();
            if (wasRunning) {
                running = true;
            }
        }
        //Start the stopwatch running when the Start button is clicked.
        public void onClickStart(View view) {
            running = true;
        }

        //Stop the stopwatch running when the Stop button is clicked.
        public void onClickStop(View view) {
            running = false;
        }

        //Reset the stopwatch when the Reset button is clicked.
        public void onClickReset(View view){
            running = false;
            seconds = 0;
            tvPush.setText("");
        }

        //Обновление таймера
        private void runTimer(){
            timeView = findViewById(R.id.time_view);
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int hours = seconds/3600;
                    int minutes = (seconds%3600)/60;
                    int secs = seconds%60;
                    String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs );
                    //Задать текст надписи
                    timeView.setText(time);
                    if (running){
                        seconds++;
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }
    //КОНЕЦ ТАЙМЕРА

    public void savePush(View view) {
        edPush.getText();
        String time = timeView.getText().toString();
        Push push = new Push(time, Integer.parseInt(edPush.getText().toString()));
//        mWordViewModel.insert(push);

    }

    void init() {
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        edPush = findViewById(R.id.edPush);
        tvPush = findViewById(R.id.tvPush);
    }
}