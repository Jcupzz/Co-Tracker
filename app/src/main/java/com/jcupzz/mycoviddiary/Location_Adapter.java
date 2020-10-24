package com.jcupzz.mycoviddiary;

import android.content.Intent;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Location_Adapter extends RecyclerView.Adapter<Location_ViewHolder> {
    Location_History location_history;
    ArrayList<Lati_Longi_Models> downModels;
    public Location_Adapter(Location_History location_history, ArrayList<Lati_Longi_Models> downModels) {
        this.location_history = location_history;
        this.downModels = downModels;
    }

    @NonNull
    @Override
    public Location_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(location_history.getBaseContext());
        View view = layoutInflater.inflate(R.layout.location_elements, parent, false);
        return new Location_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Location_ViewHolder holder, final int position) {
holder.day.setText(downModels.get(position).getDay());
holder.date.setText(downModels.get(position).getDate());
holder.longitude.setText( "Longitude:"+downModels.get(position).getLongi_S());
holder.lattitude.setText("Latitude:"+downModels.get(position).getLati_S());
holder.address.setText("Address: "+downModels.get(position).getAddress());
holder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

double getlati = Double.parseDouble(downModels.get(position).getLati_S());
double getlongi = Double.parseDouble(downModels.get(position).getLongi_S());
DisplayActivity.j=2;
Intent intent = new Intent(v.getContext(),DisplayActivity.class);
intent.putExtra("la",getlati);
intent.putExtra("lo",getlongi);
v.getContext().startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return downModels.size();
    }
}
