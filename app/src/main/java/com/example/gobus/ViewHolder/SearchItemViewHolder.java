package com.example.gobus.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gobus.Interface.ItemClickListner;
import com.example.gobus.R;

public class SearchItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView searchrouteId, searchrouteLocation , searchrouteDestination , searchrouteRoutenumber,
                    searchrouteDistance , searchrouteTicketPrice , searchrouteTimeDuration ;

    public Button searchrouteupdatebutton , searchroutedeletebutton;

    public  ItemClickListner listner;

    public SearchItemViewHolder(@NonNull View itemView) {
        super(itemView);

        searchrouteId = itemView.findViewById(R.id.admin_view_searchroute_Id);
        searchrouteLocation = itemView.findViewById(R.id.admin_view_searchroute_location);
        searchrouteDestination = itemView.findViewById(R.id.admin_view_searchroute_destination);
        searchrouteRoutenumber = itemView.findViewById(R.id.admin_view_searchroute_routenumber);
        searchrouteDistance = itemView.findViewById(R.id.admin_view_searchroute_distance);
        searchrouteTicketPrice = itemView.findViewById(R.id.admin_view_searchroute_ticketprice);
        searchrouteTimeDuration = itemView.findViewById(R.id.admin_view_searchroute_timeduration);
        searchrouteupdatebutton = itemView.findViewById(R.id.admin_view_searchroute_updatebutton);
        searchroutedeletebutton = itemView.findViewById(R.id.admin_view_searchroute_deletebutton);


    }

    public void setItemClickListner(ItemClickListner listner){
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {

            listner.onclick(view , getAdapterPosition(),false);
    }
}
