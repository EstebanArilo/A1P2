package org.example.tallerbicicletas.model;

import java.util.ArrayList;

public class Cliente extends Persona implements INotificable {
    private String direccion;
    private ArrayList<String> listNotificaciones;
    private ArrayList<Bicicleta> listBicicleta;
    private String ultimoEstadoNotificado;

    public Cliente(String nombre, int id, int telefono, String direccion) {
        super(nombre, id, telefono);
        this.direccion = direccion;
        this.listNotificaciones = new ArrayList<>();
        this.listBicicleta = new ArrayList<>();
        this.ultimoEstadoNotificado = "Sin notificaciones";
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

    public ArrayList<Bicicleta> getListBicicleta() {
        return listBicicleta;
    }


    @Override
    public void notificarEstadoBicicleta(String mensaje) {
        recibirEstadoBicicleta(mensaje);
    }

    @Override
    public void recibirEstadoBicicleta(String mensaje) {
        if (mensaje != null && !mensaje.trim().isEmpty()) {
            this.ultimoEstadoNotificado = mensaje;
            if (this.listNotificaciones == null) {
                this.listNotificaciones = new java.util.ArrayList<>();
            }
            this.listNotificaciones.add(0, mensaje);
        }
    }

    @Override
    public String recibirEstadoBicicleta() {
        return (this.ultimoEstadoNotificado != null) ? this.ultimoEstadoNotificado : "Sin notificaciones";
    }

    @Override
    public String toString() {
        return getNombre() + " (ID: " + getId() + ")";
    }
}