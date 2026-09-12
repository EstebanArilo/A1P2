package org.example.tallerbicicletas.model;

    public class Cliente extends Persona{
        private String direccion;

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

        @Override
        public String toString() {
            return "Cliente{" +
                    "direccion='" + direccion + '\'' +
                    '}';
        }
    }

