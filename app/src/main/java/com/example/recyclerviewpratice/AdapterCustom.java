package com.example.recyclerviewpratice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCustom extends RecyclerView.Adapter<AdapterCustom.DataHandler> {
    public ArrayList<DataProvider> dataProviderArrayList;
    public  OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener mlistener){
        listener = mlistener;
    }

    static class DataHandler extends RecyclerView.ViewHolder {
        public ImageView img_anything;
        public TextView txt_anything;
        public TextView txt_anything1;

        public DataHandler(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            img_anything = itemView.findViewById(R.id.img_anything);
            txt_anything = itemView.findViewById(R.id.txt_anything);
            txt_anything1 = itemView.findViewById(R.id.txt_anything1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public AdapterCustom(ArrayList<DataProvider> dataProviderArrayList) {
        this.dataProviderArrayList = dataProviderArrayList;
    }

    @NonNull
    @Override
    public DataHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View convertView;
       convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_celula,parent,false);
       DataHandler dataHandler = new DataHandler(convertView,listener);

       return dataHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull DataHandler holder, int position) {
        DataProvider dataProvider = dataProviderArrayList.get(position);
        holder.txt_anything.setText(dataProvider.getAnything());
        holder.txt_anything1.setText(dataProvider.getAnything1());
        holder.img_anything.setImageResource(dataProvider.getImg_resource());

    }

    @Override
    public int getItemCount() {
        return dataProviderArrayList.size();
    }


}
