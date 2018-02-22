package com.example.home.androidtestgame;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

public class TabHostActivity extends ActivityGroup {

    TabHost teacherStudentHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        teacherStudentHost = findViewById(R.id.tabHost);

        teacherStudentHost.setup(this.getLocalActivityManager());

        TabHost.TabSpec ts1 = teacherStudentHost.newTabSpec("Teacher");
        ts1.setIndicator("Teacher");
        ts1.setContent(new Intent(this,TeacherLogInActivity.class));
        teacherStudentHost.addTab(ts1);

        TabHost.TabSpec ts2 = teacherStudentHost.newTabSpec("Student");
        ts2.setIndicator("Student");
        ts2.setContent(new Intent(this,LogInActivity.class));
        teacherStudentHost.addTab(ts2);

    }
}
