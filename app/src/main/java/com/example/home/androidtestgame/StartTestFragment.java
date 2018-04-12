package com.example.home.androidtestgame;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartTestFragment extends Fragment {


    public StartTestFragment() {
        // Required empty public constructor
    }


    ListView lvStartTest;
    ArrayList<String> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View startTestView = inflater.inflate(R.layout.fragment_start_test, container, false);

        items = new ArrayList<>();

        items.add("ANDROID");

        lvStartTest = startTestView.findViewById(R.id.lv_choose_test);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.single_row,R.id.textView,items);
        lvStartTest.setAdapter(adapter);

        lvStartTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),TestActivity.class);
                startActivity(intent);
            }
        });

        return startTestView;
    }

}
