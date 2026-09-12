package org.example.tallerbicicletas.model;


    import java.util.ArrayList;

    public class Taller {
        private String nombre;
        private String direccion;
        private int nit;
        private ArrayList<Cliente> listCliente;
        private ArrayList<Mecanico> listMecanico;
        private ArrayList<Bicicleta> listBicicleta;
        private ArrayList<OrdenServicio> listOrdenServicio;

        public Taller(String nombre, String direccion, int nit, ArrayList<Cliente> listCliente, ArrayList<Mecanico> listMecanico, ArrayList<Bicicleta> listBicicleta, ArrayList<OrdenServicio> listOrdenServicio) {
            this.nombre = nombre;
            this.direccion = direccion;
            this.nit = nit;
            this.listCliente = listCliente;
            this.listMecanico = listMecanico;
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

        public ArrayList<Mecanico> getListMecanico() {
            return listMecanico;
        }

        public void setListmecanico(ArrayList<Mecanico> listMecanico) {
            this.listMecanico = listMecanico;
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
                    ", listMecanico=" + listMecanico +
                    ", listBicicleta=" + listBicicleta +
                    ", listOrdenServicio=" + listOrdenServicio +
                    '}';
        }

        //METODOS
        public void registrarCliente(Cliente nuevoCliente) {
            listCliente.add(nuevoCliente);
        }

        public Cliente buscarClientePorId(int id) {
            for (Cliente c : this.listCliente) {
                if (c.getId() == (id)) {
                    return c;
                }
            }
            return null;
        }

        public boolean eliminarCliente(int id) {
            for (Cliente c : this.listCliente) {
                if (c.getId() == (id)) {
                    return this.listCliente.remove(c);
                }
            }
            return false;
        }
        public void registrarMecanico(Mecanico nuevoMecanico) {
            listMecanico.add(nuevoMecanico);
        }

        public Mecanico buscarMecanicoPorId(int id) {
            for (Mecanico m : this.listMecanico) {
                if (m.getId() == (id)) {
                    return m;
                }
            }
            return null;
        }

        public boolean eliminarMecanico(int id) {
            for (Mecanico m : this.listMecanico) {
                if (m.getId() == (id)) {
                    return this.listMecanico.remove(m);
                }
            }
            return false;
        }
        public void registrarBicicleta(Bicicleta nuevoBicicleta) {
            listBicicleta.add(nuevoBicicleta);
        }
        public boolean eliminarBicicleta(int numeroSerial) {
            for (Bicicleta b : this.listBicicleta) {
                if (b.getNumeroSerial() == (numeroSerial)) {
                    return this.listBicicleta.remove(b);
                }
            }
            return false;
        }
    }
