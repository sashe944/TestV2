package com.example.home.androidtestgame.teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.PossibleAnswer;
import com.example.home.androidtestgame.objects.Question;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TeacherGiveAnswerToQuestionFragment extends Fragment {

    private static final String TAG = "TeacherGiverAnswerToQue";
    private static final String EXTRA_KEY_QUESTION = "key_question";

    EditText etAnswerName, etPoints;
    CheckBox cbIsCorrect;
    private Question question;

    public static TeacherGiveAnswerToQuestionFragment newInstance(Question question) {
        TeacherGiveAnswerToQuestionFragment fragment = new TeacherGiveAnswerToQuestionFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_KEY_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    public TeacherGiveAnswerToQuestionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        question = getArguments().getParcelable(EXTRA_KEY_QUESTION);

        View answersView = inflater.inflate(R.layout.fragment_teacher_giver_asnwer_to_question, container, false);


        etAnswerName = answersView.findViewById(R.id.answerEditText);
        etPoints = answersView.findViewById(R.id.pointsEditText);
        cbIsCorrect = answersView.findViewById(R.id.isCorrectAnswerCheckBox);

        answersView.findViewById(R.id.btn_save_question_with_all_answers).setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
            @Override
            public void onClick(View v) {
                getNewAnswerInfoFromUI();
                new RegisterQuestionAsyncTask().execute(question);
                startActivity(intent);
            }
        });

        answersView.findViewById(R.id.btn_new_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewAnswerInfoFromUI();
                clearUiElements();
            }
        });


        return answersView;
    }

    private void getNewAnswerInfoFromUI() {
        PossibleAnswer answer = new PossibleAnswer();
        answer.name = etAnswerName.getText().toString();
        answer.isCorrect = cbIsCorrect.isChecked() ? 1 : 0;
        answer.points = Integer.parseInt(etPoints.getText().toString());
        question.possibleAnswers.add(answer);
    }

    private void clearUiElements() {
        etAnswerName.setText(null);
        cbIsCorrect.setChecked(false);
        etPoints.setText(null);
    }

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
        }
    }

}
