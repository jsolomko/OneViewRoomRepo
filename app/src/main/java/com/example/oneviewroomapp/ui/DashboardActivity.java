package com.example.oneviewroomapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.oneviewroomapp.R;
import com.example.oneviewroomapp.databinding.ActivityDashboardBinding;

public class DashboardActivity extends DrawerBaseActivity {


    ActivityDashboardBinding activityDashboardBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
    }
}