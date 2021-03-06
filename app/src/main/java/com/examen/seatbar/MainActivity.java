package com.examen.seatbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

    Button btnMesa1, btnMesa2, btnMesa3, btnMesa4, btnMesa5, btnMesa6, btnMesa7, btnMesa8;
    Button btnCancelar1, btnCancelar2, btnCancelar3, btnCancelar4, btnCancelar5, btnCancelar6, btnCancelar7, btnCancelar8;

    Mesa mesaSelected;
    private RecyclerView mrMesas;
    private ArrayList<Mesa> cola;
    private ArrayList<Mesa> mesasDB;
    private RecyclerAdapter adapter;
    private ArrayList<Button> botones;
    DAOMesa dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao= new DAOMesa();

        btnMesa1 = findViewById(R.id.btn_mesa1);
        btnMesa2 = findViewById(R.id.btn_mesa2);
        btnMesa3 = findViewById(R.id.btn_mesa3);
        btnMesa4 = findViewById(R.id.btn_mesa4);
        btnMesa5 = findViewById(R.id.btn_mesa5);
        btnMesa6 = findViewById(R.id.btn_mesa6);
        btnMesa7 = findViewById(R.id.btn_mesa7);
        btnMesa8 = findViewById(R.id.btn_mesa8);

        btnCancelar1 = findViewById(R.id.btn_cancelar1);
        btnCancelar2 = findViewById(R.id.btn_cancelar2);
        btnCancelar3 = findViewById(R.id.btn_cancelar3);
        btnCancelar4 = findViewById(R.id.btn_cancelar4);
        btnCancelar5 = findViewById(R.id.btn_cancelar5);
        btnCancelar6 = findViewById(R.id.btn_cancelar6);
        btnCancelar7 = findViewById(R.id.btn_cancelar7);
        btnCancelar8 = findViewById(R.id.btn_cancelar8);

        cola = new ArrayList<>();
        botones = new ArrayList<>();

        mrMesas = findViewById(R.id.rvMesas);
        adapter = new RecyclerAdapter(cola) {
            @Override
            public void eliminarMesa(View v, Integer layoutPosition) {
                eliminar_Mesa(v, layoutPosition);
            }
        };
        //adapter.setItems(mesas);
        //adapter.notifyDataSetChanged();
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mrMesas.setLayoutManager(lm);
        mrMesas.setAdapter(adapter);
        loadData();
        actions();
        actionsCancelar();

        botones.add(btnMesa1);
        botones.add(btnMesa2);
        botones.add(btnMesa3);
        botones.add(btnMesa4);
        botones.add(btnMesa5);
        botones.add(btnMesa6);
        botones.add(btnMesa7);
        botones.add(btnMesa8);

    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mesasDB = new ArrayList<>();//datos de DB
                for (DataSnapshot data : snapshot.getChildren()) {
                   Mesa mesa = data.getValue(Mesa.class);
                    mesasDB.add(mesa); //
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void actions() {
        btnMesa1.setOnClickListener(view -> {
            setState((Button) view, 1);
        });

        btnMesa2.setOnClickListener(view -> {
            setState((Button) view, 2);
        });
        btnMesa3.setOnClickListener(view -> {
            setState((Button) view, 3);
        });

        btnMesa4.setOnClickListener(view -> {
            setState((Button) view, 4);
        });

        btnMesa5.setOnClickListener(view -> {
            setState((Button) view, 5);
        });

        btnMesa6.setOnClickListener(view -> {
            setState((Button) view, 6);
        });

        btnMesa7.setOnClickListener(view -> {
            setState((Button) view, 7);
        });

        btnMesa8.setOnClickListener(view -> {
            setState((Button) view, 8);
        });

    }

    private void actionsCancelar() {
        btnCancelar1.setOnClickListener(view -> {
            eventoCancelar(btnMesa1, 1);
        });

        btnCancelar2.setOnClickListener(view -> {
            eventoCancelar(btnMesa2, 2);
        });
        btnCancelar3.setOnClickListener(view -> {
            eventoCancelar(btnMesa3, 3);
        });

        btnCancelar4.setOnClickListener(view -> {
            eventoCancelar(btnMesa4, 4);
        });

        btnCancelar5.setOnClickListener(view -> {
            eventoCancelar(btnMesa5, 5);
        });

        btnCancelar6.setOnClickListener(view -> {
            eventoCancelar(btnMesa6, 6);
        });

        btnCancelar7.setOnClickListener(view -> {
            eventoCancelar(btnMesa7, 7);
        });

        btnCancelar8.setOnClickListener(view -> {
            eventoCancelar(btnMesa8, 8);
        });

    }
    private void eventoCancelar(Button btn, Integer number) {
        System.out.println("Aqui estoy");
        if (!btn.isEnabled()) { // revisar*****
            cancelarEspera(btn,number);
        } else {
            mostrarNoSeleccionado();
        }
    }

    // Cambiar estado de un bot??n al ser presionado
    private void setState(Button btn, Integer number) {
        if (btn.isEnabled()) {
            agregarMesaCola(btn,number);
        } else {
            btn.setBackgroundResource(R.drawable.circle_green);
            btn.setEnabled(true);
        }
    }

    private void agregarMesaCola(Button btn,Integer numero) {
        //obtener la mesa de DB
        int i = mesasDB.indexOf(new Mesa(numero, false));
        Mesa mesa = mesasDB.get(i);
        // cambiar el estado en la DB actualizar
        // esta linea se cambiar con el metodo que jala el array de la DB
        if(cola.contains(mesa)==false){
            cola.add(mesa);
            btn.setEnabled(false);
            btn.setBackgroundResource(R.drawable.circle_gray);
            mostrarAlerta(numero);
        }
        else{
            mostrarAlertaMesadupli(numero);
        }
        adapter.notifyDataSetChanged();
        mrMesas.scrollToPosition(cola.size()-1);
        mesaSelected = mesa;
    }

    private void mostrarAlerta(Integer numero) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.setTitle("Ser?? atendido en la mesa " + numero)
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
        AlertDialog dialog = builder.setTitle("La mesa " + numero + " ya esta siendo atendia")
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
        AlertDialog dialog = builder.setTitle("La mesa " + numero + " tiene que ser atendia primero")
                .create();
        dialog.show();
    }

    private void cancelarEspera(Button btn,Integer numero) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.setTitle("Se cancelar?? su mesa "+numero)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Quita el boton seleccionado
                        Mesa mesaSelected = new Mesa(numero,false);
                        btn.setEnabled(true);
                        btn.setBackgroundResource(R.drawable.circle_green);
                        cola.remove(mesaSelected);
                        adapter.notifyDataSetChanged();
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

    private void eliminar_Mesa(View v, Integer numero) {
        if (numero > 0) {
            mostrarAlertaNomesa1(cola.get(0).getNumero());
        } else {
            // obtener boton de la besa por atender
            Mesa m = cola.remove((int) numero);
            cola.remove(m);
            Button button = botones.get(m.getNumero() - 1);
            button.setEnabled(true);
            button.setBackgroundResource(R.drawable.circle_green);
            adapter.notifyDataSetChanged();
        }
    }
}