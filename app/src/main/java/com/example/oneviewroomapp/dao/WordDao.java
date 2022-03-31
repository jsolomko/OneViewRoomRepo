package com.example.oneviewroomapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.entities.Word;

import java.text.SimpleDateFormat;
import java.util.List;

@Dao
public interface WordDao {
    @Insert(entity = Word.class)
    void insert(Word word);


    @Query("SELECT * FROM word ORDER by Date DESC")
    LiveData<List<Word>> getWord();

    @Query("SELECT * FROM word WHERE Date = :date")
    LiveData<List<Word>> getAWord(String date);

    @Query("DELETE FROM word")
    void delete();

    @Update()
    void update(Word word);

    @Query("UPDATE word SET reps = :reps, counter = :count, word =:comment  WHERE Date = :date")
    int customUpdate(String date, int reps, int count, String comment);


    //Запросы ко второй таблице
    @Insert(entity = Push.class)
    void insert(Push push);

    @Query("SELECT * FROM push")
    LiveData<List<Push>> getPush();

}
