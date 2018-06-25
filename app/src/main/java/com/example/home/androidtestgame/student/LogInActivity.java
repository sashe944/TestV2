package com.example.home.androidtestgame.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.home.androidtestgame.App;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.User;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LogInActivity extends AppCompatActivity {

    private static final String TAG = "LogInActivity";

    Button register;
    Button login;
    TextView checkUserType;
    String result;


    String studentPassword,studentFacultyNumber;
    EditText password,fNumber;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        register = findViewById(R.id.btn_register);
        login = findViewById(R.id.btn_log_in);
        checkUserType = findViewById(R.id.tvCheck);


        password = findViewById(R.id.et_student_password);
        fNumber = findViewById(R.id.et_fnumber);

        register.setOnClickListener(onClickListener);
        login.setOnClickListener(onClickListener);
    }
    private boolean validateFacultyNumber(){
        if(studentFacultyNumber.isEmpty()){
            fNumber.setError("Полето не може да бъде празно!");
            return false;
        }
            else{
            fNumber.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        if(studentPassword.isEmpty()){
            password.setError("Полето не може да бъде празно!");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId()== R.id.btn_register){
                Intent regIntent = null;
                regIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }else {
                studentFacultyNumber = fNumber.getText().toString();
                studentPassword = password.getText().toString();

                if(!validateFacultyNumber() | !validatePassword()){
                    return;
                }
                else{
                   new LoginAsyncTask(studentFacultyNumber,studentPassword).execute();
                }
            }
        }
    };

    private class LoginAsyncTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(LogInActivity.this);
        String Password;
        String stuFacultyNumber;


        public LoginAsyncTask(String stuFacultyNumber, String Password) {
                this.stuFacultyNumber = stuFacultyNumber;
                this.Password = Password;

        }

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
                url = new URL( Constants.URL + "UserLogInServlet" );

                Log.d(TAG, "url: " + url.toString());
                urlConnection = (HttpURLConnection)
                        url.openConnection();
                urlConnection.setRequestMethod("POST");

                user.password = password.getText().toString().trim();
                user.facultyNumber = fNumber.getText().toString().trim();
                user.userTypeID = App.userTypeId;

                String creds = new GsonBuilder().create().toJson(user);

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

            if (TextUtils.isEmpty(result)) {
                // show wrong credentials dialog
                Log.d(TAG, "wrong credentials");
            } else {
                User student = new GsonBuilder().create().fromJson(result, User.class);
                App.loggedUserId =  student.id;
                App.userTypeId = student.userTypeID;

                Intent intent = new Intent(LogInActivity.this, MenuActivity.class);
                startActivity(intent);
            }


        }
    }
}
