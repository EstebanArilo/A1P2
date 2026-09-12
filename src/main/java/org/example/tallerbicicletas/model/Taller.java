package org.example.tallerbicicletas.model;


    import java.time.LocalDate;
    import java.time.LocalTime;
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
        public boolean actualizarCliente(int id, String nuevoNombre, int nuevoTelefono, String nuevaDireccion) {
            Cliente c= buscarClientePorId(id);

            if (c != null) {
                c.setNombre(nuevoNombre);
                c.setTelefono(nuevoTelefono);
                c.setDireccion(nuevaDireccion);
                return true;
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
        public boolean actualizarMecanico(int id, String nuevoNombre, int nuevoTelefono, TipoEspecializacion nuevoTipoEspecializacion, int NuevoNumeroCertificacion) {
            Mecanico m= buscarMecanicoPorId(id);

            if (m != null) {
                m.setNombre(nuevoNombre);
                m.setTelefono(nuevoTelefono);
                m.setTipoEspecializacion(nuevoTipoEspecializacion);
                m.setNumeroCertificacion(NuevoNumeroCertificacion);
                return true;
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
        public Bicicleta buscarBicicletaPorNumeroSerial(int numeroSerial) {
            for (Bicicleta b : this.listBicicleta) {
                if (b.getNumeroSerial() == (numeroSerial)) {
                    return b;
                }
            }
            return null;
        }
        public boolean actualizarBicicleta(String nuevoMarca, String nuevoColor, int numeroSerial, int nuevoAño, TipoBicicleta nuevoTipoBicicleta) {
            Bicicleta b= buscarBicicletaPorNumeroSerial(numeroSerial);

            if (b != null) {
                b.setMarca(nuevoMarca);
                b.setColor(nuevoColor);
                b.setAño(nuevoAño);
                b.setTipoBicicleta(nuevoTipoBicicleta);
                return true;
            }
            return false;
        }
        public void crearOrdenServicio(OrdenServicio nuevoOrdenServicio) {
            listOrdenServicio.add(nuevoOrdenServicio);
        }

        public boolean eliminarOrdenServicio(int idOrden) {
            for (OrdenServicio o : this.listOrdenServicio) {
                if (o.getIdOrden() == (idOrden)) {
                    return this.listBicicleta.remove(o);
                }
            }
            return false;
        }
        public OrdenServicio buscarOrdenServicio(int idOrden) {
            for (OrdenServicio o : this.listOrdenServicio) {
                if (o.getIdOrden() == (idOrden)) {
                    return o;
                }
            }
            return null;
        }
        public boolean actualizarOrdenServicio(LocalDate nuevoFechaIngreso, LocalTime nuevoHora, Bicicleta nuevoBicicleta, Mecanico nuevoMecanico, String nuevoMotivoServicio, String nuevoDiagnostico, String nuevoTrabajosRealizados, double nuevoCostoTotal, int idOrden) {
            OrdenServicio o = buscarOrdenServicio(idOrden);

            if (o != null) {
                o.setFechaIngreso(nuevoFechaIngreso);
                o.setHora(nuevoHora);
                o.setBicicleta(nuevoBicicleta);
                o.setMecanico(nuevoMecanico);
                o.setMotivoServicio(nuevoMotivoServicio);
                o.setDiagnostico(nuevoDiagnostico);
                o.setTrabajosRealizados(nuevoTrabajosRealizados);
                o.setCostoTotal(nuevoCostoTotal);
                return true;
            }
            return false;
        }
        public void notificarTodos(String mensaje) {

            for (INotificable visitante : listCliente) {

                visitante.notificarEstadoBicicleta(mensaje);
            }
        }
        public boolean asignarMecanicoAOrden(int idOrden, int idMecanico) {
            OrdenServicio orden = buscarOrdenServicio(idOrden);
            Mecanico mecanico = buscarMecanicoPorId(idMecanico);

            if (orden != null && mecanico != null) {
                orden.setMecanico(mecanico);
                return true;
            }
            return false;
        }

        public ArrayList<OrdenServicio> buscarOrdenesDelMecanico(int idMecanico) {
            ArrayList<OrdenServicio> ordenesDelMecanico = new ArrayList<>();

            for (OrdenServicio orden : this.listOrdenServicio) {
                if (orden.getMecanico() != null && orden.getMecanico().getId() == idMecanico) {
                    ordenesDelMecanico.add(orden);
                    System.out.println("Orden asignada:" + orden.getIdOrden() + "Motivo del servicio: "+ orden.getMotivoServicio()+"Diagnostico: "+orden.getDiagnostico()+"Costo Total:"+ orden.getCostoTotal());
                }
            }
            if (ordenesDelMecanico.isEmpty()) {
                System.out.println("No se encontraron órdenes asignadas al mecánico con ID: " + idMecanico);
            }
            return ordenesDelMecanico;
        }
    }
