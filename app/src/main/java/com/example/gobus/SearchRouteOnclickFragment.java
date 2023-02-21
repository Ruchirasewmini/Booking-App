package com.example.gobus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Map;

public class SearchRouteOnclickFragment extends Fragment {

    String routenumber , ticketprice , time , distance ;



    public SearchRouteOnclickFragment(Map<String,Object> data){
        routenumber = data.get("route_Numbers").toString();
        ticketprice = data.get("distance").toString();
        time =  data.get("ticket_Prie").toString();
        distance =  data.get("time").toString();
    }

    TextView route , ticket , Time , Distance ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.searchrouteonclick_fragment,container,false);

        route = view.findViewById(R.id.searchroute_onclick_routes);
        ticket = view.findViewById(R.id.searchroute_onclick_ticketprice);
        Time = view.findViewById(R.id.searchroute_onclick_timeduration);
        Distance = view.findViewById(R.id.searchroute_onclick_distance);

        route.setText(routenumber);
        ticket.setText(ticketprice);
        Time.setText(time);
        Distance.setText(distance);

        return  view;
    }
}
