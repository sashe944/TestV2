package com.example.home.androidtestgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TeacherCreateQuestionActivity extends AppCompatActivity {

    Button giveAnswer;
    Button saveQuestion;
    Button cancelSavingQuestion;

    ListView lv_test_answers;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_question);

       lv_test_answers= findViewById(R.id.lv_answers);
       cancelSavingQuestion = findViewById(R.id.btnCancelQuestion);

       list.add("Добре съм");
       list.add("На 23 години съм");

       adapter = new ArrayAdapter<String>(this,R.layout.questions_layout,R.id.tv_question_add,list);

       lv_test_answers.setAdapter(adapter);

       giveAnswer = findViewById(R.id.btn_write_answer);

       giveAnswer.setOnClickListener(onClickListener);
       cancelSavingQuestion.setOnClickListener(onCancelListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TeacherCreateQuestionActivity.this,AnswerPopupActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener save = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO: Save Question for the test
        }
    };

    View.OnClickListener onCancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TeacherCreateQuestionActivity.this,TeacherMenuActivity.class);
            startActivity(intent);
        }
    };
}