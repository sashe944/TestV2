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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherGiveGradeFragment extends Fragment {

    ListView studentGradeList;
    ArrayList<String> disciplineArrayList = new ArrayList<>();

    public TeacherGiveGradeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View gradeView = inflater.inflate(R.layout.fragment_teacher_give_grade, container, false);

        studentGradeList = gradeView.findViewById(R.id.lv_grades);

        disciplineArrayList.add("ANDROID");

        studentGradeList.setAdapter(new TeacherGiveGradeFragment.MyListAdapter(getContext(),R.layout.fragment_grading,disciplineArrayList));

        return gradeView;

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
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.grade = convertView.findViewById(R.id.btnGrade);
                viewHolder.look = convertView.findViewById(R.id.btnLook);
                viewHolder.cbIsGradedTest = convertView.findViewById(R.id.cbIsGradedTest);
              /*  if (!viewHolder.cbIsGradedTest.isChecked()) {
                    Toast.makeText(getContext(),"not checked",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(),"checked",Toast.LENGTH_LONG).show();
                }*/
                viewHolder.title = convertView.findViewById(R.id.discNameTextView);
                viewHolder.title.setText(disciplineArrayList.get(0));
                viewHolder.look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),TeacherViewTestResultsActivity.class);
                        startActivity(intent);
                    }
                });
                viewHolder.grade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),TeacherGradeStudentsAnswerActivity.class);
                        startActivity(intent);
                    }
                });
                convertView.setTag(viewHolder);
            }else{
                mainViewHolder = (TeacherGiveGradeFragment.ViewHolder) convertView.getTag();

            }
            return convertView;
        }
    }
    public  class ViewHolder {
        Button look;
        Button grade;
        TextView title;
        CheckBox cbIsGradedTest;
    }

}
