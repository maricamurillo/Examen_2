package com.examen.seatbar.Modelo;

import java.util.Objects;

public class Mesa {
     Integer numero;
     boolean atendido;

     public  Mesa(){}

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

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (!(o instanceof Mesa)) return false;
          Mesa mesa = (Mesa) o;
          return Objects.equals(getNumero(), mesa.getNumero());
     }


}
