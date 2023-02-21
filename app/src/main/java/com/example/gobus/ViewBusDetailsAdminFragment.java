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
import com.example.gobus.ViewHolder.SearchItemViewHolder;
import com.firebase.ui.database.FirebaseIndexArray;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewBusDetailsAdminFragment extends Fragment {

    DatabaseReference dbreference;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminviewbusdetails_fragment,container,false);

        dbreference = FirebaseDatabase.getInstance().getReference().child("BusDetails");
        recyclerView = view.findViewById(R.id.reycle_menu_adminviewbusdetails);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BusDetails> options = new FirebaseRecyclerOptions.Builder<BusDetails>()
                .setQuery(dbreference, BusDetails.class).build();

        FirebaseRecyclerAdapter<BusDetails , BusDetailsViewHolder> adapter =
                new FirebaseRecyclerAdapter<BusDetails, BusDetailsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BusDetailsViewHolder busDetailsViewHolder, int i, @NonNull final BusDetails busDetails) {
                        busDetailsViewHolder.BusdetailsId.setText("ID= " + busDetails.getBusNumber());
                        busDetailsViewHolder.BusdetailsBusNumber.setText(busDetails.getBusNumber());
                        busDetailsViewHolder.BusdetailsOwnerName.setText(busDetails.getBusOwnerName());
                        busDetailsViewHolder.BusdetailsOwnerPhoneNumber.setText(String.valueOf(busDetails.getBusOwnerPhonenumber()));
                        busDetailsViewHolder.BusDetailsroute.setText(busDetails.getBusroute());


                        busDetailsViewHolder.updatebusdetailsbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(getContext(), UpdateBusdDetailsAdmin.class);
                                intent.putExtra("busD_number", busDetails.getBusNumber());

                                startActivity(intent);
                            }
                        });

                        busDetailsViewHolder.deletebusdetailsbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                DatabaseReference dbdelete = FirebaseDatabase.getInstance().getReference().child("BusDetails");

                                dbdelete.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.hasChild(String.valueOf(busDetails.getBusNumber()))){

                                            dbreference = FirebaseDatabase.getInstance().getReference().
                                                    child("BusDetails").child(String.valueOf(busDetails.getBusNumber()));
                                            dbreference.removeValue();

                                            Toast.makeText(getContext(), "Data delete Success" , Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getContext(), "No Source to delete" , Toast.LENGTH_SHORT).show();
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
                    public BusDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminviewbusdetails_singleitem,parent,false);
                        BusDetailsViewHolder holder = new BusDetailsViewHolder(view);
                        return holder;
                    }
                };
            recyclerView.setAdapter(adapter);
            adapter.startListening();

    }
}
