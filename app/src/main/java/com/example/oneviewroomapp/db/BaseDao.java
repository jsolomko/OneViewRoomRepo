package com.example.oneviewroomapp.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.oneviewroomapp.entities.Word;

public interface BaseDao<T> {
    @Insert()
    void insert(T t);

    @Update()
    void update(T t);

    @Delete
    void delete(T t);
}
