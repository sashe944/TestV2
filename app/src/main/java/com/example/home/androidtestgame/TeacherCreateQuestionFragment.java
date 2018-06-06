package com.example.home.androidtestgame;


import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Spinner;

import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Question;
import com.example.home.androidtestgame.objects.QuestionType;
import com.example.home.androidtestgame.objects.Subject;
import com.example.home.androidtestgame.objects.TestHeader;
import com.example.home.androidtestgame.teacher.TeacherMenuActivity;
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
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCreateQuestionFragment extends Fragment {

    private static final String TAG = "TeacherCreateQuestionFr";

    private long selectedQuestionTypeId = -1;
    EditText questionEditText;

    private long questionTypeId;
    private long testHeaderId;
    Button giveAnswer;
    Button saveQuestion;
    Button cancelSavingQuestion;
    Spinner questionTypeSpinner;

    ListView lv_test_answers;
    ArrayAdapter<QuestionType> answersAdapter;
    ArrayList<String> listOfAnswers = new ArrayList<>();

    public TeacherCreateQuestionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View questionView = inflater.inflate(R.layout.fragment_teacher_create_question, container, false);

        /*lv_test_answers= questionView.findViewById(R.id.lv_answers);*/

       // cancelSavingQuestion = questionView.findViewById(R.id.btnCancelQuestion);

        saveQuestion = questionView.findViewById(R.id.btnSaveQuestion);
        questionTypeSpinner = questionView.findViewById(R.id.answersSpinner);
        questionEditText = questionView.findViewById(R.id.questionEditText);

      /*  listOfAnswers.add("Добре съм");
        listOfAnswers.add("На 23 години съм"); */

        //answersAdapter = new ArrayAdapter<String>(getContext(),R.layout.questions_layout,R.id.tv_question_add, listOfAnswers);

        //lv_test_answers.setAdapter(answersAdapter);

        //giveAnswer = questionView.findViewById(R.id.btn_write_answer);

        saveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: initialize the AsyncTask here

                Question registeredQuestion = new Question();
                registeredQuestion.name = questionEditText.getText().toString();
                registeredQuestion.questionTypeId = selectedQuestionTypeId;
                registeredQuestion.testHeaderId = 0 ;


                new RegisterQuestionAsyncTask().execute(registeredQuestion);
            }
        });

        /*giveAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AnswerPopupActivity.class);
                startActivity(intent);
            }
        });*/
       /* cancelSavingQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                startActivity(intent);
            }
        });*/

        new GetQuestionTypeAsyncTask().execute();
        return questionView;
    }
    //TODO: HTTP POST HERE for the questions
    private class RegisterQuestionAsyncTask extends AsyncTask<Question, Void, String> {

        ProgressDialog registerProgressDialog = new ProgressDialog(getContext());


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            registerProgressDialog.setTitle("Registering question! Please wait...!");
            registerProgressDialog.setCanceledOnTouchOutside(false);
            registerProgressDialog.show();
        }

        @Override
        protected String doInBackground(Question... question) {
            Log.d(TAG, "Getting question from the server");
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL( Constants.URL + "QuestionRegisterServlet" );

                Log.d(TAG, "url: " + url.toString());
                urlConnection = (HttpURLConnection)
                        url.openConnection();
                urlConnection.setRequestMethod("POST");

                String jsonQuestion = new GsonBuilder().create().toJson(question[0]);
                byte[] outputInBytes = jsonQuestion.getBytes("UTF-8");
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
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            registerProgressDialog.dismiss();
            QuestionType takenQuestionType = new GsonBuilder().create().fromJson(result, QuestionType.class);
            questionTypeId = takenQuestionType.id;
            TestHeader testHeader = new GsonBuilder().create().fromJson(result, TestHeader.class);
           testHeaderId = testHeader.id;

        }
    }
    //TODO: HTTP GET QuestionType
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
            dialogLogIn.setTitle("Login in please wait!");
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



}
