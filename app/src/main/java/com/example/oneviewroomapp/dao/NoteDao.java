package com.example.oneviewroomapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.oneviewroomapp.entities.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert(entity = Note.class)
    void insert(Note note);

    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getNotes();


}
