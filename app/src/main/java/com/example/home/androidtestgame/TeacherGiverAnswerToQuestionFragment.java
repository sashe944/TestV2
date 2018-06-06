package com.example.home.androidtestgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

public class TeacherGiverAnswerToQuestionFragment extends Fragment {


    EditText answer,points;
    CheckBox isCorrect;

    public TeacherGiverAnswerToQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View answersView = inflater.inflate(R.layout.fragment_teacher_giver_asnwer_to_question, container, false);


        answer = answersView.findViewById(R.id.answerEditText);
        points = answersView.findViewById(R.id.pointsEditText);
        isCorrect = answersView.findViewById(R.id.isCorrectAnswerCheckBox);


        return answersView;
    }


}
