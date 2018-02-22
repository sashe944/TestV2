package com.example.home.androidtestgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StartTestActivity extends AppCompatActivity {

    ListView lvStartTest;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);

        items = new ArrayList<>();
        items.add("C#");
        items.add("JAVA");
        items.add("PHP");
        items.add("ANDROID");
        items.add("C");
        items.add("PYTHON");
        items.add("PL/SQL");
        items.add("MySQL");
        items.add("T-SQL");
   lvStartTest = findViewById(R.id.lv_choose_test);

      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView,items);
       lvStartTest.setAdapter(adapter);

       lvStartTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(StartTestActivity.this,TestActivity.class);
               startActivity(intent);
           }
       });

    }
}
