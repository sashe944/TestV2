package com.example.home.androidtestgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class LogInActivity extends AppCompatActivity {
    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        register = findViewById(R.id.btn_register);
        login = findViewById(R.id.btn_log_in);

        register.setOnClickListener(onClickListener);
        login.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = null;
            if(v.getId()==R.id.btn_log_in){
               intent = new Intent(LogInActivity.this,StudentStatusActivity.class);
            }else {
                intent = new Intent(LogInActivity.this, RegisterActivity.class);
            }
            startActivity(intent);

        }
    };
}
