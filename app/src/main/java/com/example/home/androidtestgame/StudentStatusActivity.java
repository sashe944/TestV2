package com.example.home.androidtestgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Home on 17.4.2018 г..
 */

public class StudentStatusActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_status);

       StudentStatusFragment statusFragment = new StudentStatusFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.studentStatusFragmentContainer, statusFragment);
        fragmentTransaction.commit();
    }
}
