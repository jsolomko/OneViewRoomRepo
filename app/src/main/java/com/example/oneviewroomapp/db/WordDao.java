package com.example.oneviewroomapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(entity = Word.class)
    void insert(Word word);

    @Query("SELECT * FROM word")
    LiveData<List<Word>> getWord();

    @Query("DELETE FROM word")
    void delete();

    @Update(entity = Word.class)
    void update(Word word);

    @Query("UPDATE word SET reps = :reps WHERE id = :tid")
    int customUpdate(int tid, int reps);


}
