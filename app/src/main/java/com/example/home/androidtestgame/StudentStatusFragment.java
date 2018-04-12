package com.example.home.androidtestgame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentStatusFragment extends Fragment {

    ListView lvStudentInfo;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter adapter;

    public StudentStatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View studentStatusView =inflater.inflate(R.layout.fragment_student_status, container, false);

        lvStudentInfo = studentStatusView.findViewById(R.id.lv_studentInfo);

        items.add("10.02.2005 PL/SQL 20");
        items.add("10.02.2005 C 20");
        items.add("10.02.2005 C# 20");
        items.add("10.02.2005 JAVA 20");
        items.add("10.02.2005 ANDROID 30");
        items.add("10.02.2005 PHP 10");
        items.add("10.02.2005 RUBY 20");
        items.add("10.02.2005 GRAILS 20");
        items.add("10.02.2005 RESTFUL 20");
        items.add("10.02.2005 T-SQL 20");

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,items);

        lvStudentInfo.setAdapter(adapter);

        return studentStatusView;
    }

}
