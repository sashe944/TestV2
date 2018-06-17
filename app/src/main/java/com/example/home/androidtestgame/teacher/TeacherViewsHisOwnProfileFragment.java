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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.home.androidtestgame.App;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Subject;
import com.example.home.androidtestgame.objects.Teacher;
import com.example.home.androidtestgame.objects.User;
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
public class TeacherViewsHisOwnProfileFragment extends Fragment {

    Button buttonGoBack;
    TextView teacherName,teacherEmail;
    String name, email;
    private static final String TAG = "TeacherViewsHisOwnProfi";

    public TeacherViewsHisOwnProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View teacherProfileView = inflater.inflate(R.layout.fragment_teacher_views_his_own_profile, container, false);

        teacherName = teacherProfileView.findViewById(R.id.tvTeacherName);
        teacherEmail = teacherProfileView.findViewById(R.id.tvTeacherEmail);

        name = teacherName.getText().toString();
        email = teacherEmail.getText().toString();

        buttonGoBack = teacherProfileView.findViewById(R.id.btn_back);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                startActivity(intent);
            }
        });

        new GetTeacherDetailsAsyncTask().execute();
        return teacherProfileView;
    }

    private class GetTeacherDetailsAsyncTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(getContext());

        String result;

        public GetTeacherDetailsAsyncTask() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Checking profile....");
            dialogLogIn.setTitle("Check profile, please wait!");
            dialogLogIn.setCanceledOnTouchOutside(false);
            dialogLogIn.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "Getting teacher info from the server");
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL( Constants.URL + "TeacherViewDetailsServlet" );

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

            Teacher teacher = new Teacher();
            teacher.id = (int) App.loggedUserId;
            teacher.name = name;
           teacher.email = email;
        }
    }

}
