package com.example.gobus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateBusTimeTable extends AppCompatActivity {

    TextView bustimetablebusnumber;
    EditText bustimetableroute , bustimetabletime , bustimetablestartingplace;
    BusTimes busTimes;
    Button btnupdate;
    private String busnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bus_time_table);
        busTimes = new BusTimes();

        busnumber = getIntent().getExtras().get("busT_number").toString();
        btnupdate = findViewById(R.id.admin_updatedeletebustimetable_updatebutton);


        bustimetablebusnumber = findViewById(R.id.admin_updatebustimetable_busnumber);
        bustimetableroute = findViewById(R.id.admin_updatedeletebustimetable_busroute);
        bustimetabletime = findViewById(R.id.admin_updatedeletebustimetable_time);
        bustimetablestartingplace = findViewById(R.id.admin_updatedeletebustimetable_Startinplace);

        final DatabaseReference refdb = FirebaseDatabase.getInstance().getReference().child("busTimes").child(busnumber);

        refdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {

                    bustimetablebusnumber.setText(dataSnapshot.child("busNumber").getValue().toString().trim());
                    bustimetableroute.setText(dataSnapshot.child("route").getValue().toString().trim());
                    bustimetabletime.setText(dataSnapshot.child("time").getValue().toString().trim());
                    bustimetablestartingplace.setText(dataSnapshot.child("startingPlace").getValue().toString().trim());
                }else {

                    Toast.makeText(UpdateBusTimeTable.this ,"No Source Display" , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    busTimes.setBusNumber(bustimetablebusnumber.getText().toString());
                    busTimes.setRoute(bustimetableroute.getText().toString());
                    busTimes.setTime(bustimetabletime.getText().toString());
                    busTimes.setStartingPlace(bustimetablestartingplace.getText().toString());

                    DatabaseReference dbupdate = FirebaseDatabase.getInstance().getReference().child("busTimes").child(bustimetablebusnumber.getText().toString());
                    dbupdate.setValue(busTimes);

                    Toast.makeText(getApplicationContext() , "Data Updabase Success", Toast.LENGTH_SHORT).show();


                }catch (Exception e){

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });


    }

}
