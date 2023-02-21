package com.example.gobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawer ,toolbar,R.string.navigation_drawer_open ,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,
                    new SearchRouteFragment()).commit();
            navigationView.setCheckedItem(R.id.searchroute);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.searchroute:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,
                       new SearchRouteFragment()).commit();
                break;
            case R.id.busdetails:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,
                        new BusDetailsFragment()).commit();
                break;
            case R.id.timetable:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,
                        new TimeTableFragment()).commit();
                break;
            case R.id.booking:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,
                        new BookingFragment()).commit();
                break;
            case R.id.login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,
                        new LoginFragment()).commit();
                break;
            case R.id.share:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,
                        new ShareFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }



    }
}
