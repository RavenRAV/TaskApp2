package com.example.taskapp2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity

public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String desk;

    public Task(String title, String desk) {
        this.title = title;
        this.desk = desk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }
}
