package com.example.oneviewroomapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.entities.Word;

import java.util.List;

@Dao
 interface WordDao extends BaseDao<Word> {
   //Перенес их в интерфейс родителя
//    @Insert(entity = Word.class)
//    void insert(Word word);

    @Query("SELECT * FROM word ORDER by Date DESC")
    LiveData<List<Word>> getWord();

//    @Query("DELETE FROM word")
//    void delete();



    @Query("UPDATE word SET reps = :reps, counter = :count WHERE Date = :date")
    int customUpdate(String date, int reps, int count);


   //Запросы ко второй таблице
//   @Insert(entity = Push.class)
//   void insert(Push push);
//
//   @Query("SELECT * FROM push")
//   LiveData<List<Push>> getPush();
}
