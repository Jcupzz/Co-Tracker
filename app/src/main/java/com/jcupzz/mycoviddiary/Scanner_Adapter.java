package com.jcupzz.mycoviddiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Scanner_Adapter extends RecyclerView.Adapter<Scanner_ViewHolder> {
    Scanner_History scanner_history;
    ArrayList<Models> ArrayListModels;
    public Scanner_Adapter(Scanner_History scanner_history,ArrayList<Models> ArrayListModels){
        this.scanner_history = scanner_history;
        this.ArrayListModels = ArrayListModels;
    }
    @NonNull
    @Override
    public Scanner_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(scanner_history.getBaseContext());
        View view = layoutInflater.inflate(R.layout.scanner_elements, parent, false);
        return new Scanner_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Scanner_ViewHolder holder, int position) {
        holder.s_day.setText(ArrayListModels.get(position).getQr_day());
        holder.s_date.setText("On "+ArrayListModels.get(position).getQr_date());
        holder.s_result.setText("You have been to:-\n"+ArrayListModels.get(position).getQr_result());
    }

    @Override
    public int getItemCount() {
        return ArrayListModels.size();
    }
}
