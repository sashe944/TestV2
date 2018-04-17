package com.example.home.androidtestgame;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCreateQuestionFragment extends Fragment {

    Button giveAnswer;
    Button saveQuestion;
    Button cancelSavingQuestion;

    ListView lv_test_answers;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();

    public TeacherCreateQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View questionView = inflater.inflate(R.layout.fragment_teacher_create_question, container, false);

        lv_test_answers= questionView.findViewById(R.id.lv_answers);
        cancelSavingQuestion = questionView.findViewById(R.id.btnCancelQuestion);

        list.add("Добре съм");
        list.add("На 23 години съм");

        adapter = new ArrayAdapter<String>(getContext(),R.layout.questions_layout,R.id.tv_question_add,list);

        lv_test_answers.setAdapter(adapter);

        giveAnswer = questionView.findViewById(R.id.btn_write_answer);

        giveAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AnswerPopupActivity.class);
                startActivity(intent);
            }
        });
        cancelSavingQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                startActivity(intent);
            }
        });







        return questionView;
    }

}
