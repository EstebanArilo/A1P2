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
    private ArrayList<String> historialNotificacionesGeneral;

    public Taller() {
        this.nombre = "Bike-Service UQ";
        this.direccion = "Sede Principal";
        this.nit = 123456789;
        this.listCliente = new ArrayList<>();
        this.listMecanico = new ArrayList<>();
        this.listBicicleta = new ArrayList<>();
        this.listOrdenServicio = new ArrayList<>();
        this.historialNotificacionesGeneral = new ArrayList<>();
    }

    // --- GETTERS Y SETTERS ---

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getNit() { return nit; }
    public void setNit(int nit) { this.nit = nit; }

    public ArrayList<Cliente> getListCliente() { return listCliente; }
    public void setListCliente(ArrayList<Cliente> listCliente) { this.listCliente = listCliente; }

    public ArrayList<Mecanico> getListMecanico() { return listMecanico; }
    public void setListMecanico(ArrayList<Mecanico> listMecanico) { this.listMecanico = listMecanico; }

    public ArrayList<OrdenServicio> getListOrdenServicio() { return listOrdenServicio; }
    public void setListOrdenServicio(ArrayList<OrdenServicio> listOrdenServicio) { this.listOrdenServicio = listOrdenServicio; }

    public ArrayList<Bicicleta> getListBicicleta() { return listBicicleta; }
    public void setListBicicleta(ArrayList<Bicicleta> listBicicleta) { this.listBicicleta = listBicicleta; }

    public ArrayList<String> getHistorialNotificacionesGeneral() { return historialNotificacionesGeneral; }

    // --- GESTIÓN DE NOTIFICACIONES ---

    public void registrarNotificacionEnHistorial(String mensaje) {
        if (mensaje != null) {
            this.historialNotificacionesGeneral.add(0, mensaje);
        }
    }

    public void notificarTodos(String mensaje) {
        for (INotificable visitante : listCliente) {
            visitante.notificarEstadoBicicleta(mensaje);
        }
        registrarNotificacionEnHistorial("NOTIFICACIÓN GENERAL: " + mensaje);
    }

    // --- GESTIÓN DE CLIENTES ---

    public void registrarCliente(Cliente nuevoCliente) {
        listCliente.add(nuevoCliente);
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente c : this.listCliente) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean eliminarCliente(int id) {
        Cliente c = buscarClientePorId(id);
        if (c != null) {
            return this.listCliente.remove(c);
        }
        return false;
    }

    public boolean actualizarCliente(int id, String nuevoNombre, int nuevoTelefono, String nuevaDireccion) {
        Cliente c = buscarClientePorId(id);
        if (c != null) {
            c.setNombre(nuevoNombre);
            c.setTelefono(nuevoTelefono);
            c.setDireccion(nuevaDireccion);
            return true;
        }
        return false;
    }

    // --- GESTIÓN DE MECÁNICOS ---

    public void registrarMecanico(Mecanico nuevoMecanico) {
        listMecanico.add(nuevoMecanico);
    }

    public Mecanico buscarMecanicoPorId(int id) {
        for (Mecanico m : this.listMecanico) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public boolean eliminarMecanico(int id) {
        Mecanico m = buscarMecanicoPorId(id);
        if (m != null) {
            return this.listMecanico.remove(m);
        }
        return false;
    }

    public boolean actualizarMecanico(int id, String nuevoNombre, int nuevoTelefono, TipoEspecializacion nuevoTipoEspecializacion, int nuevoNumeroCertificacion) {
        Mecanico m = buscarMecanicoPorId(id);
        if (m != null) {
            m.setNombre(nuevoNombre);
            m.setTelefono(nuevoTelefono);
            m.setTipoEspecializacion(nuevoTipoEspecializacion);
            m.setNumeroCertificacion(nuevoNumeroCertificacion);
            return true;
        }
        return false;
    }

    // --- GESTIÓN DE BICICLETAS ---

    public void registrarBicicleta(Bicicleta nuevaBicicleta) {
        this.listBicicleta.add(nuevaBicicleta);
    }

    public boolean asociarBicicletaACliente(int idCliente, Bicicleta bicicleta) {
        Cliente c = buscarClientePorId(idCliente);
        if (c != null && bicicleta != null) {
            if (c.getListBicicleta() != null) {
                c.getListBicicleta().add(bicicleta);
            }
            if (!listBicicleta.contains(bicicleta)) {
                listBicicleta.add(bicicleta);
            }
            return true;
        }
        return false;
    }

    public boolean eliminarBicicleta(int numeroSerial) {
        Bicicleta b = buscarBicicletaPorNumeroSerial(numeroSerial);
        if (b != null) {
            return this.listBicicleta.remove(b);
        }
        return false;
    }

    public Bicicleta buscarBicicletaPorNumeroSerial(int numeroSerial) {
        for (Bicicleta b : this.listBicicleta) {
            if (b.getNumeroSerial() == numeroSerial) {
                return b;
            }
        }
        return null;
    }

    public boolean actualizarBicicleta(String nuevaMarca, String nuevoColor, int numeroSerial, int nuevoAño, TipoBicicleta nuevoTipoBicicleta) {
        Bicicleta b = buscarBicicletaPorNumeroSerial(numeroSerial);
        if (b != null) {
            b.setMarca(nuevaMarca);
            b.setColor(nuevoColor);
            b.setAño(nuevoAño);
            b.setTipoBicicleta(nuevoTipoBicicleta);
            return true;
        }
        return false;
    }

    // --- GESTIÓN DE ÓRDENES DE SERVICIO ---

    public void crearOrdenServicio(OrdenServicio nuevaOrdenServicio) {
        listOrdenServicio.add(nuevaOrdenServicio);
    }

    public boolean eliminarOrdenServicio(int idOrden) {
        OrdenServicio o = buscarOrdenServicio(idOrden);
        if (o != null) {
            return this.listOrdenServicio.remove(o);
        }
        return false;
    }

    public OrdenServicio buscarOrdenServicio(int idOrden) {
        for (OrdenServicio o : this.listOrdenServicio) {
            if (o.getIdOrden() == idOrden) {
                return o;
            }
        }
        return null;
    }

    public boolean actualizarOrdenServicio(LocalDate nuevaFechaIngreso, LocalTime nuevaHora, Bicicleta nuevaBicicleta, Mecanico nuevoMecanico, String nuevoMotivoServicio, String nuevoDiagnostico, String nuevosTrabajosRealizados, double nuevoCostoTotal, int idOrden) {
        OrdenServicio o = buscarOrdenServicio(idOrden);
        if (o != null) {
            o.setFechaIngreso(nuevaFechaIngreso);
            o.setHora(nuevaHora);
            o.setBicicleta(nuevaBicicleta);
            o.setMecanico(nuevoMecanico);
            o.setMotivoServicio(nuevoMotivoServicio);
            o.setDiagnostico(nuevoDiagnostico);
            o.setTrabajosRealizados(nuevosTrabajosRealizados);
            o.setCostoTotal(nuevoCostoTotal);
            return true;
        }
        return false;
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
            }
        }
        return ordenesDelMecanico;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", nit=" + nit +
                ", listCliente=" + listCliente.size() +
                ", listMecanico=" + listMecanico.size() +
                ", listBicicleta=" + listBicicleta.size() +
                ", listOrdenServicio=" + listOrdenServicio.size() +
                '}';
    }
}