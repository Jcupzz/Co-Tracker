package com.jcupzz.mycoviddiary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddPlaces_Viewholder extends RecyclerView.ViewHolder {
   TextView addplacesuploadcontent_tv;

    public AddPlaces_Viewholder(@NonNull View itemView) {
        super(itemView);

        addplacesuploadcontent_tv = (TextView) itemView.findViewById(R.id.addplaces_tv_id);


    }
}
