package com.example.home.androidtestgame;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class TeacherCreateTestActivity extends AppCompatActivity {

    Button saveTest;
    Button cancelSavingTest;

    EditText et_dateFrom;
    EditText et_dateTo;
    int year_fromDate, month_fromDate, day_fromDate;
    int year_toDate, month_toDate, day_toDate;
    ArrayAdapter<String> adapter;
    ArrayList<String> items;
    ListView listViewQuestions;
    Spinner sp_questionType;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_test);

        saveTest =findViewById(R.id.btnSaveTest);
        cancelSavingTest = findViewById(R.id.btnCancelTest);

        et_dateFrom = findViewById(R.id.fromDateEditText);
        et_dateTo = findViewById(R.id.toDateEditText);
        listViewQuestions = findViewById(R.id.lv_questions);

        et_dateFrom.setOnClickListener(onClickListenerFromDate);
        et_dateTo.setOnClickListener(onClickListenerToDate);

        saveTest.setOnClickListener(onSaveTestListener);
        cancelSavingTest.setOnClickListener(onSaveTestListener);

        items = new ArrayList<>();
        items.add("Kak си ?");
        items.add("Какво правиш ?");
        items.add("На колко си ?");
        adapter = new ArrayAdapter<String>(this,R.layout.question_row,R.id.tv_question,items);
        listViewQuestions.setAdapter(adapter);
        sp_questionType = findViewById(R.id.sp_question);
        sp_questionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(TeacherCreateTestActivity.this,QuestionsPopupActivity.class);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    View.OnClickListener onClickListenerFromDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar calendarFromDate = Calendar.getInstance();
            year_fromDate = calendarFromDate.get(Calendar.YEAR);
            month_fromDate = calendarFromDate.get(Calendar.MONTH);
            day_fromDate = calendarFromDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(TeacherCreateTestActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year_fromDate, month_fromDate, day_fromDate);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = month + "/" + day + "/" + year;
                    et_dateFrom.setText(date);
                }
            };
        }
    };

    View.OnClickListener onClickListenerToDate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar calendarFromDate = Calendar.getInstance();
            year_toDate = calendarFromDate.get(Calendar.YEAR);
            month_toDate = calendarFromDate.get(Calendar.MONTH);
            day_toDate = calendarFromDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(TeacherCreateTestActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year_toDate, month_toDate, day_toDate);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = month + "/" + day + "/" + year;
                    et_dateTo.setText(date);

                }
            };
        }
    };

   View.OnClickListener onSaveTestListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           if(v.getId()==R.id.btnSaveTest){
               //TODO: Save Test
           }else{
               Intent intent = new Intent(TeacherCreateTestActivity.this,TeacherMenuActivity.class);
               startActivity(intent);
           }
       }
   } ;

}
