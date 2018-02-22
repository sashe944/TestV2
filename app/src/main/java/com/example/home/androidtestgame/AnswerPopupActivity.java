package com.example.home.androidtestgame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * Created by Home on 21.2.2018 г..
 */

public class AnswerPopupActivity extends AppCompatActivity {

    Button addAnswer;
    Button close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_popup_dialog);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        addAnswer = findViewById(R.id.btnAddAnswer);
        close = findViewById(R.id.btnCancelAnswer);

        close.setOnClickListener(closeListener);
    }
    View.OnClickListener closeListener = new View.OnClickListener() {
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
