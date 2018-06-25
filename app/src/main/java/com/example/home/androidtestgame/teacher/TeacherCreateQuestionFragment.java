package com.example.home.androidtestgame.teacher;


import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Question;
import com.example.home.androidtestgame.objects.QuestionType;
import com.example.home.androidtestgame.objects.Subject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCreateQuestionFragment extends Fragment {

    private static final String TAG = "TeacherCreateQuestionFr";

    private long selectedQuestionTypeId = -1;
    private long selectedTestHeaderId = -1;

    EditText questionEditText;

    Button saveQuestion;
    Spinner questionTypeSpinner;
    Spinner testHeaderSpinner;

    ArrayAdapter<QuestionType> answersAdapter;
    ArrayAdapter<Subject> subjectArrayAdapter;

    public TeacherCreateQuestionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View questionView = inflater.inflate(R.layout.fragment_teacher_create_question, container, false);

        saveQuestion = questionView.findViewById(R.id.btnSaveQuestion);
        questionTypeSpinner = questionView.findViewById(R.id.answersSpinner);
        questionEditText = questionView.findViewById(R.id.questionEditText);
        testHeaderSpinner = questionView.findViewById(R.id.testHeaderSpinner);


        saveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Question registeredQuestion = new Question();
                registeredQuestion.name = questionEditText.getText().toString();
                registeredQuestion.questionTypeId = selectedQuestionTypeId;
                registeredQuestion.testHeaderId = selectedTestHeaderId;

                TeacherGiveAnswerToQuestionFragment fragment = TeacherGiveAnswerToQuestionFragment.newInstance(registeredQuestion);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fillThisContainerWithFragment, fragment)
                        .commit();
            }
        });

        new GetQuestionTypeAsyncTask().execute();
        new GetTestHeaderAsyncTask().execute();
        return questionView;
    }


    private class GetQuestionTypeAsyncTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(getContext());

        String result;

        public GetQuestionTypeAsyncTask() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Login in....");
            dialogLogIn.setTitle("Взимане типовете за въпросите, моля изчакайте...");
            dialogLogIn.setCanceledOnTouchOutside(false);
            dialogLogIn.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "Getting questionTypes from the server");
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL( Constants.URL + "QuestionTypeFindServlet" );

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
            final List<QuestionType> questionTypes = gson.fromJson(result, new TypeToken<List<QuestionType>>(){}.getType());
            answersAdapter = new ArrayAdapter<QuestionType>(getContext(),android.R.layout.simple_list_item_1, questionTypes);
            questionTypeSpinner.setAdapter(answersAdapter);
            questionTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedQuestionTypeId = questionTypes.get(position).id;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }
    private class GetTestHeaderAsyncTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(getContext());

        String result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Login in....");
            dialogLogIn.setTitle("Взимане на дизциплините, моля изчакайте...");
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
            subjectArrayAdapter = new ArrayAdapter<Subject>(getContext(),android.R.layout.simple_list_item_1, testSubjects);
            testHeaderSpinner.setAdapter(subjectArrayAdapter);
            testHeaderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedTestHeaderId = testSubjects.get(position).id;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }



}
