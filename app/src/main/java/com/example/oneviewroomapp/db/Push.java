package com.example.oneviewroomapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "push")
public class Push {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String time;
    private int pushCount;

    public Push(String time, int pushCount) {
        this.time = time;
        this.pushCount = pushCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPushCount() {
        return pushCount;
    }

    public void setPushCount(int pushCount) {
        this.pushCount = pushCount;
    }
}
