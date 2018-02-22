package com.example.home.androidtestgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class QuestionsPopupActivity extends AppCompatActivity {

    Button ok;
    Button cancel;
    ArrayAdapter <String> adapter;
    ArrayList <String> items;
    ListView lv_add_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        lv_add_questions = findViewById(R.id.custom_lv_questions);


        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        ok=findViewById(R.id.buttonOk);
        cancel=findViewById(R.id.buttonCancel);

        items = new ArrayList<>();
        items.add("Kak си ?");
        items.add("Какво правиш ?");
        items.add("На колко си ?");

        adapter = new ArrayAdapter<String>(this,R.layout.questions_layout,R.id.tv_question_add,items);
        lv_add_questions.setAdapter(adapter);

        cancel.setOnClickListener(onCancelListener);
    }

    View.OnClickListener onCancelListener = new View.OnClickListener() {
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
