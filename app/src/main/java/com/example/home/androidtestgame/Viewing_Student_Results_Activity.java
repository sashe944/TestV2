package com.example.home.androidtestgame;

import android.content.Context;
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

public class Viewing_Student_Results_Activity extends AppCompatActivity {

    ListView viewResults;
    ArrayList<String> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing__student__results_);

        viewResults = findViewById(R.id.lv_view_results);

        viewResults.setAdapter(new MyListAdapter(this,R.layout.activity_view,resultList));

        resultList.add("Erhan Mustafa");

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
                mainViewHolder = (ViewHolder) convertView.getTag();
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
