package com.example.gobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class AdminPannel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pannel);

        Toolbar toolbaradmin = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbaradmin);

        drawer_admin = findViewById(R.id.drawer_layout_admin);
        NavigationView navigationView = findViewById(R.id.nav_view_admin);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawer_admin ,toolbaradmin,R.string.navigation_drawer_open ,
                R.string.navigation_drawer_close);
        drawer_admin.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer_admin,
                    new AdminPannelHome()).commit();
            navigationView.setCheckedItem(R.id.adminhome);
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.adminhome:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer_admin,
                        new AdminPannelHome()).commit();
                break;
            case R.id.addroute:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer_admin,
                        new AddRouteAdminFragment()).commit();
                break;
            case R.id.addsearchroutes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer_admin,
                        new AddSearchRouteAdminFragment()).commit();
                break;
            case R.id.addbusdetails:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer_admin,
                        new AddBusDetailsAdminFragment()).commit();
                break;
            case R.id.addtimetable:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer_admin,
                        new AddBusTimetableAdminFragment()).commit();
                break;

        }

        drawer_admin.closeDrawer(GravityCompat.START);

        return true;
    }
}
