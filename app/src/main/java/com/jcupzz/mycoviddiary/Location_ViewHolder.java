package com.jcupzz.mycoviddiary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Location_ViewHolder extends RecyclerView.ViewHolder {
    TextView date,day,longitude,lattitude;
    CardView cardView;
    public Location_ViewHolder(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.date_tv);
        day = itemView.findViewById(R.id.day_tv);
        longitude = itemView.findViewById(R.id.longitude_tv_id);
        lattitude  = itemView.findViewById(R.id.latitude_tv_id);
        cardView = itemView.findViewById(R.id.l_card_id);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
