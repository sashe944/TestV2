package com.example.home.androidtestgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    DBHelper MyHelper;

    Button register;
    Button login;

    String studentPassword,studentFaculyNumber;
    EditText password,fNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        MyHelper = new DBHelper(this);

        register = findViewById(R.id.btn_register);
        login = findViewById(R.id.btn_log_in);

        password = findViewById(R.id.et_student_password);
        fNumber = findViewById(R.id.et_fnumber);

        register.setOnClickListener(onClickListener);
        login.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = null;
            if(v.getId()== R.id.btn_log_in){

                studentFaculyNumber = fNumber.getText().toString();
                studentPassword = password.getText().toString();



                    MyHelper.LogInStudent(studentPassword, studentFaculyNumber);

                    intent = new Intent(LogInActivity.this, StudentStatusActivity.class);

            }else {
                intent = new Intent(LogInActivity.this, RegisterActivity.class);
            }
            startActivity(intent);

        }
    };
}
