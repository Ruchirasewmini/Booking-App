package com.example.gobus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AdminPannelHome extends Fragment {

    Button btnviewsearchroute , btnviewbusdetals , btnviewbustime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.adminpannelhome_fragment,container,false);


        btnviewsearchroute = view.findViewById(R.id.adminviewsearchroutebutton);
        btnviewbusdetals = view.findViewById(R.id.adminviewbusdetailsbutton);
        btnviewbustime = view.findViewById(R.id.adminviewbustimetablebutton);

        btnviewsearchroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_cotainer_admin , new ViewBusSearchRouteAdminFragment());
                ft.commit();
            }
        });

        btnviewbusdetals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_cotainer_admin , new ViewBusDetailsAdminFragment());
                ft.commit();
            }
        });

        btnviewbustime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_cotainer_admin , new ViewBusTimeTableAdminFragment());
                ft.commit();
            }
        });


        return view;
    }
}
