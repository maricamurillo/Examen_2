package com.examen.seatbar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.seatbar.Modelo.Mesa;
import com.examen.seatbar.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private ArrayList<Mesa> fifo;

    public RecyclerAdapter(ArrayList<Mesa> fifo) {
        this.fifo = fifo;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesa,parent,false);
        return new MyViewHolder(iteView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        Integer mesa =fifo.get(position).getNumero();
        Boolean atendido =fifo.get(position).isAtendido();
        holder.tmesa.setText("kkkk");
        holder.tatendido.setText("kkkk");
    }

    @Override
    public int getItemCount() {
         return fifo.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView tmesa;
        private TextView tatendido;

        public MyViewHolder(@NonNull View itemView)  {
            super(itemView);
            tmesa = itemView.findViewById(R.id.tmesa);
            tatendido = itemView.findViewById(R.id.tatendido);
        }
    }
}
