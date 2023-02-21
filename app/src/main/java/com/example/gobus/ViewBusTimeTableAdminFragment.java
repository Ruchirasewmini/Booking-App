package com.example.gobus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gobus.ViewHolder.BusDetailsViewHolder;
import com.example.gobus.ViewHolder.BusTimeTableViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewBusTimeTableAdminFragment extends Fragment {

    DatabaseReference dbref;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminviewbustimetable_fragment,container,false);

        dbref = FirebaseDatabase.getInstance().getReference().child("busTimes");
        recyclerView = view.findViewById(R.id.reycle_menu_adminviewbustimetable);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BusTimes> options = new FirebaseRecyclerOptions.Builder<BusTimes>()
                .setQuery(dbref, BusTimes.class).build();

        FirebaseRecyclerAdapter<BusTimes , BusTimeTableViewHolder> adapter =
                new FirebaseRecyclerAdapter<BusTimes, BusTimeTableViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BusTimeTableViewHolder busTimeTableViewHolder, int i, @NonNull final BusTimes busTimes) {
                        busTimeTableViewHolder.BustimetableId.setText("ID= " + busTimes.getBusNumber());
                        busTimeTableViewHolder.BustimetableBusnumber.setText(busTimes.getBusNumber());
                        busTimeTableViewHolder.BustimetableBusroute.setText(busTimes.getRoute());
                        busTimeTableViewHolder.Bustimetabletime.setText(busTimes.getTime());
                        busTimeTableViewHolder.BustimetableStartplace.setText(busTimes.getStartingPlace());

                        busTimeTableViewHolder.updatebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), UpdateBusTimeTable.class);
                                intent.putExtra("busT_number", busTimes.getBusNumber());

                                startActivity(intent);
                            }

                       });

                        busTimeTableViewHolder.deletebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                final DatabaseReference dbdelete = FirebaseDatabase.getInstance().getReference().
                                        child("busTimes");

                                dbdelete.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.hasChild(busTimes.getBusNumber())){

                                            dbref =FirebaseDatabase.getInstance().getReference().child("busTimes").child(busTimes.getBusNumber());
                                            dbref.removeValue();

                                            Toast.makeText(getContext() , "Delete Success" , Toast.LENGTH_SHORT).show();

                                        }else {
                                            Toast.makeText(getContext(), "No Source Deleted" , Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                        });


                    }


                    @NonNull
                    @Override
                    public BusTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminviewbustimetable_singleitem,parent,false);
                        BusTimeTableViewHolder holder = new BusTimeTableViewHolder(view);
                        return holder;
                    }
                };
            recyclerView.setAdapter(adapter);
            adapter.startListening();

    }
}
