package com.example.home.androidtestgame.student;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.Fragment;

import com.example.home.androidtestgame.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OldTestsFragment extends Fragment {


    public OldTestsFragment() {
        // Required empty public constructor
    }

    Button backButton;
    ListView lvStudentScore;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View oldTestsView =  inflater.inflate(R.layout.fragment_old_tests, container, false);


        lvStudentScore = oldTestsView.findViewById(R.id.lv_moreInfo);

        items.add("ANDROID 6");

        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                items);

        lvStudentScore.setAdapter(adapter);
        backButton = oldTestsView.findViewById(R.id.button_back);
       // backButton.setOnClickListener(backPress);
        return oldTestsView;
    }

  /*  View.OnClickListener backPress = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    public void onBackPressed() {
        onBackPressed();
    }
*/
}
