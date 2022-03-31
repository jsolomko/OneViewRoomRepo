package com.example.oneviewroomapp.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.entities.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private final LiveData<List<Word>> listWord;
    private final LiveData<List<Word>> listAWord;
    private final LiveData<List<Push>> listPush;

    public WordViewModel(Application application) {
        super(application);
        repository = new WordRepository(application);
        listWord = repository.getAllWord();
        listPush = repository.getAllPush();
        listAWord = repository.getaWordList();
    }

    public LiveData<List<Word>> getAllWords() {
        return listWord;
    }

    public LiveData<List<Word>> getListAWord() {
        return listAWord;
    }

    public LiveData<List<Push>> getAllPush() {
        return listPush;
    }

    public void insert(Word word) {
        repository.insert(word);
    }

    public void insert(Push push) {
        repository.insert(push);
    }

    public void delete() {
        repository.delete();
    }

    public void update(Word word) {
        repository.update(word);
    }

    public void customUpdate(String date, int reps, int counter, String comment) {
        repository.customUpdate(date, reps, counter, comment);
    }
}
