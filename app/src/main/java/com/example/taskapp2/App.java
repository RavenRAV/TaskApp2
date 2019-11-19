package com.example.taskapp2;

import android.app.Application;

import androidx.room.Room;

import com.example.taskapp2.room.AppDatabase;

public class App extends Application {
    public static App instance;
    public static AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();

    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
