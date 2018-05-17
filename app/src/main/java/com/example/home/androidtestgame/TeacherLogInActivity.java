package com.example.home.androidtestgame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    //public static final String URL = "http://10.168.160.102:8080/TestV2/";
  private static final String URL ="http://192.168.0.110:8080/TestV2Server/";

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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TeacherName = teacherName.getText().toString();
            TeacherPassword = teacherPassword.getText().toString();

            if (!TeacherName.equals("") && !TeacherPassword.equals("")) {
                //TODO:AsyncTask inicialized here
                new TeacherLoginAsyncTask(TeacherName,TeacherPassword).execute();

                Intent intent = new Intent(TeacherLogInActivity.this, TeacherMenuActivity.class);
                startActivity(intent);
            }
        }
    };

    private class TeacherLoginAsyncTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(TeacherLogInActivity.this);
        String Password;
        String Name;
        String result;

        public TeacherLoginAsyncTask(String Name, String Password) {

            this.Name= Name;
            this.Password = Password;

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
                url = new URL( URL + "UserLogInServlet" );
                Log.d(TAG, "url: " + url.toString());
                urlConnection = (HttpURLConnection)
                        url.openConnection();
                urlConnection.setRequestMethod("POST");

                Teacher teacher = new Teacher();
                teacher.teacherName = Name;
                teacher.teacherPassword = Password;

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

            Log.d(TAG, "result: " + result);

        }
    }
}
