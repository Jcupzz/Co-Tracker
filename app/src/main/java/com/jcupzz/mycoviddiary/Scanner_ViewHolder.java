package com.jcupzz.mycoviddiary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Scanner_ViewHolder extends RecyclerView.ViewHolder {
   TextView s_day,s_date,s_result;
   CardView s_cardView;
    public Scanner_ViewHolder(@NonNull View itemView) {
        super(itemView);
        s_cardView = itemView.findViewById(R.id.s_card_id);
        s_day = itemView.findViewById(R.id.s_day_tv);
        s_date = itemView.findViewById(R.id.s_date_tv);
        s_result = itemView.findViewById(R.id.qr_result_tv_id);
    }
}
