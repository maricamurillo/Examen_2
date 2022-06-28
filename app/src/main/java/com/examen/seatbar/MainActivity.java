package com.examen.seatbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button btnMesa1, btnMesa2, btnMesa3, btnMesa4,btnMesa5,btnMesa6,btnMesa7,btnMesa8,
            btnCancel;
    boolean isButtonActive = false;
    Button btnSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMesa1 = findViewById(R.id.btn_mesa1);
        btnMesa2 = findViewById(R.id.btn_mesa2);
        btnMesa3 = findViewById(R.id.btn_mesa3);
        btnMesa4 = findViewById(R.id.btn_mesa4);
        btnMesa5 = findViewById(R.id.btn_mesa5);
        btnMesa6 = findViewById(R.id.btn_mesa6);
        btnMesa7 = findViewById(R.id.btn_mesa7);
        btnMesa8 = findViewById(R.id.btn_mesa8);

        btnCancel = findViewById(R.id.btn_cancel);
        actions();
    }

    private void actions(){
        btnMesa1.setOnClickListener(view ->  {
            setState((Button)view,"1");
        });

        btnMesa2.setOnClickListener(view -> {
            setState((Button)view,"2");
        });
        btnMesa3.setOnClickListener(view ->  {
            setState((Button)view,"3");
        });

        btnMesa4.setOnClickListener(view -> {
            setState((Button)view,"4");
        });

        btnMesa5.setOnClickListener(view ->  {
            setState((Button)view,"5");
        });

        btnMesa6.setOnClickListener(view ->  {
            setState((Button)view,"6");
        });

        btnMesa7.setOnClickListener(view ->  {
            setState((Button)view,"7");
        });

        btnMesa8.setOnClickListener(view -> {
            setState((Button)view,"8");
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
    private void setState(Button btn, String number){
        //Valida si existe una mesa seleccionada, para que no seleccione dos a la vez
        if(!isButtonActive){
            if(btn.isEnabled()){
                btn.setEnabled(false);
                btn.setBackgroundResource(R.drawable.circle_gray);
                isButtonActive = true;
                mostrarAlerta(number);
                btnSelected = btn;
            }else{
                btn.setBackgroundResource(R.drawable.circle_green);
                btn.setEnabled(true);
            }
        }
    }

    private void mostrarAlerta(String numero) {
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


}