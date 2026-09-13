package org.example.tallerbicicletas.model;

    public abstract class Persona {
        protected String nombre;
        protected int id;
        protected int telefono;

        public Persona(String nombre, int id, int telefono) {
            this.nombre = nombre;
            this.id = id;
            this.telefono = telefono;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTelefono() {
            return telefono;
        }

        public void setTelefono(int telefono) {
            this.telefono = telefono;
        }

        @Override
        public String toString() {
            return "Persona{" +
                    "nombre='" + nombre + '\'' +
                    ", id=" + id +
                    ", telefono=" + telefono +
                    '}';
        }
    }

