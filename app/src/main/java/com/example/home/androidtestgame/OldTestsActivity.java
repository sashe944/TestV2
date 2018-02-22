package com.example.home.androidtestgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OldTestsActivity extends AppCompatActivity {

    ListView lvStudentScore;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_tests);

        lvStudentScore = findViewById(R.id.lv_moreInfo);

        items.add("ТестовоИме1 PL/SQL 6");
        items.add("ТестовоИме2 SQL 6");
        items.add("ТестовоИме3 MySQL 6");
        items.add("ТестовоИме4 JAVA 6");
        items.add("ТестовоИме5 ANDROID 6");
        items.add("ТестовоИме6 C# 6");
        items.add("ТестовоИме7  C 6");
        items.add("ТестовоИме8  PHP 6");
        items.add("ТестовоИме9 C++ 6");
        items.add("ТестовоИме10 PYTHON 6");

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                items);

        lvStudentScore.setAdapter(adapter);


    }
}
