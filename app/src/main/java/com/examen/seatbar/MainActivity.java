package com.examen.seatbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.examen.seatbar.Adapter.RecyclerAdapter;
import com.examen.seatbar.DataBase.DAOMesa;
import com.examen.seatbar.Modelo.Mesa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button btnMesa1, btnMesa2, btnMesa3, btnMesa4,btnMesa5,btnMesa6,btnMesa7,btnMesa8,
            btnCancel;
    boolean isButtonActive = false;
    Button btnSelected;
    Mesa mesaSelected;
    private RecyclerView mrMesas;

    //private ArrayList<Mesa> mesas;
    DAOMesa dao;
    RecyclerAdapter adapter;
    Boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new DAOMesa();

        btnMesa1 = findViewById(R.id.btn_mesa1);
        btnMesa2 = findViewById(R.id.btn_mesa2);
        btnMesa3 = findViewById(R.id.btn_mesa3);
        btnMesa4 = findViewById(R.id.btn_mesa4);
        btnMesa5 = findViewById(R.id.btn_mesa5);
        btnMesa6 = findViewById(R.id.btn_mesa6);
        btnMesa7 = findViewById(R.id.btn_mesa7);
        btnMesa8 = findViewById(R.id.btn_mesa8);
        btnCancel = findViewById(R.id.btn_cancel);


        //mesas = new ArrayList<>();
        //agregarMesas();

        mrMesas = findViewById(R.id.rvMesas);
        //RecyclerAdapter adapter = new RecyclerAdapter(mesas);
        adapter = new RecyclerAdapter(this);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        mrMesas.setLayoutManager(lm);
        //mrMesas.setItemAnimator(new DefaultItemAnimator());
        mrMesas.setAdapter(adapter);
        //mrMesas.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        
        loadData();

        actions();


    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Mesa> m = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    Mesa mesa = data.getValue(Mesa.class);
                    m.add(mesa);
                }
                adapter.setItems(m);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void actions(){
        btnMesa1.setOnClickListener(view ->  {
            setState((Button)view,1);
        });

        btnMesa2.setOnClickListener(view -> {
            setState((Button)view,2);
        });
        btnMesa3.setOnClickListener(view ->  {
            setState((Button)view,3);
        });

        btnMesa4.setOnClickListener(view -> {
            setState((Button)view,4);
        });

        btnMesa5.setOnClickListener(view ->  {
            setState((Button)view,5);
        });

        btnMesa6.setOnClickListener(view ->  {
            setState((Button)view,6);
        });

        btnMesa7.setOnClickListener(view ->  {
            setState((Button)view,7);
        });

        btnMesa8.setOnClickListener(view -> {
            setState((Button)view,8);
        });

        btnCancel.setOnClickListener(view -> {
            if(isButtonActive){
                cancelarEspera();
            }else{
                mostrarNoSeleccionado();
            }

        });
    }

    //Cambiar estado de un botón al ser presionado
    private void setState(Button btn, Integer number){
        //Valida si existe una mesa seleccionada, para que no seleccione dos a la vez
        if(!isButtonActive){
            if(btn.isEnabled()){

            }else{
                btn.setBackgroundResource(R.drawable.circle_green);
                btn.setEnabled(true);
            }
        }
    }



    private void mostrarAlerta(Integer numero) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.setTitle("Será atendido en la mesa " + numero )
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
    }

    private void mostrarAlertaMesadupli(Integer numero) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.setTitle("La mesa " + numero +" ya esta siendo atendia")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
    }
    private void mostrarAlertaNomesa1(Integer numero) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.setTitle("La mesa " + numero +" tiene que ser atendia primero")
                .create();
        dialog.show();
    }

    private void cancelarEspera() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.setTitle("Se cancelará su mesa" )
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Quita el boton seleccionado
                        if(btnSelected != null){
                            btnSelected.setEnabled(true);
                            btnSelected.setBackgroundResource(R.drawable.circle_green);
                            adapter.notifyDataSetChanged();
                        }
                        isButtonActive = false;
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
        }).create();

        dialog.show();
    }

    private void mostrarNoSeleccionado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.setTitle("No ha seleccionado una mesa")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
    }


    /*private void  agregarMesas(){
        mesas.add(new Mesa(1,false));
        mesas.add(new Mesa(2,false));
        mesas.add(new Mesa(3,false));
        mesas.add(new Mesa(4,false));
    }*/


}