package com.example.home.androidtestgame.tabHost;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.home.androidtestgame.student.LogInActivity;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.teacher.TeacherLogInActivity;

import static com.example.home.androidtestgame.R.id.studentTeacherTabHost;

public class TabHostActivity extends ActivityGroup {

    TabHost teacherStudentHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        teacherStudentHost = findViewById(studentTeacherTabHost);

        teacherStudentHost.setup(this.getLocalActivityManager());

        TabHost.TabSpec ts1 = teacherStudentHost.newTabSpec("Преподавател");
        ts1.setIndicator("Преподавател");
        ts1.setContent(new Intent(this,TeacherLogInActivity.class));
        teacherStudentHost.addTab(ts1);

        TabHost.TabSpec ts2 = teacherStudentHost.newTabSpec("Студент");
        ts2.setIndicator("Студент");
        ts2.setContent(new Intent(this,LogInActivity.class));
        teacherStudentHost.addTab(ts2);
    }
}
