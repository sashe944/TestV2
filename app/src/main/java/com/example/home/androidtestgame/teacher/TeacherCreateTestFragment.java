package com.example.home.androidtestgame.teacher;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.Spinner;

import com.example.home.androidtestgame.App;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Subject;
import com.example.home.androidtestgame.objects.TestHeader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCreateTestFragment extends Fragment {

    private static final String TAG = "TeacherCreateTestFragme";

    private long selectedDisciplineId = -1;

    Button saveTest;
    Button cancelSavingTest;
    EditText et_dateFrom;
    EditText et_dateTo, etTestName;
    int year_fromDate, month_fromDate, day_fromDate;
    int year_toDate, month_toDate, day_toDate;
    ArrayAdapter<Subject> disciplineAdapter;
    Spinner disciplineSpinner;
    String dateFrom, dateTo;
    DatePickerDialog.OnDateSetListener dateFromSetListener,dateToSetListener;

    public TeacherCreateTestFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View createTestView = inflater.inflate(R.layout.fragment_teacher_create_test, container, false);
        saveTest =createTestView.findViewById(R.id.btnSaveTest);
        cancelSavingTest = createTestView.findViewById(R.id.btnCancelTest);

         disciplineSpinner = createTestView.findViewById(R.id.sp_disciplines);
        etTestName = createTestView.findViewById(R.id.testNameEditText);
        et_dateFrom = createTestView.findViewById(R.id.fromDateEditText);
        et_dateTo = createTestView.findViewById(R.id.toDateEditText);

        et_dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendarToDate = Calendar.getInstance();
                year_toDate = calendarToDate.get(Calendar.YEAR);
                month_toDate = calendarToDate.get(Calendar.MONTH);
                day_toDate = calendarToDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateToSetListener, year_toDate, month_toDate, day_toDate);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dateToSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearTo, int monthTo, int dayTo) {
                        monthTo = monthTo + 1;
                        dateTo = dayTo + "/" + monthTo + "/" + yearTo;
                        et_dateTo.setText(dateTo);
                    }
                };
            }
        });
        et_dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendarFromDate = Calendar.getInstance();
                year_fromDate = calendarFromDate.get(Calendar.YEAR);
                month_fromDate = calendarFromDate.get(Calendar.MONTH);
                day_fromDate = calendarFromDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateFromSetListener, year_fromDate, month_fromDate, day_fromDate);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();

                dateFromSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearFrom, int monthFrom, int dayFrom) {
                        monthFrom = monthFrom + 1;
                        dateFrom = dayFrom + "/" + monthFrom + "/" + yearFrom;
                        et_dateFrom.setText(dateFrom);
                    }
                };
            }
        });

        saveTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                TestHeader testHeader = new TestHeader();

                testHeader.testName = etTestName.getText().toString();
                testHeader.subjectID = selectedDisciplineId;
                testHeader.userID = App.loggedUserId;
                testHeader.fromDate = dateFrom;
                testHeader.toDate = dateTo;

                new CreateTestHeaderAsyncTask().execute(testHeader);
                startActivity(intent);
            }
        });
        cancelSavingTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                startActivity(intent);
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
        Log.d(TAG, "Geting subjects....");
        dialogLogIn.setTitle("Взимане на всички налични дисциплини, моля изчакайте...");
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
        final List<Subject> testSubjects = gson.fromJson(result, new TypeToken<List<Subject>>(){}.getType());
        disciplineAdapter = new ArrayAdapter<Subject>(getContext(),android.R.layout.simple_list_item_1, testSubjects);
        disciplineSpinner.setAdapter(disciplineAdapter);
        disciplineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDisciplineId = testSubjects.get(position).id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
    private  class CreateTestHeaderAsyncTask extends AsyncTask<TestHeader, Void, String> {
        ProgressDialog dialogCreateTest =
                new ProgressDialog(getContext());

        String result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Create test");
            dialogCreateTest.setTitle("Създаване на теста, моля изчакайте...");
            dialogCreateTest.setCanceledOnTouchOutside(false);
            dialogCreateTest.show();
        }

        @Override
        protected String doInBackground(TestHeader... testHeaders) {
            Log.d(TAG, "Getting test from the server");
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL( Constants.URL + "TestRegisterServlet" );

                Log.d(TAG, "url: " + url.toString());
                urlConnection = (HttpURLConnection)
                        url.openConnection();
                urlConnection.setRequestMethod("POST");

                String jsonTestHeader = new GsonBuilder().create().toJson(testHeaders[0]);
                byte[] outputInBytes = jsonTestHeader.getBytes("UTF-8");
                OutputStream os = urlConnection.getOutputStream();
                os.write( outputInBytes );
                os.close();

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

                return  content.toString();
            }catch (MalformedURLException e){
                Log.wtf("WRONG!", e.getMessage());
            }catch(IOException e){
                Log.wtf("WRONG!", e.getMessage());
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "tes header: " + result);
            dialogCreateTest.dismiss();
        }
    }

}
