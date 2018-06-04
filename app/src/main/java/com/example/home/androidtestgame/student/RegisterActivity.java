package com.example.home.androidtestgame.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.example.home.androidtestgame.DBHelper;
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
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    Button ok;
    Button cancel;
   DBHelper MyHelper;
    Spinner spinner;
    TextInputEditText Name, FNumber, Password;
    String spinnerChoice, studentName, facultyNumber, studentPassword;
    RadioButton gender;
    RadioGroup studentSex;

     private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = findViewById(R.id.et_username);
        FNumber = findViewById(R.id.et_fNumber);
        Password = findViewById(R.id.et_password);

        MyHelper = new DBHelper(RegisterActivity.this);
        ArrayList<String> list = MyHelper.getAllUserTypes();
        spinner = findViewById(R.id.sp_typeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.tv_spinner, list);
        spinner.setAdapter(adapter);

        ok = findViewById(R.id.btn_ok);
        cancel = findViewById(R.id.btn_cancel);

        studentSex = findViewById(R.id.genderRadioGroup);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerChoice = spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //NOTHING YET
            }
        });
        studentName = Name.getText().toString();
        facultyNumber = FNumber.getText().toString();
        studentPassword = Password.getText().toString();
        gender = findViewById(studentSex.getCheckedRadioButtonId());

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentName = Name.getText().toString().trim();
                facultyNumber = FNumber.getText().toString().trim();
                studentPassword = Password.getText().toString().trim();

                String FullName = studentName;
                String FNumber = facultyNumber;
                String Pass = studentPassword;
                Long Choice = Long.valueOf(spinnerChoice);
                String Sex = gender.getText().toString();

                if(!validateUsername() | !validatePassword() | validateFacultyNumber()){
                    return;
                }else{
                    new RegisterAsyncTask(FNumber,FullName,Pass,Choice,Sex).execute();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean validateUsername(){
        if(studentName.isEmpty()){
            Name.setError("Полето не може да бъде празно!");
            return false;
        }else if(studentName.length()>15){
            Name.setError("Прекалено дълго име!");
            return false;
        }else{
            Name.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        if (studentPassword.isEmpty()) {
            Password.setError("Полето не може да бъде празно!");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }
    private boolean validateFacultyNumber() {
        if (facultyNumber.equals(FNumber.getText().toString())) {
            FNumber.setError("Вече има регистриран такъв факултетен номер!");
            return false;
        } else {
            FNumber.setError(null);
            return true;
        }
    }
    private class RegisterAsyncTask extends AsyncTask<Void, Void, Void> {

        String FacultyNumber;
        String FullName;
        String Password;
        long StudentChoice;
        String Sex;
        String content;

        public RegisterAsyncTask(String FacultyNumber,String FullName,String Password, long StudentChoice,String Sex ) {

            this.FacultyNumber = FacultyNumber;
            this.FullName = FullName;
            this.Password = Password;
            this.StudentChoice = StudentChoice;
            this.Sex = Sex;

        }

        ProgressDialog registerProgressDialog = new ProgressDialog(RegisterActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            registerProgressDialog.setTitle("Registering the user! Please wait...!");
            registerProgressDialog.setCanceledOnTouchOutside(false);
            registerProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL(Constants.URL+"UserRegisterServlet" );

                urlConnection = (HttpURLConnection) url.openConnection();
                Log.d(TAG, "url: " + url.toString());

                urlConnection.setRequestMethod("POST");
                User user = new User();
                user.name = Name.getText().toString();
                user.facultyNumber = FNumber.getText().toString();
                user.password = Password;
                user.userTypeID = StudentChoice;
                user.gender = Sex;

                String credentials = new GsonBuilder().create().toJson(user);

                byte[] outputInBytes = credentials.getBytes("UTF-8");
                OutputStream os = urlConnection.getOutputStream();
                os.write( outputInBytes );
                os.close();


                br = new BufferedReader( new InputStreamReader( urlConnection.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while(line != null) {
                    sb.append(line);
                    line = br.readLine();
                }

                content = sb.toString();

            }catch(MalformedURLException e){
                Log.wtf("WRONG!", e.getMessage());
            }catch(IOException e){
                Log.wtf("WRONG!", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            registerProgressDialog.dismiss();

            Log.d(TAG, "content: " + content);

            Intent menuIntent = new Intent(RegisterActivity.this, MenuActivity.class);
            startActivity(menuIntent);

        }
    }
}
