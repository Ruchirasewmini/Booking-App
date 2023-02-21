package com.example.gobus;

import android.os.Bundle;
import android.os.DropBoxManager;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBusDetailsAdminFragment extends Fragment {


    EditText Busnumber , busroute , busownername , busownerphonenumber;
    Button addbusdetailsbutton;
    DatabaseReference dbref;
    BusDetails busDetails;

    private void clearconsole(){
        Busnumber.setText("");
        busroute.setText("");
        busownername.setText("");
        busownerphonenumber.setText("");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.addbusdetails_adminfragment,container,false);

        Busnumber = view.findViewById(R.id.admin_addbusdetails_busNo);
        busroute = view.findViewById(R.id.admin_addbusdetails_route);
        busownername = view.findViewById(R.id.admin_addbusdetails_busownername);
        busownerphonenumber = view.findViewById(R.id.admin_addbusdetails_busownephonenumber);

        addbusdetailsbutton =view.findViewById(R.id.admin_addbusdetails_buttonaddbusdetails);

        busDetails = new BusDetails();

        addbusdetailsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("BusDetails");

                try {
                    if (TextUtils.isEmpty(Busnumber.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Bus Number" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(busroute.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Route" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(busownername.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Bus Owner" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(busownerphonenumber.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Bus Owner Phone Number" , Toast.LENGTH_SHORT).show();
                    }else {



                        busDetails.setBusNumber(Busnumber.getText().toString().trim());
                        busDetails.setBusroute(busroute.getText().toString().trim());
                        busDetails.setBusOwnerName(busownername.getText().toString().trim());
                        busDetails.setBusOwnerPhonenumber(Integer.parseInt(busownerphonenumber.getText().toString().trim()));


                        dbref.child(busDetails.getBusNumber()).setValue(busDetails);

                        Toast.makeText(getContext(), "Data Saved Successfull", Toast.LENGTH_SHORT).show();
                        clearconsole();
                    }

                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Invalid Phone Number" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
