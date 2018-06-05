package com.example.home.androidtestgame.teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Teacher;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class TeacherLogInActivity extends AppCompatActivity {

    private static final String TAG = "TeacherLogInActivity";

    Button teacher_log_in;
    EditText teacherName, teacherPassword;
    String TeacherName, TeacherPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_log_in);

        teacher_log_in = findViewById(R.id.btn_log_in);
        teacherName = findViewById(R.id.et_teacher_name);
        teacherPassword = findViewById(R.id.et_teacher_password);

        teacher_log_in.setOnClickListener(onClickListener);
    }
    private boolean validatePassword(){
        if(TeacherPassword.isEmpty()){
            teacherPassword.setError("Полето не може да бъде празно!");
            return false;
        }else{
            teacherPassword.setError(null);
            return true;
        }
    }
    private boolean validateTeacherName(){
        if(TeacherName.isEmpty()){
            teacherName.setError("Полето не може да бъде празно!");
            return false;
        }else if(TeacherName.length()>15){
            teacherName.setError("Прекалено дълго име!");
            return false;
        }else{
            teacherName.setError(null);
            return true;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TeacherName = teacherName.getText().toString();
            TeacherPassword = teacherPassword.getText().toString();

            if (!validateTeacherName() | !validatePassword()) {
                return;
            }else{

                new TeacherLoginAsyncTask(TeacherName,TeacherPassword).execute();

            }
        }
    };

    private class TeacherLoginAsyncTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(TeacherLogInActivity.this);

        String password,name;
        String result;

        public TeacherLoginAsyncTask(String name, String password) {

            this.name = name;
            this.password = password;

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
            Log.d(TAG, "Communicating with server");
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL( Constants.URL + "TeacherLogInServlet" );

                Log.d(TAG, "url: " + url.toString());
                urlConnection = (HttpURLConnection)
                        url.openConnection();
                urlConnection.setRequestMethod("POST");

               Teacher teacher = new Teacher();
                teacher.password =  teacherPassword.getText().toString();
                teacher.name = teacherName.getText().toString();

                String creds = new GsonBuilder().create().toJson(teacher);

                byte[] outputInBytes = creds.getBytes("UTF-8");
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
            super.onPostExecute(aVoid);
            dialogLogIn.dismiss();
            App.loggedUserId =  new GsonBuilder().create().fromJson(result, Teacher.class).id;
            Log.d(TAG, "result: " + result);

            Intent intent = new Intent(TeacherLogInActivity.this, TeacherMenuActivity.class);
            startActivity(intent);
        }
    }
}
