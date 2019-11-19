package com.example.taskapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FromActivity extends AppCompatActivity {
    EditText editText1, editText2;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);
        editText1 = findViewById(R.id.ET1);
        editText2 = findViewById(R.id.ET2);
        task = (Task) getIntent().getSerializableExtra("task");
        if (task != null){
         editText2.setText(task.getDesk());
        editText1.setText(task.getTitle());
        }
    }
    public void onClick(View view){
        String title = editText1.getText().toString().trim();
        String desk = editText2.getText().toString().trim();
//        Intent intent = new Intent();
        if (task != null){
            task.setTitle(title);
            task.setDesk(desk);
            App.getDatabase().taskDao().update(task);
        }else{
         task = new Task(title, desk);

        App.getDatabase().taskDao().insert(task);}
//        intent.putExtra("task", task);
//        setResult(RESULT_OK, intent);
        finish();


    }
}
