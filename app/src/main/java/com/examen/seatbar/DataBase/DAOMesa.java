package com.examen.seatbar.DataBase;

import com.examen.seatbar.Modelo.Mesa;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOMesa {

    private DatabaseReference databaseReference;

    public DAOMesa(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference().child("Mesas");
    }

    public Task<Void> add(Mesa m){
       return databaseReference.push().setValue(m);
    }

    public Query get(){
        return databaseReference.orderByKey();
    }
}
