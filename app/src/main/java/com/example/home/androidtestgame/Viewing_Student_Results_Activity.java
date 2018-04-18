package com.example.home.androidtestgame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Home on 18.4.2018 г..
 */

public class Viewing_Student_Results_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing__student__results_);

        ViewingStudentResultsFragment viewingStudentResultsFragment = new ViewingStudentResultsFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.viewingResultsContainer,viewingStudentResultsFragment);
        fragmentTransaction.commit();
    }
}
