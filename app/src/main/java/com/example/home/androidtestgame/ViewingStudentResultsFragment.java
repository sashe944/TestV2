package com.example.home.androidtestgame;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewingStudentResultsFragment extends Fragment {

    ListView viewResults;
    ArrayList<String> resultList = new ArrayList<>();

    public ViewingStudentResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View viewingStudentResultsView = inflater.inflate(R.layout.fragment_viewing_student_results, container, false);


        viewResults = viewingStudentResultsView.findViewById(R.id.lv_view_results);

        viewResults.setAdapter(new ViewingStudentResultsFragment.MyListAdapter(getContext(),R.layout.fragment_view_results,resultList));

        resultList.add("Erhan Mustafa");

        return viewingStudentResultsView;
    }

    private class MyListAdapter extends ArrayAdapter<String> {

        private int layout;

        public MyListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
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
                ViewingStudentResultsFragment.ViewHolder viewHolder = new ViewingStudentResultsFragment.ViewHolder();

                viewHolder.et_view_comment = convertView.findViewById(R.id.etViewComment);
                viewHolder.et_view_points = convertView.findViewById(R.id.etViewPoints);

                viewHolder.view_student_name = convertView.findViewById(R.id.tv_view_student_name);
                viewHolder.view_student_fn = convertView.findViewById(R.id.tv_view_faculty_number);
                viewHolder.view_stu_answer = convertView.findViewById(R.id.et_view_student_answer);

                viewHolder.et_view_points.setEnabled(false);
                viewHolder.et_view_comment.setEnabled(false);

                viewHolder.view_student_name.setText("Erhan Mustafa");
                viewHolder.view_student_fn.setText("1701737009");
                viewHolder.view_stu_answer.setText("Отворена операционна система!");
                viewHolder.view_stu_answer.setEnabled(false);
                convertView.setTag(viewHolder);
            }else{
                mainViewHolder = (ViewingStudentResultsFragment.ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }
    public  class ViewHolder {
        EditText et_view_comment;
        EditText et_view_points;
        TextView view_student_name;
        TextView view_student_fn;
        EditText view_stu_answer;
    }

}
