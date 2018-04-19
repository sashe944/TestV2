package com.example.home.androidtestgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    Button ok;
    Button cancel;
    DBHelper MyHelper;
    Spinner spinner;
    EditText Name, FNumber, Password;
    String spinnerChoice, studentName, facultyNumber, studentPassword;
    RadioButton gender;
    RadioGroup studentSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*RegisterFragment registerFragment = new RegisterFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.registerFragmentContainer,registerFragment);
        fragmentTransaction.commit();*/

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

                studentName = Name.getText().toString();
                facultyNumber = FNumber.getText().toString();
                studentPassword = Password.getText().toString();

                String fullName = studentName;
                String fNumber = facultyNumber;
                String pass = studentPassword;
                String choice = spinnerChoice;
                String sex = gender.getText().toString();

                MyHelper.addStudent(fNumber, fullName, pass, choice, sex);

                Intent menuIntent = new Intent(RegisterActivity.this, MenuActivity.class);
                startActivity(menuIntent);

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

}
