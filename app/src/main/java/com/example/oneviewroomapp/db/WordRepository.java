package com.example.oneviewroomapp.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.entities.Word;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private PushDao pushDao;
    private LiveData<List<Word>> AllWord;
    private LiveData<List<Push>> allPush;

    public WordRepository(Application application) {
        WordDataBase db = WordDataBase.getINSTANCE(application);
        wordDao = db.wordDao();
        AllWord = wordDao.getWord();
        allPush = pushDao.getPush();
    }

    //Возаращает то, что указанов в DAO то есть LiveData
    public LiveData<List<Word>> getAllWord() {
        return AllWord;
    }

    public LiveData<List<Push>> getAllPush() {
        return allPush;
    }


    void insert(Word word) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.insert(word));
    }

//    void insert(Push push) {
//        WordDataBase.EXECUTOR_SERVICE.execute(() ->
//                wordDao.insert(push));
//    }
//
//    public void delete() {
//        WordDataBase.EXECUTOR_SERVICE.execute(() ->
//                wordDao.delete());
//    }

    void update(Word word) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.update(word));
    }

    void customUpdate(String date, int reps, int counter) {
        WordDataBase.EXECUTOR_SERVICE.execute(() ->
                wordDao.customUpdate(date, reps, counter));
    }
}
