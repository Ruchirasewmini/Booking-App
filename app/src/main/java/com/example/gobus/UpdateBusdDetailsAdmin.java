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

public class UpdateBusdDetailsAdmin extends AppCompatActivity {

        TextView busdetailsbusnumber;
        EditText busdetailsowner , busdetailsownernumber , budroute;
        Button updatebutton;
        BusDetails busDetails;

        private String busnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_busd_details_admin);

        busDetails = new BusDetails();

        busnumber = getIntent().getExtras().get("busD_number").toString();

        busdetailsbusnumber = findViewById(R.id.admin_updatebusdetails_busnumber);
        busdetailsowner = findViewById(R.id.admin_updatedeletebusdetails_busowner);
        busdetailsownernumber = findViewById(R.id.admin_updatedeletebusdetails_ownerphone);
        budroute = findViewById(R.id.admin_updatedeletebusdetails_Route);

        final DatabaseReference refdb = FirebaseDatabase.getInstance().getReference().child("BusDetails").child(busnumber);

        refdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {

                    busdetailsbusnumber.setText(dataSnapshot.child("busNumber").getValue().toString().trim());
                    busdetailsowner.setText(dataSnapshot.child("busOwnerName").getValue().toString().trim());
                    busdetailsownernumber.setText(dataSnapshot.child("busOwnerPhonenumber").getValue().toString().trim());
                    budroute.setText(dataSnapshot.child("busroute").getValue().toString().trim());
                }else {

                    Toast.makeText(UpdateBusdDetailsAdmin.this ,"No Source Display" , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            updatebutton = findViewById(R.id.admin_updatedeletebusdetails_updatebutton);

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    busDetails.setBusNumber(busdetailsbusnumber.getText().toString());
                    busDetails.setBusOwnerName(busdetailsowner.getText().toString());
                    busDetails.setBusOwnerPhonenumber(Integer.parseInt(busdetailsownernumber.getText().toString()));
                    busDetails.setBusroute(budroute.getText().toString());

                    DatabaseReference dbupdate = FirebaseDatabase.getInstance().getReference().child("BusDetails").child(busdetailsbusnumber.getText().toString());
                    dbupdate.setValue(busDetails);

                    Toast.makeText(getApplicationContext() , "Data Updabase Success", Toast.LENGTH_SHORT).show();


                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}
