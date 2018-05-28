package com.example.home.androidtestgame.teacher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.home.androidtestgame.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherViewsHisOwnProfileFragment extends Fragment {

    Button buttonGoBack;

    public TeacherViewsHisOwnProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View teacherProfileView = inflater.inflate(R.layout.fragment_teacher_views_his_own_profile, container, false);


        buttonGoBack = teacherProfileView.findViewById(R.id.btn_back);
        //buttonGoBack.setOnClickListener(onBackClicked);
        //TODO: find a way to implement back press in fragment

        View.OnClickListener onBackClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onBackPressed();
            }
        };

        return teacherProfileView;
    }

}
