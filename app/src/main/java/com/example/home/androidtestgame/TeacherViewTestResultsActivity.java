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

public class TeacherViewTestResultsActivity extends AppCompatActivity {

    ListView lv_view_student_results;
    ArrayList<String> results = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_test_results);

        lv_view_student_results = findViewById(R.id.lv_view_results);

        results.add("Erhan Mustafa");
        /*results.add("1701737009");*/

        lv_view_student_results.setAdapter(new MyListAdapter(this,R.layout.activity_teacher_view_student_results,results));
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
            TeacherGradeStudentsAnswerActivity.ViewHolder mainViewHolder = null;
            if(convertView==null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout,parent,false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.btn_viewTest = convertView.findViewById(R.id.buttonView);
                viewHolder.viewStudentFacultyNumber = convertView.findViewById(R.id.tv_view_faculty_number);
                viewHolder.viewStudentName = convertView.findViewById(R.id.tv_view_student_name);

                //text here
                viewHolder.viewStudentName.setText("Erhan Mustafa");
                viewHolder.viewStudentFacultyNumber.setText("1701737009");

                viewHolder.btn_viewTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TeacherViewTestResultsActivity.this, Viewing_Student_Results_Activity.class);
                        startActivity(intent);
                    }
                });

                convertView.setTag(viewHolder);
            }else{
                mainViewHolder = (TeacherGradeStudentsAnswerActivity.ViewHolder) convertView.getTag();
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
