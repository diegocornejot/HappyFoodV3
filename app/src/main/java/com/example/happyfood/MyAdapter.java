package com.example.happyfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<DataPlatos> list;

    public MyAdapter(Context context, ArrayList<DataPlatos> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataPlatos datoplatos = list.get(position);
        holder.Nombre.setText(datoplatos.getNombre());
        holder.Proteinas.setText(datoplatos.getProteinas());
        holder.Grasa.setText(datoplatos.getGrasa());
        holder.Calorias.setText(datoplatos.getCalorias());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Nombre, Proteinas, Grasa, Calorias;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Nombre = itemView.findViewById(R.id.Nombre);
            Proteinas = itemView.findViewById(R.id.Proteinas);
            Grasa = itemView.findViewById(R.id.Grasa);
            Calorias = itemView.findViewById(R.id.Calorias);
        }
    }
}
