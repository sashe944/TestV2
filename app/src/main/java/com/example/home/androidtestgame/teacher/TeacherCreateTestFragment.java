package com.example.home.androidtestgame.teacher;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import com.example.home.androidtestgame.QuestionsPopupActivity;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Subject;
import com.example.home.androidtestgame.teacher.TeacherMenuActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCreateTestFragment extends Fragment {

    private static final String TAG = "TeacherCreateTestFragme";

    Button saveTest;
    Button cancelSavingTest;

    EditText et_dateFrom;
    EditText et_dateTo;
    int year_fromDate, month_fromDate, day_fromDate;
    int year_toDate, month_toDate, day_toDate;
    ArrayAdapter<String> questionAdapter;
    ArrayAdapter<Subject> disciplineAdapter;
    ArrayList<String> testQuestions;
    ListView listViewQuestions;
    Spinner sp_questionType;
    Spinner disciplineSpinner;
    DatePickerDialog.OnDateSetListener dateSetListener;

    public TeacherCreateTestFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View createTestView = inflater.inflate(R.layout.fragment_teacher_create_test, container, false);

        saveTest =createTestView.findViewById(R.id.btnSaveTest);
        cancelSavingTest = createTestView.findViewById(R.id.btnCancelTest);

         disciplineSpinner = createTestView.findViewById(R.id.sp_disciplines);

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

        testQuestions = new ArrayList<>();
        testQuestions.add("Kak си ?");
        testQuestions.add("Какво правиш ?");
        testQuestions.add("На колко си ?");
        questionAdapter = new ArrayAdapter<String>(getContext(),R.layout.question_row,R.id.tv_question, testQuestions);
        listViewQuestions.setAdapter(questionAdapter);
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
         new GetSubjectsAsyncTask().execute();
        return createTestView;
    }
    private class GetSubjectsAsyncTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(getContext());

        String result;

    public GetSubjectsAsyncTask() {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "Login in....");
        dialogLogIn.setTitle("Login in please wait!");
        dialogLogIn.setCanceledOnTouchOutside(false);
        dialogLogIn.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "Getting subjects from the server");
        URL url;
        HttpURLConnection urlConnection;
        BufferedReader br;

        try{
            url = new URL( Constants.URL + "SubjectFindServlet" );

            Log.d(TAG, "url: " + url.toString());
            urlConnection = (HttpURLConnection)
                    url.openConnection();
            urlConnection.setRequestMethod("GET");


            br = new BufferedReader
                    (new InputStreamReader(
                            urlConnection.getInputStream()
                    ));

            StringBuilder content = new StringBuilder();

            String line = br.readLine();
            while(line != null){
                content.append(line);
                line = br.readLine();
            }

            result = content.toString();
        }catch (MalformedURLException e){
            Log.wtf("WRONG!", e.getMessage());
        }catch(IOException e){
            Log.wtf("WRONG!", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d(TAG, "taking data");
        Log.d(TAG, "result: " + result);
        super.onPostExecute(aVoid);
        dialogLogIn.dismiss();
        Gson gson = new GsonBuilder().create();
        List<Subject> testSubjects = gson.fromJson(result, new TypeToken<List<Subject>>(){}.getType());
        disciplineAdapter = new ArrayAdapter<Subject>(getContext(),android.R.layout.simple_list_item_1, testSubjects);
        disciplineSpinner.setAdapter(disciplineAdapter);

    }
}
//TODO: ANOTHER HTTP GET HERE for the questions with the answers

}
