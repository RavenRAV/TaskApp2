package com.example.taskapp2.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskapp2.Task;

import java.util.List;

@Dao
public interface TaskDao {
//    @Query("SELECT * FROM task")
//    List<Task> getAll();

    @Query("SELECT * FROM task")
    LiveData<List<Task>>getAll();

    @Insert
    void insert(Task task);
    @Update
    void update(Task task);
}
