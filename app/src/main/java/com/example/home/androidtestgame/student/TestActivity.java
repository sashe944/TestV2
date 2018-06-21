package com.example.home.androidtestgame.student;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.home.androidtestgame.R;

public class TestActivity extends AppCompatActivity {

    private static final String EXTRA_HEADER_ID = "headerId";

    Button give;
    private long testHeaderId;


    public static void startTestActivity(Context context, long headerId) {
        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtra(EXTRA_HEADER_ID, headerId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testHeaderId = getIntent().getExtras().getLong(EXTRA_HEADER_ID);

        TestFragment testFragment = TestFragment.newInstance(testHeaderId);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.testContainer,testFragment);
        fragmentTransaction.commit();
    }

   /* View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TestActivity.this,MenuActivity.class);
            startActivity(intent);
        }
    };*/
}
