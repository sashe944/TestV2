package com.example.home.androidtestgame;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherViewTestResultsFragment extends Fragment {

    ListView lv_view_student_results;
    ArrayList<String> results = new ArrayList<>();

    public TeacherViewTestResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View testResultsView = inflater.inflate(R.layout.fragment_teacher_view_test_results, container, false);


        lv_view_student_results = testResultsView.findViewById(R.id.lv_view_results);

        results.add("Erhan Mustafa");
        /*results.add("1701737009");*/

        lv_view_student_results.setAdapter(new TeacherViewTestResultsFragment.MyListAdapter(getContext(),R.layout.activity_teacher_view_student_results,results));

        return testResultsView;
    }
    private class MyListAdapter extends ArrayAdapter<String> {

        private int layout;

        private MyListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if(convertView==null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout,parent,false);
                TeacherViewTestResultsFragment.ViewHolder viewHolder = new TeacherViewTestResultsFragment.ViewHolder();
                viewHolder.btn_viewTest = convertView.findViewById(R.id.buttonView);
                viewHolder.viewStudentFacultyNumber = convertView.findViewById(R.id.tv_view_faculty_number);
                viewHolder.viewStudentName = convertView.findViewById(R.id.tv_view_student_name);

                //text here
                viewHolder.viewStudentName.setText("Erhan Mustafa");
                viewHolder.viewStudentFacultyNumber.setText("1701737009");

                viewHolder.btn_viewTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), Viewing_Student_Results_Activity.class);
                        startActivity(intent);
                    }
                });

                convertView.setTag(viewHolder);
            }else{
                mainViewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }
    public  class ViewHolder {
        Button btn_viewTest;
        TextView viewStudentName;
        TextView viewStudentFacultyNumber;
    }

}
