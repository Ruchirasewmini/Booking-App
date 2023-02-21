package com.example.gobus.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gobus.Interface.ItemClickListner;
import com.example.gobus.R;

public class BusDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView BusdetailsId , BusdetailsBusNumber , BusdetailsOwnerName , BusdetailsOwnerPhoneNumber , BusDetailsroute;
    public Button updatebusdetailsbutton , deletebusdetailsbutton;

    public TextView ClientviewBusdetailsBusNumber , ClientviewBusdetailsOwnerName , ClientviewBusdetailsOwnerPhoneNumber , ClientviewBusDetailsroute;
    public  ItemClickListner listner;

    public BusDetailsViewHolder(@NonNull View itemView) {
        super(itemView);

        BusdetailsId = itemView.findViewById(R.id.admin_view_busdetails_Id);
        BusdetailsBusNumber = itemView.findViewById(R.id.admin_view_busdetails_busnumber);
        BusdetailsOwnerName = itemView.findViewById(R.id.admin_view_busdetails_busownername);
        BusdetailsOwnerPhoneNumber = itemView.findViewById(R.id.admin_view_busdetails_busownerphonenumber);
        BusDetailsroute = itemView.findViewById(R.id.admin_view_busdetails_busroute);
        updatebusdetailsbutton = itemView.findViewById(R.id.admin_view_busdetails_updatebutton);
        deletebusdetailsbutton = itemView.findViewById(R.id.admin_view_busdetails_deletebutton);


        ClientviewBusdetailsBusNumber = itemView.findViewById(R.id.busdetails_busnumber);
        ClientviewBusdetailsOwnerName = itemView.findViewById(R.id.busdetails_ownername);
        ClientviewBusdetailsOwnerPhoneNumber = itemView.findViewById(R.id.busdetails_phonenumber);
        ClientviewBusDetailsroute = itemView.findViewById(R.id.busdetails_routenumber);


    }

    public void setItemClickListner(ItemClickListner listner){
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {

            listner.onclick(view , getAdapterPosition(),false);
    }
}
