package com.example.oneviewroomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.db.WordViewModel;

public class PushActivity extends AppCompatActivity {
    EditText edPush;
    TextView tvPush;
    private WordViewModel mWordViewModel;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push2);
        init();
//        mWordViewModel.getAllPush().observe(this, pushes -> {
//            for (Push p : pushes) {
//                tvPush.setText(String.valueOf(p.getPushCount()));
//            }
//        });


        ///Таймер
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 100000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(PushActivity.this, "Bing!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();

            tvPush.setText(String.valueOf(pauseOffset));
            running = false;
        }
    }

    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }


    //КОНЕЦ ТАЙМЕРА

    public void savePush(View view) {
        edPush.getText();
        Push push = new Push("фывфыв", Integer.parseInt(edPush.getText().toString()));
        mWordViewModel.insert(push);

    }

    void init() {
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        edPush = findViewById(R.id.edPush);
        tvPush = findViewById(R.id.tvPush);
    }
}