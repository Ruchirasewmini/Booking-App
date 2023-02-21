package com.example.gobus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gobus.Interface.ItemClickListner;
import com.example.gobus.ViewHolder.SearchItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewBusSearchRouteAdminFragment extends Fragment {



    DatabaseReference dbref;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminviewsearchroutes_fragment,container,false);

        dbref = FirebaseDatabase.getInstance().getReference().child("SearchRoutes");
        recyclerView = view.findViewById(R.id.reycle_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<SearchRoutes> options = new FirebaseRecyclerOptions.Builder<SearchRoutes>()
                .setQuery(dbref, SearchRoutes.class).build();

        final FirebaseRecyclerAdapter<SearchRoutes , SearchItemViewHolder> adapter =
                new FirebaseRecyclerAdapter<SearchRoutes, SearchItemViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull SearchItemViewHolder searchItemViewHolder, int i, @NonNull final SearchRoutes searchRoutes) {
                            searchItemViewHolder.searchrouteId.setText("ID =" + String.valueOf(searchRoutes.getSearchRouteId()));
                            searchItemViewHolder.searchrouteLocation.setText(searchRoutes.getLocation());
                            searchItemViewHolder.searchrouteDestination.setText(searchRoutes.getDestination());
                            searchItemViewHolder.searchrouteRoutenumber.setText(searchRoutes.getRoute_Numbers());
                            searchItemViewHolder.searchrouteDistance.setText(searchRoutes.getDistance());
                            searchItemViewHolder.searchrouteTicketPrice.setText( searchRoutes.getTicket_Prie());
                            searchItemViewHolder.searchrouteTimeDuration.setText(searchRoutes.getTime());

                            searchItemViewHolder.searchroutedeletebutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    DatabaseReference dbdelete = FirebaseDatabase.getInstance().getReference().child("sn");

                                    dbdelete.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            if(dataSnapshot.hasChild(String.valueOf(searchRoutes.getSearchRouteId()))){
                                                dbref = FirebaseDatabase.getInstance().getReference().child("SearchRoutes")
                                                        .child(String.valueOf(searchRoutes.getSearchRouteId()));

                                                dbref.removeValue();
                                                Toast.makeText(getContext() , "Data Delete Success" , Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                                Toast.makeText(getContext() , "No Source to Delete" , Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                }
                            });

                            searchItemViewHolder.searchrouteupdatebutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                Intent intent =  new Intent(getContext(),  UpdateSearchRouteAdmin.class);
                                intent.putExtra("SrhR_ID" , searchRoutes.getSearchRouteId());

                                startActivity(intent);


                                }
                            });




                    }


                    @NonNull
                    @Override
                    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminviewsearchroutes_singleitem,parent,false);
                        SearchItemViewHolder holder = new SearchItemViewHolder(view);
                        return holder;
                    }

                };



            recyclerView.setAdapter(adapter);
            adapter.startListening();



    }
}
