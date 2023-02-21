package com.example.gobus;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRouteAdminFragment extends Fragment {

    EditText routeid , route , routestartdestination;
    Button addroute;
    DatabaseReference dbref;
    Routes routes;

    private void clearconsole(){

        routeid.setText("");
        route.setText("");
        routestartdestination.setText("");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.addroute_adminfragment,container,false);

        routeid = view.findViewById(R.id.admin_addroute_routeid);
        route = view.findViewById(R.id.admin_addroute_route);
        routestartdestination = view.findViewById(R.id.admin_addroute_routestartdestination);

        addroute = view.findViewById(R.id.admin_addroute_buttonaddroute);

        routes = new Routes();
        addroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Routes");
                try {
                    if (TextUtils.isEmpty(routeid.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Route Id" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(route.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Route" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(routestartdestination.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the route Start and Destination" , Toast.LENGTH_SHORT).show();
                    }else {


                        routes.setRouteId(Integer.parseInt(routeid.getText().toString().trim()));
                        routes.setRoute(route.getText().toString().trim());
                        routes.setStartAndDestination(routestartdestination.getText().toString().trim());

                        dbref.child(String.valueOf(routes.getRouteId())).setValue(routes);

                        Toast.makeText(getContext(), "Data Saved Successfull", Toast.LENGTH_SHORT).show();
                        clearconsole();

                    }

                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Invalid Route Id" , Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}
