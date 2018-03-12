package com.example.home.androidtestgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherCreateDisciplineActivity extends AppCompatActivity {

    Button ok;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_discipline);

        ok = findViewById(R.id.btnAddDiscipline);
        back = findViewById(R.id.btnCancelDiscipline);

        ok.setOnClickListener(OnClickListener);
        back.setOnClickListener(OnClickListener);
    }

    View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            if(v.getId() == R.id.btnAddDiscipline){
                //TODO Save Discipline
            }else{
                intent = new Intent(TeacherCreateDisciplineActivity.this,TeacherMenuActivity.class);
            }
            startActivity(intent);
        }
    };
}
