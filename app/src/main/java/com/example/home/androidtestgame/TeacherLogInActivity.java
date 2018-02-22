package com.example.home.androidtestgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherLogInActivity extends AppCompatActivity {

    Button teacher_log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_log_in);

        teacher_log_in = findViewById(R.id.btn_log_in);

        teacher_log_in.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TeacherLogInActivity.this,TeacherMenuActivity.class);
            startActivity(intent);
        }
    };
}
