package com.example.home.androidtestgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class OldTestsActivity extends AppCompatActivity {

    Button backButton;
    ListView lvStudentScore;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_tests);

        lvStudentScore = findViewById(R.id.lv_moreInfo);

        items.add("ANDROID 6");


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                items);

        lvStudentScore.setAdapter(adapter);
        backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(backPress);
    }

    View.OnClickListener backPress = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
