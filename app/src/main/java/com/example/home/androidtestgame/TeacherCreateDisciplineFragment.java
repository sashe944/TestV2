package com.example.home.androidtestgame;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCreateDisciplineFragment extends Fragment {

    Button ok;
    Button back;

    public TeacherCreateDisciplineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View disciplineView = inflater.inflate(R.layout.fragment_teacher_create_discipline, container, false);

        ok = disciplineView.findViewById(R.id.btnAddDiscipline);
        back = disciplineView.findViewById(R.id.btnCancelDiscipline);

        //ok.setOnClickListener(OnClickListener);
        //back.setOnClickListener(OnClickListener);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherMenuActivity.class);
                //Save Discipline
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

   /* View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            if(v.getId() == R.id.btnAddDiscipline){

            }else{
                intent = new Intent(TeacherCreateDisciplineActivity.this,TeacherMenuActivity.class);
            }
            startActivity(intent);
        }
    };

        */
        return  disciplineView;
    }

}
