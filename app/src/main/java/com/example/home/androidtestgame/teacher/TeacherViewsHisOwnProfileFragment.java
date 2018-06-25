package com.example.home.androidtestgame.teacher;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.home.androidtestgame.App;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Teacher;
import com.example.home.androidtestgame.objects.User;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherViewsHisOwnProfileFragment extends Fragment {

    Button buttonGoBack;
    TextView teacherName,teacherEmail;
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

    private class GetTeacherDetailsAsyncTask extends AsyncTask<Void, Void, String> {

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
        protected String doInBackground(Void... voids) {
            Log.d(TAG, "Getting teacher info from the server");
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{

                Uri builtUri = Uri.parse(Constants.URL + "TeacherViewDetailsServlet")
                        .buildUpon()
                        .appendQueryParameter("id", String.valueOf(App.loggedUserId))
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

                result = content.toString();
            }catch (MalformedURLException e){
                Log.wtf("WRONG!", e.getMessage());
            }catch(IOException e){
                Log.wtf("WRONG!", e.getMessage());
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d(TAG, "taking data");
            Log.d(TAG, "result: " + this.result);
            super.onPostExecute(result);
            dialogLogIn.dismiss();

            Teacher teacher = new GsonBuilder().create().fromJson(result, Teacher.class);

            teacherName.setText(teacher.name);
            teacherEmail.setText(teacher.email);

        }
    }

}
