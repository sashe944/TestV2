package com.example.home.androidtestgame.student;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.student.MenuActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    Button give;

    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View testView = inflater.inflate(R.layout.fragment_test, container, false);

        give = testView.findViewById(R.id.buttonGiveToTeacher);
        give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MenuActivity.class);
                startActivity(intent);
            }
        });

        return testView;
    }

}
