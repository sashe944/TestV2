package com.example.home.androidtestgame.student;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.home.androidtestgame.App;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.User;
import com.example.home.androidtestgame.student.MenuActivity;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfileFragment extends Fragment {

    Button ok;
    Button cancel;
    TextInputEditText updateName, updatePassword;
    String studentName, studentPassword;
    RadioButton gender;
    RadioGroup studentSex;

    private static final String TAG = "UpdateProfileFragment";

    public UpdateProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View updateProfileView = inflater.inflate(R.layout.fragment_update_profile, container, false);
        setRetainInstance(true);

        updateName = updateProfileView.findViewById(R.id.et_updateUsername);
        updatePassword = updateProfileView.findViewById(R.id.et_updatePassword);

        ok = updateProfileView.findViewById(R.id.btn_ok);
        cancel = updateProfileView.findViewById(R.id.btn_cancel);

        studentSex = updateProfileView.findViewById(R.id.genderRadioGroup);

        studentName = updateName.getText().toString();
        studentPassword = updatePassword.getText().toString();
        gender = updateProfileView.findViewById(studentSex.getCheckedRadioButtonId());

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentName = updateName.getText().toString().trim();
                studentPassword = updatePassword.getText().toString().trim();

                String FullName = studentName;
                String Pass = studentPassword;
                String Sex = gender.getText().toString();

                if (!validateUsername() | !validatePassword()) {
                    return;
                } else {
                    new UpdateAsyncTask(FullName, Pass, Sex).execute();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MenuActivity.class);
                startActivity(intent);
            }
        });
        return updateProfileView;
    }


    private boolean validateUsername(){
        if(studentName.isEmpty()){
            updateName.setError("Полето не може да бъде празно!");
            return false;
        }else if(studentName.length()>15){
            updateName.setError("Много дълго поле!");
            return false;
        }else{
            updateName.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        if (studentPassword.isEmpty()) {
            updatePassword.setError("Полето не може да бъде празно!");
            return false;
        } else {
            updatePassword.setError(null);
            return true;
        }
    }


    private class UpdateAsyncTask extends AsyncTask<Void, Void, Void> {


        String FullName;
        String Password;
        String Sex;
        String content;

        public UpdateAsyncTask(String FullName,String Password,String Sex ) {


            this.FullName = FullName;
            this.Password = Password;
            this.Sex = Sex;

        }

        ProgressDialog registerProgressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            registerProgressDialog.setTitle("Updating the user! Please wait...!");
            registerProgressDialog.setCanceledOnTouchOutside(false);
            registerProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL(Constants.URL+"UserUpdateServlet" );

                urlConnection = (HttpURLConnection) url.openConnection();
                Log.d(TAG, "url: " + url.toString());

                urlConnection.setRequestMethod("POST");
                User user = new User();
                user.id = App.loggedUserId ;
                user.name = updateName.getText().toString();
                user.password = Password;
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

            Intent menuIntent = new Intent(getContext(), MenuActivity.class);
            startActivity(menuIntent);

        }
    }
}

