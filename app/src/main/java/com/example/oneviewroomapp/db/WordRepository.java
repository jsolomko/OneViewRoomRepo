package com.example.oneviewroomapp.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> AllWord;

    public WordRepository(Application application) {
        WordDataBase db = WordDataBase.getINSTANCE(application);
        wordDao = db.wordDao();
        AllWord = wordDao.getWord();
    }

    //Возаращает то, что указанов в DAO то есть LiveData
    public LiveData<List<Word>> getAllWord() {
        return AllWord;
    }

    void insert(Word word) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.insert(word));
    }

    public void delete() {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.delete());
    }

    void update(Word word) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.update(word));
    }

    void customUpdate(String date, int reps, int counter) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.customUpdate(date, reps, counter));
    }
}
