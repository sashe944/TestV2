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
import android.widget.Button;
import android.widget.EditText;

import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Subject;
import com.example.home.androidtestgame.teacher.TeacherMenuActivity;
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
public class TeacherCreateDisciplineFragment extends Fragment {

    Button ok;
    Button back;
    EditText disciplineName;
    EditText disciplineDescription;

    private static final String TAG = "TeacherCreateDiscipline";

    public TeacherCreateDisciplineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View disciplineView = inflater.inflate(R.layout.fragment_teacher_create_discipline, container, false);
        setRetainInstance(true);

        ok = disciplineView.findViewById(R.id.btnAddDiscipline);
        back = disciplineView.findViewById(R.id.btnCancelDiscipline);
        disciplineName = disciplineView.findViewById(R.id.disciplineEditText);
        disciplineDescription = disciplineView.findViewById(R.id.descriptionEditText);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);

                String disName = disciplineName.getText().toString();
                String disDescription = disciplineDescription.getText().toString();

                    new RegisterSubjectAsyncTask(disName,disDescription).execute();

                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                startActivity(intent);
            }
        });

        return  disciplineView;
    }
    private class RegisterSubjectAsyncTask extends AsyncTask<Void, Void, Void> {

       String subjectName;
       String subjectDescription;
        String content;

        public RegisterSubjectAsyncTask(String subjectName,String subjectDescription ) {

           this.subjectName = subjectName;
           this.subjectDescription = subjectDescription;

        }

        ProgressDialog registerProgressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            registerProgressDialog.setTitle("Registering subject! Please wait...!");
            registerProgressDialog.setCanceledOnTouchOutside(false);
            registerProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL(Constants.URL+"SubjectRegisterServlet" );

                urlConnection = (HttpURLConnection) url.openConnection();
                Log.d(TAG, "url: " + url.toString());

                urlConnection.setRequestMethod("POST");
                Subject subject = new Subject();
                subject.name = disciplineName.getText().toString();
                subject.description = disciplineDescription.getText().toString();

                String createDiscipline = new GsonBuilder().create().toJson(subject);

                byte[] outputInBytes = createDiscipline.getBytes("UTF-8");
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
        }
    }

}
