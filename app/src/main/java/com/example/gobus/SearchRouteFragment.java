package com.example.gobus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchRouteFragment extends Fragment {

    EditText searchroutedestination, searchrouteloation;
    Button btnsearchtoute;
    DatabaseReference dbref;

    String[] columns = new String[] {

            "destination" , "distance" , "location" , "route_Numbers" ,
            "searchRouteId" , "ticket_Prie" , "time"
    };






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchroute_fragment,container,false);

        searchrouteloation = view.findViewById(R.id.searchroute_location);
        searchroutedestination = view.findViewById(R.id.searchroute_destination);
        btnsearchtoute = view.findViewById(R.id.searchtoute_searchbutton);


//        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot childDataSnapshot: dataSnapshot.getChildren()){
//
//                    if(childDataSnapshot.child("SearchRoutes").getValue() != null){
//
//                        ArrayList<SearchRoutes> arrayList = new ArrayList<SearchRoutes>();
//
//                        for (DataSnapshot ing : childDataSnapshot.child("SearchRoutes").getChildren()){
//
//                            arrayList.add(ing.child("SearchRoutes").getValue(SearchRoutes.class));
//                        }
//
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });





        btnsearchtoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    dbref = FirebaseDatabase.getInstance().getReference();
                    Query  query = dbref.child("SearchRoutes").orderByChild("location").equalTo(searchrouteloation.getText().toString());

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Map<String , Object>  map = new HashMap<>();

                                String data = snapshot.child("destination").getValue().toString();
                                if (data.equals(searchroutedestination.getText().toString())) {

                                    for (String colum : columns ){
                                        map.put(colum , snapshot.child(colum).getValue());
                                    }

                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.fragment_cotainer , new SearchRouteOnclickFragment(map));
                                    ft.commit();
                                    break;

                                }
                                else {
                                    Toast.makeText(getContext(), "Invalid Destination", Toast.LENGTH_SHORT);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(getContext(),"Invalid Location and Destination", Toast.LENGTH_SHORT).show();

                        }
                    });
            }
        });



       return view;
    }
}
