package com.example.home.androidtestgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TeacherGiveGradeActivity extends AppCompatActivity {

    ListView studentGradeList;
    ArrayAdapter adapter;
    ArrayList<String> disciplineArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_give_grade);

        studentGradeList = findViewById(R.id.lv_grades);

        disciplineArrayList.add("C#");
        disciplineArrayList.add("C");
        disciplineArrayList.add("JAVA");
        disciplineArrayList.add("ANDROID");
        disciplineArrayList.add("C++");
        disciplineArrayList.add("PL/SQL");
        disciplineArrayList.add("MySQL");
        disciplineArrayList.add("T-SQL");

        adapter = new ArrayAdapter<String>(this,R.layout.grading_layout,R.id.discNameTextView,disciplineArrayList);

        studentGradeList.setAdapter(adapter);





    }
}
