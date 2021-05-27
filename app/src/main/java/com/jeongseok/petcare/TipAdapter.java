package com.jeongseok.petcare;

import android.app.Activity;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.MyViewHolder> {

    private Activity activity;
    private ArrayList<ItemTip> tiplist;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemTip data = tiplist.get(position);
        holder.disease.setText(data.getDiseaseName());
        holder.result1.setText(data.getResult1());
        holder.result2.setText(data.getResult2());
        holder.tip.setText(data.getTip());
    }

    @Override
    public int getItemCount() {
        return tiplist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView disease;
        TextView result1;
        TextView result2;
        TextView tip;

        public MyViewHolder(View itemview) {
            super(itemview);
            disease = (TextView) itemview.findViewById(R.id.disease_Name);
            result1 = (TextView) itemview.findViewById(R.id.result1_Tv);
            result2 = (TextView) itemview.findViewById(R.id.result2_Tv);
            tip = (TextView) itemview.findViewById(R.id.tip_Tv);
        }
    }
    public TipAdapter(Activity activity,ArrayList<ItemTip> tiplist){
        this.activity = activity;
        this.tiplist = tiplist;
    }
}
