package com.example.home.androidtestgame;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TeacherGradeStudentsAnswerActivity extends AppCompatActivity {

    ListView lv_stuAnswers;
    ArrayList<String> answersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_grade_students_answer);

        lv_stuAnswers = findViewById(R.id.lv_students_answers_to_questions);

        answersList.add("Erhan Mustafa");
       /* answersList.add("1701737009");
        answersList.add("Отворена операционна система!");*/

        lv_stuAnswers.setAdapter(new MyListAdapter(this,R.layout.student_answer_layout,answersList));
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
                viewHolder.btn_points = convertView.findViewById(R.id.buttonPoints);
                viewHolder.btn_comment = convertView.findViewById(R.id.buttonComment);
                viewHolder.stu_answer = convertView.findViewById(R.id.et_student_answer);
                viewHolder.student_name = convertView.findViewById(R.id.tv_student_name);
                viewHolder.student_fn = convertView.findViewById(R.id.tv_faculty_number);

                viewHolder.student_name.setText("Erhan Mustafa");
                viewHolder.student_fn.setText("1701737009");
                viewHolder.stu_answer.setText("Отворена операционна система!");

               viewHolder.btn_points.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 24.2.2018 г.  Make new Activity with the proper needed things for look test
                    }
                });
                viewHolder.btn_comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TeacherGradeStudentsAnswerActivity.this,"pressed button",Toast.LENGTH_LONG).show();
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
        Button btn_comment;
        Button btn_points;
        TextView student_name;
        TextView student_fn;
        EditText stu_answer;
    }
}
