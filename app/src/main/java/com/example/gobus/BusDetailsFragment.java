package com.example.gobus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gobus.ViewHolder.BusDetailsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BusDetailsFragment extends Fragment {

    Animation animations;
    ImageView imageViewbusdetails;
    DatabaseReference dbref;
    EditText search;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.busdetails_fragment,container,false);

        dbref = FirebaseDatabase.getInstance().getReference().child("BusDetails");
        recyclerView = view.findViewById(R.id.busdetails_recyle_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        imageViewbusdetails = view.findViewById(R.id.busdetails_fragment_icon);

        rotateAnimation();

        search = view.findViewById(R.id.searchbusdetails);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    filter(editable.toString());
            }
        });


        return view;
    }

    public void filter(String text){

    }

    

    private void rotateAnimation() {

        animations = AnimationUtils.loadAnimation(getContext(), R.anim.imagerotation);
        imageViewbusdetails.startAnimation(animations);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BusDetails> options = new FirebaseRecyclerOptions.Builder<BusDetails>()
                .setQuery(dbref, BusDetails.class).build();

        FirebaseRecyclerAdapter<BusDetails , BusDetailsViewHolder> adapter =
                new FirebaseRecyclerAdapter<BusDetails, BusDetailsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BusDetailsViewHolder busDetailsViewHolder, int i, @NonNull BusDetails busDetails) {

                        busDetailsViewHolder.ClientviewBusdetailsBusNumber.setText(busDetails.getBusNumber());
                        busDetailsViewHolder.ClientviewBusdetailsOwnerName.setText(busDetails.getBusOwnerName());
                        busDetailsViewHolder.ClientviewBusdetailsOwnerPhoneNumber.setText(String.valueOf(busDetails.getBusOwnerPhonenumber()));
                        busDetailsViewHolder.ClientviewBusDetailsroute.setText(busDetails.getBusroute());

                    }

                    @NonNull
                    @Override
                    public BusDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.busdetails_singleitem_fragment,parent,false);
                        BusDetailsViewHolder holder = new BusDetailsViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }


}
