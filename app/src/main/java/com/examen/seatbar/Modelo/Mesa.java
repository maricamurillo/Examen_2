package com.examen.seatbar.Modelo;

public class Mesa {
     Integer numero;
     boolean atendido;

     public Mesa(Integer numero, boolean atendido) {
          this.numero = numero;
          this.atendido = atendido;
     }

     public Integer getNumero() {
          return numero;
     }

     public void setNumero(Integer numero) {
          this.numero = numero;
     }

     public boolean isAtendido() {
          return atendido;
     }

     public void setAtendido(boolean atendido) {
          this.atendido = atendido;
     }
}
