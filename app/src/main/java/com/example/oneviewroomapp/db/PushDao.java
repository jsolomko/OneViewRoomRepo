package com.example.oneviewroomapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.entities.Word;

import java.util.List;

interface PushDao extends BaseDao<Push> {
    //Запросы ко второй таблице
    @Insert(entity = Push.class)
    void insert(Push push);

    @Query("SELECT * FROM push")
    LiveData<List<Push>> getPush();

}
