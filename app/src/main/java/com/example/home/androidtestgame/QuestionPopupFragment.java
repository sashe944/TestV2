package com.example.home.androidtestgame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
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
public class QuestionPopupFragment extends Fragment {

    Button ok;
    Button cancel;
    ArrayAdapter<String> adapter;
    ArrayList<String> items;
    ListView lv_add_questions;


    public QuestionPopupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View questionPopupView =  inflater.inflate(R.layout.fragment_question_popup, container, false);


        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        lv_add_questions = questionPopupView.findViewById(R.id.custom_lv_questions);


        getActivity().getWindow().setLayout((int)(width*.8),(int)(height*.6));

        ok=questionPopupView.findViewById(R.id.buttonOk);
        cancel=questionPopupView.findViewById(R.id.buttonCancel);

        items = new ArrayList<>();
        items.add("Kak си ?");
        items.add("Какво правиш ?");
        items.add("На колко си ?");

        adapter = new ArrayAdapter<String>(getContext(),R.layout.questions_layout,R.id.tv_question_add,items);
        lv_add_questions.setAdapter(adapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return questionPopupView;
    }

}
