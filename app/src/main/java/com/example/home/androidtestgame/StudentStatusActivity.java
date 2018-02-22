package com.example.home.androidtestgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentStatusActivity extends AppCompatActivity {

    ListView lvStudentInfo;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_status);

        lvStudentInfo = findViewById(R.id.lv_studentInfo);

        items.add("10.02.2005 PL/SQL 20");
        items.add("10.02.2005 C 20");
        items.add("10.02.2005 C# 20");
        items.add("10.02.2005 JAVA 20");
        items.add("10.02.2005 ANDROID 30");
        items.add("10.02.2005 PHP 10");
        items.add("10.02.2005 PYTHON 20");

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                items);

        lvStudentInfo.setAdapter(adapter);

    }
}
