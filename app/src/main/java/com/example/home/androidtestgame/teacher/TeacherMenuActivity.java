package com.example.home.androidtestgame.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.home.androidtestgame.R;
import com.example.home.androidtestgame.TeacherGiveGradeFragment;
import com.example.home.androidtestgame.tabHost.TabHostActivity;

public class TeacherMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //Intent intent = null;

        int id = item.getItemId();

        if(id==R.id.nav_profile){
            TeacherViewsHisOwnProfileFragment teacherFragment = new TeacherViewsHisOwnProfileFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fillThisContainerWithFragment,teacherFragment);
            fragmentTransaction.commit();
        }
        else if(id == R.id.nav_create_discipline){
          TeacherCreateDisciplineFragment disciplineFragment = new TeacherCreateDisciplineFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fillThisContainerWithFragment,disciplineFragment);
            fragmentTransaction.commit();

        }
        else if(id==R.id.nav_create_test){
           TeacherCreateTestFragment createTestFragment = new TeacherCreateTestFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fillThisContainerWithFragment,createTestFragment);
            fragmentTransaction.commit();

        }
        else if(id==R.id.nav_create_question){
            TeacherCreateQuestionFragment questionFragment = new TeacherCreateQuestionFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fillThisContainerWithFragment,questionFragment);
            fragmentTransaction.commit();

        }
        else if(id==R.id.nav_grade){
            TeacherGiveGradeFragment gradeFragment = new TeacherGiveGradeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fillThisContainerWithFragment,gradeFragment);
            fragmentTransaction.commit();

        }else if(id==R.id.nav_log_out){
            Intent intent = new Intent(TeacherMenuActivity.this,TabHostActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
