package org.example.tallerbicicletas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TallerTest {

    private Taller taller;
    private Cliente cliente;
    private Mecanico mecanico;
    private Bicicleta bicicleta;

    @BeforeEach
    void setUp() {
        taller = new Taller();
        cliente = new Cliente("Juan Perez", 1001, 300123456, "Calle 10 # 5-20");
        mecanico = new Mecanico("Carlos Ruiz", 2001, 310555111, TipoEspecializacion.SUSPENSION, 9876);
        bicicleta = new Bicicleta("Trek", "Rojo", 55555, 2022, TipoBicicleta.MTB);
    }

    //  DATOS INICIALES

    @Test
    void testTallerIniciaConDatosPorDefecto() {
        assertTrue(taller.getNombre().equals("Bike-Service UQ"));
        assertTrue(taller.getDireccion().equals("Sede Principal"));
        assertTrue(taller.getNit() == 123456789);
    }

    @Test
    void testTallerIniciaConListasVacias() {
        assertTrue(taller.getListCliente().isEmpty());
        assertTrue(taller.getListMecanico().isEmpty());
        assertTrue(taller.getListBicicleta().isEmpty());
        assertTrue(taller.getListOrdenServicio().isEmpty());
    }

    //  CLIENTES

    @Test
    void testRegistrarClienteLoAgregaALaLista() {
        taller.registrarCliente(cliente);
        assertTrue(taller.getListCliente().size() == 1);
        assertTrue(taller.getListCliente().get(0).equals(cliente));
    }

    @Test
    void testBuscarClientePorIdExistente() {
        taller.registrarCliente(cliente);
        Cliente encontrado = taller.buscarClientePorId(1001);
        assertTrue(encontrado != null);
        assertTrue(encontrado.equals(cliente));
    }

    @Test
    void testBuscarClientePorIdInexistenteRetornaNull() {
        taller.registrarCliente(cliente);
        Cliente encontrado = taller.buscarClientePorId(9999);
        assertTrue(encontrado == null);
    }

    @Test
    void testEliminarClienteExistenteRetornaTrue() {
        taller.registrarCliente(cliente);
        boolean resultado = taller.eliminarCliente(1001);
        assertTrue(resultado);
        assertTrue(taller.getListCliente().isEmpty());
    }

    @Test
    void testEliminarClienteInexistenteRetornaFalse() {
        boolean resultado = taller.eliminarCliente(9999);
        assertTrue(!resultado);
    }

    @Test
    void testActualizarClienteExistente() {
        taller.registrarCliente(cliente);
        boolean resultado = taller.actualizarCliente(1001, "Juan Camilo Perez", 300999999, "Nueva Direccion 123");

        assertTrue(resultado);
        Cliente actualizado = taller.buscarClientePorId(1001);
        assertTrue(actualizado.getNombre().equals("Juan Camilo Perez"));
        assertTrue(actualizado.getTelefono() == 300999999);
        assertTrue(actualizado.getDireccion().equals("Nueva Direccion 123"));
    }

    @Test
    void testActualizarClienteInexistenteRetornaFalse() {
        boolean resultado = taller.actualizarCliente(9999, "Nombre", 300000000, "Direccion");
        assertTrue(!resultado);
    }

    // MECANICOS

    @Test
    void testRegistrarMecanicoLoAgregaALaLista() {
        taller.registrarMecanico(mecanico);
        assertTrue(taller.getListMecanico().size() == 1);
        assertTrue(taller.getListMecanico().get(0).equals(mecanico));
    }

    @Test
    void testBuscarMecanicoPorIdExistente() {
        taller.registrarMecanico(mecanico);
        Mecanico encontrado = taller.buscarMecanicoPorId(2001);
        assertTrue(encontrado != null);
        assertTrue(encontrado.equals(mecanico));
    }

    @Test
    void testEliminarMecanicoExistenteRetornaTrue() {
        taller.registrarMecanico(mecanico);
        boolean resultado = taller.eliminarMecanico(2001);
        assertTrue(resultado);
        assertTrue(taller.getListMecanico().isEmpty());
    }

    @Test
    void testEliminarMecanicoInexistenteRetornaFalse() {
        boolean resultado = taller.eliminarMecanico(9999);
        assertTrue(!resultado);
    }

    @Test
    void testActualizarMecanicoExistente() {
        taller.registrarMecanico(mecanico);
        boolean resultado = taller.actualizarMecanico(2001, "Carlos A. Ruiz", 310000000,
                TipoEspecializacion.BICICLETAS_ELECTRICAS, 1111);

        assertTrue(resultado);
        Mecanico actualizado = taller.buscarMecanicoPorId(2001);
        assertTrue(actualizado.getNombre().equals("Carlos A. Ruiz"));
        assertTrue(actualizado.getTipoEspecializacion() == TipoEspecializacion.BICICLETAS_ELECTRICAS);
        assertTrue(actualizado.getNumeroCertificacion() == 1111);
    }

    @Test
    void testActualizarMecanicoInexistenteRetornaFalse() {
        boolean resultado = taller.actualizarMecanico(9999, "Nombre", 300000000, TipoEspecializacion.OTRA, 0);
        assertTrue(!resultado);
    }

    // BICICLETAS

    @Test
    void testRegistrarBicicletaLaAgregaALaLista() {
        taller.registrarBicicleta(bicicleta);
        assertTrue(taller.getListBicicleta().size() == 1);
        assertTrue(taller.getListBicicleta().get(0).equals(bicicleta));
    }

    @Test
    void testBuscarBicicletaPorNumeroSerialExistente() {
        taller.registrarBicicleta(bicicleta);
        Bicicleta encontrada = taller.buscarBicicletaPorNumeroSerial(55555);
        assertTrue(encontrada != null);
        assertTrue(encontrada.equals(bicicleta));
    }

    @Test
    void testBuscarBicicletaPorNumeroSerialInexistenteRetornaNull() {
        Bicicleta encontrada = taller.buscarBicicletaPorNumeroSerial(99999);
        assertTrue(encontrada == null);
    }

    @Test
    void testEliminarBicicletaExistenteRetornaTrue() {
        taller.registrarBicicleta(bicicleta);
        boolean resultado = taller.eliminarBicicleta(55555);
        assertTrue(resultado);
        assertTrue(taller.getListBicicleta().isEmpty());
    }

    @Test
    void testEliminarBicicletaInexistenteRetornaFalse() {
        boolean resultado = taller.eliminarBicicleta(99999);
        assertTrue(!resultado);
    }

    @Test
    void testActualizarBicicletaExistente() {
        taller.registrarBicicleta(bicicleta);
        boolean resultado = taller.actualizarBicicleta("Giant", "Negro", 55555, 2024, TipoBicicleta.URBANA);

        assertTrue(resultado);
        Bicicleta actualizada = taller.buscarBicicletaPorNumeroSerial(55555);
        assertTrue(actualizada.getMarca().equals("Giant"));
        assertTrue(actualizada.getColor().equals("Negro"));
        assertTrue(actualizada.getAño() == 2024);
        assertTrue(actualizada.getTipoBicicleta() == TipoBicicleta.URBANA);
    }

    @Test
    void testActualizarBicicletaInexistenteRetornaFalse() {
        boolean resultado = taller.actualizarBicicleta("Marca", "Color", 99999, 2020, TipoBicicleta.OTRA);
        assertTrue(!resultado);
    }

    @Test
    void testAsociarBicicletaAClienteExistente() {
        taller.registrarCliente(cliente);
        boolean resultado = taller.asociarBicicletaACliente(1001, bicicleta);

        assertTrue(resultado);
        assertTrue(cliente.getListBicicleta().size() == 1);
        assertTrue(cliente.getListBicicleta().get(0).equals(bicicleta));
        assertTrue(taller.getListBicicleta().contains(bicicleta));
    }

    @Test
    void testAsociarBicicletaAClienteInexistenteRetornaFalse() {
        boolean resultado = taller.asociarBicicletaACliente(9999, bicicleta);
        assertTrue(!resultado);
    }

    @Test
    void testAsociarBicicletaNoLaDuplicaEnListaGeneral() {
        taller.registrarCliente(cliente);
        taller.registrarBicicleta(bicicleta);
        taller.asociarBicicletaACliente(1001, bicicleta);

        assertTrue(taller.getListBicicleta().size() == 1);
    }

    // ORDENES DE SERVICIO

    @Test
    void testCrearOrdenServicioLaAgregaALaLista() {
        OrdenServicio orden = new OrdenServicio(LocalDate.of(2026, 9, 14), LocalTime.of(10, 0),
                bicicleta, mecanico, "Frenos chillan", "Pastillas gastadas", "Cambio de pastillas", 85000.0, 1);
        taller.crearOrdenServicio(orden);

        assertTrue(taller.getListOrdenServicio().size() == 1);
        assertTrue(taller.getListOrdenServicio().get(0).equals(orden));
    }

    @Test
    void testBuscarOrdenServicioExistente() {
        OrdenServicio orden = new OrdenServicio(LocalDate.of(2026, 9, 14), LocalTime.of(10, 0),
                bicicleta, mecanico, "Frenos chillan", "Pastillas gastadas", "Cambio de pastillas", 85000.0, 1);
        taller.crearOrdenServicio(orden);

        OrdenServicio encontrada = taller.buscarOrdenServicio(1);
        assertTrue(encontrada != null);
        assertTrue(encontrada.equals(orden));
    }

    @Test
    void testBuscarOrdenServicioInexistenteRetornaNull() {
        OrdenServicio encontrada = taller.buscarOrdenServicio(999);
        assertTrue(encontrada == null);
    }

    @Test
    void testEliminarOrdenServicioExistenteRetornaTrue() {
        OrdenServicio orden = new OrdenServicio(LocalDate.of(2026, 9, 14), LocalTime.of(10, 0),
                bicicleta, mecanico, "Frenos chillan", "Pastillas gastadas", "Cambio de pastillas", 85000.0, 1);
        taller.crearOrdenServicio(orden);

        boolean resultado = taller.eliminarOrdenServicio(1);
        assertTrue(resultado);
        assertTrue(taller.getListOrdenServicio().isEmpty());
    }

    @Test
    void testEliminarOrdenServicioInexistenteRetornaFalse() {
        boolean resultado = taller.eliminarOrdenServicio(999);
        assertTrue(!resultado);
    }

    @Test
    void testActualizarOrdenServicioExistente() {
        OrdenServicio orden = new OrdenServicio(LocalDate.of(2026, 9, 14), LocalTime.of(10, 0),
                bicicleta, mecanico, "Frenos chillan", "Pastillas gastadas", "Cambio de pastillas", 85000.0, 1);
        taller.crearOrdenServicio(orden);

        LocalDate nuevaFecha = LocalDate.of(2026, 9, 20);
        boolean resultado = taller.actualizarOrdenServicio(nuevaFecha, LocalTime.of(14, 0), bicicleta, mecanico,
                "Cadena suelta", "Cadena desgastada", "Cambio de cadena", 60000.0, 1);

        assertTrue(resultado);
        OrdenServicio actualizada = taller.buscarOrdenServicio(1);
        assertTrue(actualizada.getFechaIngreso().equals(nuevaFecha));
        assertTrue(actualizada.getMotivoServicio().equals("Cadena suelta"));
        assertTrue(actualizada.getCostoTotal() == 60000.0);
    }

    @Test
    void testActualizarOrdenServicioInexistenteRetornaFalse() {
        boolean resultado = taller.actualizarOrdenServicio(LocalDate.now(), LocalTime.now(), bicicleta, mecanico,
                "Motivo", "Diagnostico", "Trabajo", 0.0, 999);
        assertTrue(!resultado);
    }

    @Test
    void testAsignarMecanicoAOrdenExistente() {
        Mecanico otroMecanico = new Mecanico("Laura Gomez", 2002, 311123456, TipoEspecializacion.FRENOS_Y_TRANSIMISION, 5555);
        OrdenServicio orden = new OrdenServicio(LocalDate.of(2026, 9, 14), LocalTime.of(10, 0),
                bicicleta, mecanico, "Frenos chillan", "Pastillas gastadas", "Cambio de pastillas", 85000.0, 1);
        taller.crearOrdenServicio(orden);
        taller.registrarMecanico(otroMecanico);

        boolean resultado = taller.asignarMecanicoAOrden(1, 2002);
        assertTrue(resultado);
        assertTrue(taller.buscarOrdenServicio(1).getMecanico().equals(otroMecanico));
    }

    @Test
    void testAsignarMecanicoAOrdenInexistenteRetornaFalse() {
        taller.registrarMecanico(mecanico);
        boolean resultado = taller.asignarMecanicoAOrden(999, 2001);
        assertTrue(!resultado);
    }

    @Test
    void testAsignarMecanicoInexistenteAOrdenRetornaFalse() {
        OrdenServicio orden = new OrdenServicio(LocalDate.of(2026, 9, 14), LocalTime.of(10, 0),
                bicicleta, mecanico, "Frenos chillan", "Pastillas gastadas", "Cambio de pastillas", 85000.0, 1);
        taller.crearOrdenServicio(orden);

        boolean resultado = taller.asignarMecanicoAOrden(1, 9999);
        assertTrue(!resultado);
    }

    @Test
    void testBuscarOrdenesDelMecanicoRetornaSoloLasSuyas() {
        Mecanico otroMecanico = new Mecanico("Laura Gomez", 2002, 311123456, TipoEspecializacion.FRENOS_Y_TRANSIMISION, 5555);
        OrdenServicio orden1 = new OrdenServicio(LocalDate.of(2026, 9, 14), LocalTime.of(10, 0),
                bicicleta, mecanico, "Frenos chillan", "Pastillas gastadas", "Cambio de pastillas", 85000.0, 1);
        OrdenServicio orden2 = new OrdenServicio(LocalDate.of(2026, 9, 15), LocalTime.of(11, 0),
                bicicleta, otroMecanico, "Rueda pinchada", "Llanta rota", "Cambio de llanta", 40000.0, 2);

        taller.crearOrdenServicio(orden1);
        taller.crearOrdenServicio(orden2);

        assertTrue(taller.buscarOrdenesDelMecanico(2001).size() == 1);
        assertTrue(taller.buscarOrdenesDelMecanico(2001).get(0).equals(orden1));
    }

    @Test
    void testBuscarOrdenesDelMecanicoSinOrdenesRetornaListaVacia() {
        assertTrue(taller.buscarOrdenesDelMecanico(2001).isEmpty());
    }

    //  NOTIFICACIONES

    @Test
    void testRegistrarNotificacionEnHistorial() {
        taller.registrarNotificacionEnHistorial("Taller cerrado por mantenimiento");
        assertTrue(taller.getHistorialNotificacionesGeneral().size() == 1);
        assertTrue(taller.getHistorialNotificacionesGeneral().get(0).equals("Taller cerrado por mantenimiento"));
    }

    @Test
    void testRegistrarNotificacionNulaNoSeAgrega() {
        taller.registrarNotificacionEnHistorial(null);
        assertTrue(taller.getHistorialNotificacionesGeneral().isEmpty());
    }

    @Test
    void testNotificarTodosEnviaAmTodosLosClientes() {
        Cliente otroCliente = new Cliente("Ana Torres", 1002, 300765432, "Carrera 5 # 10-15");
        taller.registrarCliente(cliente);
        taller.registrarCliente(otroCliente);

        taller.notificarTodos("El taller abrirá una hora más tarde mañana");

        assertTrue(cliente.recibirEstadoBicicleta().equals("El taller abrirá una hora más tarde mañana"));
        assertTrue(otroCliente.recibirEstadoBicicleta().equals("El taller abrirá una hora más tarde mañana"));
        assertTrue(taller.getHistorialNotificacionesGeneral().size() == 1);
    }

    // TOSTRING

    @Test
    void testToStringContieneNombreYConteos() {
        taller.registrarCliente(cliente);
        taller.registrarMecanico(mecanico);
        String texto = taller.toString();

        assertTrue(texto.contains("Bike-Service UQ"));
        assertTrue(texto.contains("listCliente=1"));
        assertTrue(texto.contains("listMecanico=1"));
    }
}