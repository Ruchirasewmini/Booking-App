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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBusTimetableAdminFragment extends Fragment {

    EditText busNumber , busroute , time , startingplace;
    Button Addbustimes;
    DatabaseReference dbref;
    BusTimes busTimes;

    private void clearconsole(){
        busNumber.setText("");
        busroute.setText("");
        time.setText("");
        startingplace.setText("");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.addbustimetable_adminfragment,container,false);


        busNumber = view.findViewById(R.id.admin_addbustimetable_busNo);
        busroute = view.findViewById(R.id.admin_addbustimetable_route);
        time = view.findViewById(R.id.admin_addbustimetable_time);
        startingplace = view.findViewById(R.id.admin_addbustimetable_Startingplace);

        Addbustimes = view.findViewById(R.id.admin_addbustimetable_addtimebutton);

        busTimes = new BusTimes();


        Addbustimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("busTimes");

                try {
                    if (TextUtils.isEmpty(busNumber.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Bus Number" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(busroute.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Route" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(time.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the Time" , Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(startingplace.getText().toString())){
                        Toast.makeText(getContext(), "Please Enter the  Starting Place" , Toast.LENGTH_SHORT).show();
                    }else {



                        busTimes.setBusNumber(busNumber.getText().toString().trim());
                        busTimes.setRoute(busroute.getText().toString().trim());
                        busTimes.setTime(time.getText().toString().trim());
                        busTimes.setStartingPlace(startingplace.getText().toString().trim());



                        dbref.child(busTimes.getBusNumber()).setValue(busTimes);

                        Toast.makeText(getContext(), "Data Saved Successfull", Toast.LENGTH_SHORT).show();
                        clearconsole();
                    }

                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Invalid Number" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
