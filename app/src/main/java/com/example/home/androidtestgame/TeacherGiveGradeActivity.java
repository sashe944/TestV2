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
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TeacherGiveGradeActivity extends AppCompatActivity {

    ListView studentGradeList;
    ArrayList<String> disciplineArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_give_grade);

        studentGradeList = findViewById(R.id.lv_grades);

        disciplineArrayList.add("ANDROID");

        studentGradeList.setAdapter(new MyListAdapter(this,R.layout.grading_layout,disciplineArrayList));

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
                viewHolder.title = convertView.findViewById(R.id.discNameTextView);
                viewHolder.title.setText(disciplineArrayList.get(0));
                viewHolder.look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 24.2.2018 г.  Make new Activity with the proper needed things for look test
                    }
                });
                viewHolder.grade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TeacherGiveGradeActivity.this,TeacherGradeStudentsAnswerActivity.class);
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
        Button look;
        Button grade;
        TextView title;
    }
}
