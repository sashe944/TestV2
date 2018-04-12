package com.example.home.androidtestgame;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }

    Button ok;
    Button cancel;
    DBHelper MyHelper;
    Spinner spinner;
    EditText Name, FNumber, Password;
    String spinnerChoice, studentName, facultyNumber, studentPassword;
    RadioButton gender;
    RadioGroup studentSex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View registerView = inflater.inflate(R.layout.fragment_register, container, false);

        Name = registerView.findViewById(R.id.et_username);
        FNumber = registerView.findViewById(R.id.et_fNumber);
        Password = registerView.findViewById(R.id.et_password);

        MyHelper = new DBHelper(getActivity());
        ArrayList<String> list = MyHelper.getAllUserTypes();
        spinner = registerView.findViewById(R.id.sp_typeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, R.id.tv_spinner, list);
        spinner.setAdapter(adapter);

        ok = registerView.findViewById(R.id.btn_ok);
        cancel = registerView.findViewById(R.id.btn_cancel);

        studentSex = registerView.findViewById(R.id.genderRadioGroup);
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
        gender = registerView.findViewById(studentSex.getCheckedRadioButtonId());


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.btn_cancel) {
                    //onBackPressed();
                } else {

                    studentName = Name.getText().toString();
                    facultyNumber = FNumber.getText().toString();
                    studentPassword = Password.getText().toString();

                    String fullName = studentName;
                    String fNumber = facultyNumber;
                    String pass = studentPassword;
                    String choice = spinnerChoice;
                    String sex = gender.getText().toString();

                    MyHelper.addStudent(fNumber, fullName, pass, choice, sex);

                    Intent menuIntent = new Intent(getContext(), MenuActivity.class);
                    startActivity(menuIntent);

                }
            }
        });

        return registerView;
    }
}
  /*  View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_cancel) {
                //onBackPressed();
            } else {

                studentName = Name.getText().toString();
                facultyNumber = FNumber.getText().toString();
                studentPassword = Password.getText().toString();

                //gender = findViewById(studentSex.getCheckedRadioButtonId());

               String fullName = studentName;
                String fNumber = facultyNumber;
                String pass = studentPassword;
                String choice = spinnerChoice;
                String sex = gender.getText().toString();

                 MyHelper.addStudent(fNumber,fullName,pass,choice,sex);

                //Intent menuIntent = new Intent(RegisterActivity.this, MenuActivity.class);
                //startActivity(menuIntent);

            }
        }
    };*/
//}
