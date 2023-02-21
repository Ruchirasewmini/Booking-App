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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddSearchRouteAdminFragment extends Fragment {

    EditText SearchrouteId , Locatin , Destination , Route_numbers , Distance , Ticket_price , Time;
    Button addsearchroutebutton;
    DatabaseReference dbref;
    SearchRoutes searchRoutes;



    private void clearconsole(){
        SearchrouteId.setText("");
        Locatin.setText("");
        Destination.setText("");
        Route_numbers.setText("");
        Distance.setText("");
        Ticket_price.setText("");
        Time.setText("");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.addsearchroute_adminfragment,container,false);

        SearchrouteId = view.findViewById(R.id.admin_addsearchroute_searchrouteid);
        Locatin = view.findViewById(R.id.admin_addsearchroute_location);
        Destination = view.findViewById(R.id.admin_addsearchroute_Destination);
        Route_numbers = view.findViewById(R.id.admin_addsearchroute_routes);
        Distance = view.findViewById(R.id.admin_addsearchroute_Distance);
        Ticket_price = view.findViewById(R.id.admin_addsearchroute_price);
        Time = view.findViewById(R.id.admin_addsearchroute_time);

        addsearchroutebutton = view.findViewById(R.id.admin_addsearchroute_addbutton);

        searchRoutes = new SearchRoutes();



        addsearchroutebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("SearchRoutes");

                try {
                    if (TextUtils.isEmpty(SearchrouteId.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Search Route Id" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Locatin.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Location" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Destination.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Destination" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Route_numbers.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Route Numbers" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Distance.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Distance" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Ticket_price.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Ticket Price" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Time.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Time" , Toast.LENGTH_SHORT).show();
                    }else {


                        searchRoutes.setSearchRouteId(Integer.parseInt(SearchrouteId.getText().toString().trim()));
                        searchRoutes.setLocation(Locatin.getText().toString().trim());
                        searchRoutes.setDestination(Destination.getText().toString().trim());
                        searchRoutes.setRoute_Numbers(Route_numbers.getText().toString().trim());
                        searchRoutes.setDistance(Distance.getText().toString().trim());
                        searchRoutes.setTicket_Prie(Ticket_price.getText().toString().trim());
                        searchRoutes.setTime(Time.getText().toString().trim());

                        dbref.child(String.valueOf(searchRoutes.getSearchRouteId())).setValue(searchRoutes);

                        Toast.makeText(getContext(), "Data Saved Successfull", Toast.LENGTH_SHORT).show();
                        clearconsole();

                    }

                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Invalid Search Route Id" , Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
}
