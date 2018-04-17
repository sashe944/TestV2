package com.example.home.androidtestgame;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCreateTestFragment extends Fragment {

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

    public TeacherCreateTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View createTestView = inflater.inflate(R.layout.fragment_teacher_create_test, container, false);

        saveTest =createTestView.findViewById(R.id.btnSaveTest);
        cancelSavingTest = createTestView.findViewById(R.id.btnCancelTest);

        et_dateFrom = createTestView.findViewById(R.id.fromDateEditText);
        et_dateTo = createTestView.findViewById(R.id.toDateEditText);
        listViewQuestions = createTestView.findViewById(R.id.lv_questions);

        et_dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendarFromDate = Calendar.getInstance();
                year_fromDate = calendarFromDate.get(Calendar.YEAR);
                month_fromDate = calendarFromDate.get(Calendar.MONTH);
                day_fromDate = calendarFromDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year_fromDate, month_fromDate, day_fromDate);
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
        });
        et_dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendarFromDate = Calendar.getInstance();
                year_toDate = calendarFromDate.get(Calendar.YEAR);
                month_toDate = calendarFromDate.get(Calendar.MONTH);
                day_toDate = calendarFromDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year_toDate, month_toDate, day_toDate);
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
        });

        saveTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SaveTest
            }
        });
        cancelSavingTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                startActivity(intent);
            }
        });

        items = new ArrayList<>();
        items.add("Kak си ?");
        items.add("Какво правиш ?");
        items.add("На колко си ?");
        adapter = new ArrayAdapter<String>(getContext(),R.layout.question_row,R.id.tv_question,items);
        listViewQuestions.setAdapter(adapter);
        sp_questionType = createTestView.findViewById(R.id.sp_question);
        sp_questionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               Intent intent = new Intent(getContext(),QuestionsPopupActivity.class);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        return createTestView;
    }

}
