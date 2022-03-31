package com.example.oneviewroomapp.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.oneviewroomapp.dao.WordDao;
import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.entities.Word;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> AllWord;
    private LiveData<List<Word>> aWordList;
    private LiveData<List<Push>> allPush;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    public WordRepository(Application application) {
        WordDataBase db = WordDataBase.getINSTANCE(application);
        wordDao = db.wordDao();
        AllWord = wordDao.getWord();
        aWordList = wordDao.getAWord(format.format(new Date()));
        allPush = wordDao.getPush();
    }

    //Возаращает то, что указанов в DAO то есть LiveData
    public LiveData<List<Word>> getAllWord() {
        return AllWord;
    }
    public LiveData<List<Word>> getaWordList() {
        return aWordList;
    }

    public LiveData<List<Push>> getAllPush() {
        return allPush;
    }


    void insert(Word word) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.insert(word));
    }

    void insert(Push push) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.insert(push));
    }

    public void delete() {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.delete());
    }

    void update(Word word) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.update(word));
    }

    void customUpdate(String date, int reps, int counter, String comment) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.customUpdate(date, reps, counter,comment));
    }
}
