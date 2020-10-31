package com.jcupzz.cotracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddPlaces_Adapter extends RecyclerView.Adapter<AddPlaces_Viewholder> {
AddPlacesMain addPlacesMain;

    public AddPlaces_Adapter(AddPlacesMain addPlacesMain, ArrayList<AddPlacesManually_Models> addPlacesManually_modelsArrayList) {
        this.addPlacesMain = addPlacesMain;
        this.addPlacesManually_modelsArrayList = addPlacesManually_modelsArrayList;
    }

    ArrayList<AddPlacesManually_Models> addPlacesManually_modelsArrayList;

    @NonNull
    @Override
    public AddPlaces_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(addPlacesMain.getBaseContext());
        View view = layoutInflater.inflate(R.layout.addplaces_elements, parent, false);
        return new AddPlaces_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddPlaces_Viewholder holder, int position) {
holder.addplacesuploadcontent_tv.setText(addPlacesManually_modelsArrayList.get(position).getUploadcontent());
    }

    @Override
    public int getItemCount() {
        return addPlacesManually_modelsArrayList.size();
    }
}
