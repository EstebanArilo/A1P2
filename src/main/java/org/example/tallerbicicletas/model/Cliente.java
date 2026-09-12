package org.example.tallerbicicletas.model;

import java.util.ArrayList;

public class Cliente extends Persona implements INotificable{
        private String direccion;
        private ArrayList<String> listNotificaciones;

        public Cliente(String nombre, int id, int telefono, String direccion) {
            super(nombre, id, telefono);
            this.direccion = direccion;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

    public ArrayList<String> getListNotificaciones() {
        return listNotificaciones;
    }

    public void setListNotificaciones(ArrayList<String> listNotificaciones) {
        this.listNotificaciones = listNotificaciones;
    }

//METODOS
        @Override
        public String toString() {
            return "Cliente{" +
                    "direccion='" + direccion + '\'' +
                    '}';
        }

    @Override
    public String recibirEstadoBicicleta() {
        return "";
    }

    @Override
    public void recibirEstadoBicicleta(String mensaje) {
        this.listNotificaciones.add(mensaje);
        System.out.print(mensaje);
    }

    @Override
    public void notificarEstadoBicicleta(String mensaje) {

    }


}
