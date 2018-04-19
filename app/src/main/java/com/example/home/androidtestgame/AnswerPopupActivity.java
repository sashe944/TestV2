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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_popup_dialog);

        AnswerPopupFragment answerPopupFragment = new AnswerPopupFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.answerPopupContainer,answerPopupFragment);
        fragmentTransaction.commit();
    }

}
