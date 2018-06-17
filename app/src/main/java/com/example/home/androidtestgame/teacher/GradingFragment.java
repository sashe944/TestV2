package com.example.home.androidtestgame.teacher;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home.androidtestgame.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GradingFragment extends Fragment {


    public GradingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View greadingView = inflater.inflate(R.layout.fragment_grading, container, false);

        return greadingView;
    }

}
