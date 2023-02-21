package com.example.gobus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gobus.ViewHolder.BusTimeTableViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TimeTableFragment extends Fragment {

    Animation animation;
    ImageView imageViewbustimetable;
    DatabaseReference dbref;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.timetable_fragment,container,false);


        dbref = FirebaseDatabase.getInstance().getReference().child("busTimes");
        recyclerView = view.findViewById(R.id.bustimetable_recyle_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        imageViewbustimetable = view.findViewById(R.id.bustimetable_fragment_icon);

        rotateAnimation();

        return view;
    }

    private void rotateAnimation() {

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.imagerotation);
        imageViewbustimetable.startAnimation(animation);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BusTimes> options = new FirebaseRecyclerOptions.Builder<BusTimes>()
                .setQuery(dbref, BusTimes.class).build();

        FirebaseRecyclerAdapter<BusTimes , BusTimeTableViewHolder> adapter =
                new FirebaseRecyclerAdapter<BusTimes, BusTimeTableViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BusTimeTableViewHolder busTimeTableViewHolder, int i, @NonNull BusTimes busTimes) {

                        busTimeTableViewHolder.ClintSideBustimetableBusnumber.setText(busTimes.getBusNumber());
                        busTimeTableViewHolder.ClintSideBustimetableBusroute.setText(busTimes.getRoute());
                        busTimeTableViewHolder.ClintSideBustimetabletime.setText(busTimes.getTime());
                        busTimeTableViewHolder.ClintSideBustimetableStartplace.setText(busTimes.getStartingPlace());

                    }

                    @NonNull
                    @Override
                    public BusTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bustimetble_singleitem_fragment,parent,false);
                        BusTimeTableViewHolder holder = new BusTimeTableViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

}
