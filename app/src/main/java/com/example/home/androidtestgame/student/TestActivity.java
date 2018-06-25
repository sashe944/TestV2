package com.example.home.androidtestgame.student;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.home.androidtestgame.R;

public class TestActivity extends AppCompatActivity {

    private static final String EXTRA_HEADER_ID = "headerId";
    private static final String EXTRA_HEADER_NAME = "headerName";

    Button give;
    private long testHeaderId;
    private String testName;


    public static void startTestActivity(Context context, long headerId,String headerName) {
        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtra(EXTRA_HEADER_ID, headerId);
        intent.putExtra(EXTRA_HEADER_NAME,headerName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testHeaderId = getIntent().getExtras().getLong(EXTRA_HEADER_ID);
        testName = getIntent().getExtras().getString(EXTRA_HEADER_NAME);

        TestFragment testFragment = TestFragment.newInstance(testHeaderId,testName);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.testContainer,testFragment);
        fragmentTransaction.commit();
    }
}
