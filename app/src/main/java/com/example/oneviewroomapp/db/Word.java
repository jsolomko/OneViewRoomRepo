package com.example.oneviewroomapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word")
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String word;
    private int rep;


    public Word(String word, int rep) {
        this.word = word;
        this.rep = rep;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
