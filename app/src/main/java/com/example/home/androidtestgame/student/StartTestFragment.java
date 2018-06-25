package com.example.home.androidtestgame.student;


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
import android.widget.ListView;
import android.widget.TextView;

import com.example.home.androidtestgame.App;
import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.constants.Constants;
import com.example.home.androidtestgame.objects.Subject;
import com.example.home.androidtestgame.objects.TestHeader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartTestFragment extends Fragment {

    private static final String TAG = "StartTestFragment";
    private ArrayAdapter<TestHeader> testsAdapter;
    public StartTestFragment() {
    }


    ListView lvStartTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View startTestView = inflater.inflate(R.layout.fragment_start_test, container, false);
        setRetainInstance(true);
        lvStartTest = startTestView.findViewById(R.id.lv_choose_test);
        new GetTestsTask().execute();

        return startTestView;
    }

    private class GetTestsTask extends AsyncTask<Void, Void , Void> {

        ProgressDialog dialogLogIn =
                new ProgressDialog(getContext());

        String result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Getting testHeader....");
            dialogLogIn.setTitle("Взимане на тест, моля изчакайте...");
            dialogLogIn.setCanceledOnTouchOutside(false);
            dialogLogIn.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "Getting subjects from the server");
            URL url;
            HttpURLConnection urlConnection;
            BufferedReader br;

            try{
                url = new URL( Constants.URL + "TestFindServlet" );

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
            Gson gson = new GsonBuilder().create();
            final List<TestHeader> testHeaders = gson.fromJson(result, new TypeToken<List<TestHeader>>(){}.getType());
            testsAdapter = new ArrayAdapter<>(getContext(),R.layout.single_row,R.id.textView,testHeaders);
            lvStartTest.setAdapter(testsAdapter);
            lvStartTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TestActivity.startTestActivity(getContext(), testHeaders.get(position).id,testHeaders.get(position).testName);
                   /* App.testName = testHeaders.get(position).testName;*/
                }
            });

        }
    }

}
