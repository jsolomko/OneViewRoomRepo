package com.example.oneviewroomapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.databinding.ActivityCustomPushBinding;

public class CustomPushActivity extends DrawerBaseActivity {
    ActivityCustomPushBinding activityCustomPushBinding;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCustomPushBinding = ActivityCustomPushBinding.inflate(getLayoutInflater());
        setContentView(activityCustomPushBinding.getRoot());
        allocateActivityTitle("CUSTOM PUSH");
    }
}