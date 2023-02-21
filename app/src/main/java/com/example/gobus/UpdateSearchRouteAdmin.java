package com.example.gobus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateSearchRouteAdmin extends AppCompatActivity {
    TextView searchrouteid;
    EditText   searchroutelocation , searchroutedestination , searchrouteRoutenum , searchrouteDistance,
    searchrouteTicketprice , searchrouteTimeduration;
    Button updatebutton;

    private String mid;
    SearchRoutes s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_search_route_admin);

        s1 = new SearchRoutes();

        mid = getIntent().getExtras().get("SrhR_ID").toString();



        searchrouteid = findViewById(R.id.admin_updatedeletesearchroute_searchrouteid);
        searchroutelocation = findViewById(R.id.admin_updatedeletesearchroute_location);
        searchroutedestination = findViewById(R.id.admin_updatedeletesearchroute_Destination);
        searchrouteRoutenum= findViewById(R.id.admin_updatedeletesearchroute_routes);
        searchrouteDistance = findViewById(R.id.admin_updatedeletesearchroute_Distance);
        searchrouteTicketprice = findViewById(R.id.admin_updatedeletesearchroute_price);
        searchrouteTimeduration = findViewById(R.id.admin_updatedeletesearchroute_time);

        final DatabaseReference refdb = FirebaseDatabase.getInstance().getReference().child("SearchRoutes").child(mid);

        refdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {

                    searchrouteid.setText(dataSnapshot.child("searchRouteId").getValue().toString().trim());
                    searchroutelocation.setText(dataSnapshot.child("location").getValue().toString().trim());
                    searchroutedestination.setText(dataSnapshot.child("destination").getValue().toString().trim());
                    searchrouteRoutenum.setText(dataSnapshot.child("route_Numbers").getValue().toString().trim());
                    searchrouteDistance.setText(dataSnapshot.child("distance").getValue().toString().trim());
                    searchrouteTicketprice.setText(dataSnapshot.child("ticket_Prie").getValue().toString().trim());
                    searchrouteTimeduration.setText(dataSnapshot.child("time").getValue().toString().trim());
                }else {

                    Toast.makeText(UpdateSearchRouteAdmin.this ,"No Source Display" , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

                    updatebutton = findViewById(R.id.admin_updatesearchroute_updatebutton);

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                   s1.setSearchRouteId(Integer.parseInt(searchrouteid.getText().toString()));
                   s1.setLocation(searchroutelocation.getText().toString());
                   s1.setDestination(searchroutedestination.getText().toString());
                   s1.setRoute_Numbers(searchrouteRoutenum.getText().toString());
                   s1.setDistance(searchrouteDistance.getText().toString());
                   s1.setTicket_Prie(searchrouteTicketprice.getText().toString());
                   s1.setTime(searchrouteTimeduration.getText().toString());

                    DatabaseReference dbupdate = FirebaseDatabase.getInstance().getReference().child("SearchRoutes").child(searchrouteid.getText().toString());
                    dbupdate.setValue(s1);

                    Toast.makeText(getApplicationContext() , "Data Updabase Success", Toast.LENGTH_SHORT).show();


                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });



    }


}
