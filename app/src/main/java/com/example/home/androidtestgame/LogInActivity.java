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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.home.androidtestgame.DBHelper.User;

public class LogInActivity extends AppCompatActivity {


    private static final String URL = "http://192.168.0.101:8080/TestV2Server/";
    private static final String TAG = "LogInActivity";
   // public static final String URL = "http://10.168.160.101:8080/TestV2Server/";
    DBHelper MyHelper;

    Button register;
    Button login;

    String studentPassword,studentFacultyNumber;
    EditText password,fNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

       //MyHelper = new DBHelper(this);

        register = findViewById(R.id.btn_register);
        login = findViewById(R.id.btn_log_in);

        password = findViewById(R.id.et_student_password);
        fNumber = findViewById(R.id.et_fnumber);

        register.setOnClickListener(onClickListener);
        login.setOnClickListener(onClickListener);
    }
    Intent intent = null;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId()== R.id.btn_register){

                intent = new Intent(LogInActivity.this, RegisterActivity.class);


//                    MyHelper.LogInStudent(studentPassword, studentFacultyNumber);

            }else {
                studentFacultyNumber = fNumber.getText().toString();
                studentPassword = password.getText().toString();

                if(!studentFacultyNumber.equals("") && !studentPassword.equals("")){
                    new LoginAsyncTask(studentFacultyNumber,studentPassword).execute();
                    intent = new Intent(LogInActivity.this, StudentStatusActivity.class);
                }
            }
            startActivity(intent);
        }
    };

    private class LoginAsyncTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(LogInActivity.this);
        String Password;
        String stuFacultyNumber;
        String result;

        public LoginAsyncTask(String stuFacultyNumber, String Password) {

                this.stuFacultyNumber = stuFacultyNumber;
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
                url = new URL( URL + "UserLogInServlet?Password=" + Password + "&FacultyNumber="+stuFacultyNumber );
                Log.d(TAG, "url: " + url.toString());
                urlConnection = (HttpURLConnection)
                        url.openConnection();

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

            try{
                JSONObject resultJson = new JSONObject(result);
                Log.d(TAG, "json: " + resultJson);
                User user = new User();
                user.Password= resultJson.getString("Password");
                user.FacultyNumber = resultJson.getString("FacultyNumber");
                user.id = resultJson.getLong("_id");


                if(user.id != 0){
                    Intent intent = new Intent(LogInActivity.this,
                            MenuActivity.class);

                   /* intent.putExtra("player", user);*/

                    startActivity(intent);
                }else{
                    Toast.makeText(LogInActivity.this,
                            "Wrong Password or FacultyNumber!",
                            Toast.LENGTH_LONG).show();
                }

            }catch (JSONException e){
                Log.wtf("WRONG!", e.getMessage());
            }

        }
    }
}
