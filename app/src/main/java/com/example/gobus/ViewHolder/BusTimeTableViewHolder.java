package com.example.gobus.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gobus.Interface.ItemClickListner;
import com.example.gobus.R;

public class BusTimeTableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView BustimetableId , BustimetableBusnumber , BustimetableBusroute , Bustimetabletime , BustimetableStartplace;
   public TextView ClintSideBustimetableBusnumber , ClintSideBustimetableBusroute , ClintSideBustimetabletime , ClintSideBustimetableStartplace;
    public Button updatebutton , deletebutton ;
    public  ItemClickListner listner;

    public BusTimeTableViewHolder(@NonNull View itemView) {
        super(itemView);

        BustimetableId = itemView.findViewById(R.id.admin_view_bustimetable_Id);
        BustimetableBusnumber = itemView.findViewById(R.id.admin_view_bustimetable_busnumber);
        BustimetableBusroute = itemView.findViewById(R.id.admin_view_bustimetable_busroute);
        Bustimetabletime = itemView.findViewById(R.id.admin_view_bustimetable_time);
        BustimetableStartplace = itemView.findViewById(R.id.admin_view_bustimetable_startingplace);
        updatebutton = itemView.findViewById(R.id.admin_view_bustimetable_updatebutton);
        deletebutton = itemView.findViewById(R.id.admin_view_bustimetable_deletebutton);

        ClintSideBustimetableBusnumber = itemView.findViewById(R.id.bustimetable_busnumber);
        ClintSideBustimetableBusroute = itemView.findViewById(R.id.bustimetable_busroute);
        ClintSideBustimetabletime = itemView.findViewById(R.id.bustimetable_time);
        ClintSideBustimetableStartplace = itemView.findViewById(R.id.bustimetable_startingPlace);

    }

    public void setItemClickListner(ItemClickListner listner){
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {

            listner.onclick(view , getAdapterPosition(),false);
    }
}
