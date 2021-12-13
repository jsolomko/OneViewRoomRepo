package com.example.oneviewroomapp.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private final LiveData<List<Word>> listWord;

    public WordViewModel(Application application) {
        super(application);
        repository = new WordRepository(application);
        listWord = repository.getAllWord();
    }

    public LiveData<List<Word>> getAllWords() {
        return listWord;
    }

    public void insert(Word word) {
        repository.insert(word);
    }

    public void delete( ){
        repository.delete();
    }
    public void update(Word word){
        repository.update(word);
    }

}
