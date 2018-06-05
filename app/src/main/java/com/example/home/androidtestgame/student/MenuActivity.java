package com.example.home.androidtestgame.student;

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
import com.example.home.androidtestgame.UpdateProfileActivity;
import com.example.home.androidtestgame.tabHost.tabHostActivity;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar =  findViewById(R.id.toolbar);
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
        int id = item.getItemId();
        if(id==R.id.nav_profile){
           Intent intent = new Intent(MenuActivity.this,UpdateProfileActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_old_tests){
            OldTestsFragment oldTestsFragment = new OldTestsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fillThisPlaceWithFragment,oldTestsFragment);
            fragmentTransaction.commit();
        }
        else if(id==R.id.nav_start_test){
            StartTestFragment startTestFragment = new StartTestFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fillThisPlaceWithFragment,startTestFragment);
            fragmentTransaction.commit();
        }
        else if(id==R.id.nav_status){
            Intent intent = new Intent(MenuActivity.this, StudentStatusActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_log_out){
           Intent intent = new Intent(MenuActivity.this, tabHostActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
