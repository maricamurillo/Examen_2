package com.examen.seatbar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.seatbar.Modelo.Mesa;
import com.examen.seatbar.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private ArrayList<Mesa> listaMesas = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Mesa> fifo) {
        this.listaMesas = fifo;
    }
    public void setItems(ArrayList<Mesa> m){
        listaMesas.addAll(m);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesa,parent,false);
        return new MyViewHolder(iteView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        Integer mesa =listaMesas.get(position).getNumero();
        Boolean atendido =listaMesas.get(position).isAtendido();
        holder.tmesa.setText(mesa.toString());
        holder.tatendido.setText(atendido?"Sí":"No");
    }

    @Override
    public int getItemCount() {
         return listaMesas.size();
    }

    public void eliminarMesa(View v,Integer layoutPosition){
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        private TextView tmesa;
        private TextView tatendido;
        private Button atender;

        public MyViewHolder(@NonNull View itemView)  {
            super(itemView);
            tmesa = itemView.findViewById(R.id.tmesa);
            tatendido = itemView.findViewById(R.id.tatendido);
            atender = itemView.findViewById(R.id.atender);
            atender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eliminarMesa(v,getLayoutPosition());
                }
            });
        }
    }
}
