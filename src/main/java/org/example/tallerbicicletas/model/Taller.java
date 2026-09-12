package org.example.tallerbicicletas.model;


    import java.util.ArrayList;

    public class Taller {
        private String nombre;
        private String direccion;
        private int nit;
        private ArrayList<Cliente> listCliente;
        private ArrayList<Mecanico> listmecanico;
        private ArrayList<Bicicleta> listBicicleta;
        private ArrayList<OrdenServicio> listOrdenServicio;

        public Taller(String nombre, String direccion, int nit, ArrayList<Cliente> listCliente, ArrayList<Mecanico> listmecanico, ArrayList<Bicicleta> listBicicleta, ArrayList<OrdenServicio> listOrdenServicio) {
            this.nombre = nombre;
            this.direccion = direccion;
            this.nit = nit;
            this.listCliente = listCliente;
            this.listmecanico = listmecanico;
            this.listBicicleta = listBicicleta;
            this.listOrdenServicio = listOrdenServicio;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public int getNit() {
            return nit;
        }

        public void setNit(int nit) {
            this.nit = nit;
        }

        public ArrayList<Cliente> getListCliente() {
            return listCliente;
        }

        public void setListCliente(ArrayList<Cliente> listCliente) {
            this.listCliente = listCliente;
        }

        public ArrayList<Mecanico> getListmecanico() {
            return listmecanico;
        }

        public void setListmecanico(ArrayList<Mecanico> listmecanico) {
            this.listmecanico = listmecanico;
        }

        public ArrayList<OrdenServicio> getListOrdenServicio() {
            return listOrdenServicio;
        }

        public void setListOrdenServicio(ArrayList<OrdenServicio> listOrdenServicio) {
            this.listOrdenServicio = listOrdenServicio;
        }

        public ArrayList<Bicicleta> getListBicicleta() {
            return listBicicleta;
        }

        public void setListBicicleta(ArrayList<Bicicleta> listBicicleta) {
            this.listBicicleta = listBicicleta;
        }

        @Override
        public String toString() {
            return "Taller{" +
                    "nombre='" + nombre + '\'' +
                    ", direccion='" + direccion + '\'' +
                    ", nit=" + nit +
                    ", listCliente=" + listCliente +
                    ", listmecanico=" + listmecanico +
                    ", listBicicleta=" + listBicicleta +
                    ", listOrdenServicio=" + listOrdenServicio +
                    '}';
        }
    }
