package com.example.oneviewroomapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.oneviewroomapp.entities.Push;
import com.example.oneviewroomapp.entities.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class, Push.class}, version = 12, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {

    ///Этот метод для передачи обьекта ДАО в репозиторий
    public abstract WordDao wordDao();

    public static volatile WordDataBase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    //Executor создан для использования его
    //В запросах через Репозиторий в ДАО класс
    //Получается он создает определенное кол-во потоков
    static final ExecutorService EXECUTOR_SERVICE =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WordDataBase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), WordDataBase.class, "word_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
