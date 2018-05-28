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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.home.androidtestgame.teacher.TeacherMenuActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherGradeStudentAnswerFragment extends Fragment {

    ListView lv_stuAnswers;
    ArrayList<String> answersList = new ArrayList<>();
    Button cancelGivingGrade;


    public TeacherGradeStudentAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View studentAnswerView = inflater.inflate(R.layout.fragment_teacher_grade_student_answer, container, false);


        lv_stuAnswers = studentAnswerView.findViewById(R.id.lv_students_answers_to_questions);

        cancelGivingGrade = studentAnswerView.findViewById(R.id.buttonCancel);
        cancelGivingGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                startActivity(intent);
            }
        });

        answersList.add("Erhan Mustafa");
       /* answersList.add("1701737009");
        answersList.add("Отворена операционна система!");*/

        lv_stuAnswers.setAdapter(new TeacherGradeStudentAnswerFragment.MyListAdapter(getContext(),R.layout.fragment_student_answer,answersList));

        return studentAnswerView;
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
                TeacherGradeStudentAnswerFragment.ViewHolder viewHolder = new TeacherGradeStudentAnswerFragment.ViewHolder();
                viewHolder.et_points = convertView.findViewById(R.id.etAddPoints);
                viewHolder.et_comment = convertView.findViewById(R.id.etAddComment);
                viewHolder.stu_answer = convertView.findViewById(R.id.et_view_student_answer);
                viewHolder.student_name = convertView.findViewById(R.id.tv_view_student_name);
                viewHolder.student_fn = convertView.findViewById(R.id.tv_faculty_number);

                viewHolder.student_name.setText("Erhan Mustafa");
                viewHolder.student_fn.setText("1701737009");
                viewHolder.stu_answer.setText("Отворена операционна система!");
                viewHolder.stu_answer.setEnabled(false);
                viewHolder.et_comment.setEnabled(true);
                viewHolder.et_points.setEnabled(true);

                convertView.setTag(viewHolder);
            }else{
                mainViewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }
    public  class ViewHolder {
        EditText et_comment;
        EditText et_points;
        TextView student_name;
        TextView student_fn;
        EditText stu_answer;
    }

}
