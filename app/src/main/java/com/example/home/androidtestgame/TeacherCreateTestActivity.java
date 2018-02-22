package com.example.home.androidtestgame;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

public class TeacherCreateTestActivity extends AppCompatActivity {

    EditText et_dateFrom;
    EditText et_dateTo;
    int year_fromDate, month_fromDate, day_fromDate;
    int year_toDate, month_toDate, day_toDate;

    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_test);

        et_dateFrom = findViewById(R.id.fromDateEditText);
        et_dateTo = findViewById(R.id.toDateEditText);

        et_dateFrom.setOnClickListener(onClickListenerFromDate);
        et_dateTo.setOnClickListener(onClickListenerToDate);

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
}
