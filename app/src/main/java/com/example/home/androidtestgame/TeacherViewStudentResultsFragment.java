package com.example.home.androidtestgame;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherViewStudentResultsFragment extends Fragment {


    public TeacherViewStudentResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View studentResultsView = inflater.inflate(R.layout.fragment_teacher_view_student_results, container, false);
        return studentResultsView;
    }

}
