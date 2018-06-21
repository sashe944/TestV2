package com.example.home.androidtestgame.student;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.home.androidtestgame.App;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Question;
import com.example.home.androidtestgame.objects.Teacher;
import com.example.home.androidtestgame.objects.TestHeader;
import com.example.home.androidtestgame.student.MenuActivity;
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
public class TestFragment extends Fragment {

    private static final String TAG = "TestFragment";
    private static final String ARG_KEY_TEST_ID = "TEST_ID";

    Button give;

    public static TestFragment newInstance(long testHeaderId) {
        TestFragment fragment = new TestFragment();
        Bundle args  = new Bundle();
        args.putLong(ARG_KEY_TEST_ID, testHeaderId);
        fragment.setArguments(args);
        return fragment;
    }

    public TestFragment() {
        // Required empty public constructor
    }

    private long getTestId() {
        return getArguments().getLong(ARG_KEY_TEST_ID);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetQuestionsTask().execute(getTestId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View testView = inflater.inflate(R.layout.fragment_test, container, false);

        give = testView.findViewById(R.id.buttonGiveToTeacher);
        give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MenuActivity.class);
                startActivity(intent);
            }
        });

        return testView;
        //TODO: HTTP GET QUESTIONS AND ANSWERS BASED ON TESTHEADER(ACTUAL TEST)
        //(
           //1.1 Questions: SELECT * FROM Question WHERE TestHeaderID = 12 (get test)
           //1.2 QuestionTypes: SELECT * FROM Question WHERE QuestionTypeID = 1 (get question)
           //1.3 PossibleAnswers:SELECT * FROM PossibleAnswers WHERE QuestionID = 31 (get answer)
        //)

    }

    private class GetQuestionsTask extends AsyncTask<Long, Void, String> {

        ProgressDialog dialogLogIn = new ProgressDialog(getContext());

        public GetQuestionsTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Getting questions....");
            dialogLogIn.setTitle("Getting questions, please wait!");
            dialogLogIn.setCanceledOnTouchOutside(false);
            dialogLogIn.show();
        }

        @Override
        protected String doInBackground(Long... params) {
            Log.d(TAG, "Getting qustions from the server");
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{

                Uri builtUri = Uri.parse(Constants.URL + "QuestionFindServlet")
                        .buildUpon()
                        .appendQueryParameter("testId", String.valueOf(params[0]))
                        .build();

                url = new URL( builtUri.toString());

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

                return content.toString();
            }catch (MalformedURLException e){
                Log.wtf("WRONG!", e.getMessage());
            }catch(IOException e){
                Log.wtf("WRONG!", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d(TAG, "taking data");
            Log.d(TAG, "result: " + result);
            super.onPostExecute(result);
            dialogLogIn.dismiss();

            final List<Question> questions = new GsonBuilder().create().fromJson(result, new TypeToken<List<Question>>(){}.getType());

            RecyclerView recyclerView = (RecyclerView ) getView().findViewById(R.id.rvQuestions);

            QuestionsAdapter mAdapter = new QuestionsAdapter(questions);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);


            // get server response (teacher details)

            //List<Question> questions = new GsonBuilder().create().fromJson(result, Teacher.class);

            // populate teacher info on UI

        }
    }


}
