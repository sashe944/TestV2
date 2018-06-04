package com.example.home.androidtestgame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerPopupFragment extends Fragment {

    Button addAnswer;
    Button close;

    public AnswerPopupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View answerPopupView = inflater.inflate(R.layout.fragment_answer_popup, container, false);


        DisplayMetrics dm = new DisplayMetrics();
       getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getActivity().getWindow().setLayout((int)(width*.8),(int)(height*.6));

        addAnswer = answerPopupView.findViewById(R.id.btnAddAnswer);
        close = answerPopupView.findViewById(R.id.btnCancelAnswer);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Exit
            }
        });

        return answerPopupView;

    }
    //TODO: HTTP POST Here for the answers

}
