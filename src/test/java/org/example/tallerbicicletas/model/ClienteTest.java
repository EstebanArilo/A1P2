package org.example.tallerbicicletas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Juan Perez", 1001, 300234567, "Calle 10 # 5-20");
    }

    @Test
    void testConstructorAsignaDatosCorrectamente() {
        assertTrue(cliente.getNombre().equals("Juan Perez"));
        assertTrue(cliente.getId() == 1001);
        assertTrue(cliente.getTelefono() == 300234567);
        assertTrue(cliente.getDireccion().equals("Calle 10 # 5-20"));
    }

    @Test
    void testListasInicianVacias() {
        assertTrue(cliente.getListBicicleta().isEmpty());
        assertTrue(cliente.getListNotificaciones().isEmpty());
    }

    @Test
    void testEstadoInicialSinNotificaciones() {
        assertTrue(cliente.recibirEstadoBicicleta().equals("Sin notificaciones"));
    }

    @Test
    void testSetNombreActualizaCorrectamente() {
        cliente.setNombre("Juan Camilo Perez");
        assertTrue(cliente.getNombre().equals("Juan Camilo Perez"));
    }

    @Test
    void testSetDireccionActualizaCorrectamente() {
        cliente.setDireccion("Carrera 20 # 15-30");
        assertTrue(cliente.getDireccion().equals("Carrera 20 # 15-30"));
    }

    @Test
    void testSetTelefonoActualizaCorrectamente() {
        cliente.setTelefono(300999999);
        assertTrue(cliente.getTelefono() == 300999999);
    }

    @Test
    void testNotificarEstadoBicicletaGuardaMensaje() {
        cliente.notificarEstadoBicicleta("Su bicicleta está lista");
        assertTrue(cliente.recibirEstadoBicicleta().equals("Su bicicleta está lista"));
        assertTrue(cliente.getListNotificaciones().size() == 1);
        assertTrue(cliente.getListNotificaciones().get(0).equals("Su bicicleta está lista"));
    }

    @Test
    void testNotificacionesQuedanEnOrdenMasRecientePrimero() {
        cliente.notificarEstadoBicicleta("Recibida en el taller");
        cliente.notificarEstadoBicicleta("En diagnóstico");
        cliente.notificarEstadoBicicleta("Lista para recoger");

        assertTrue(cliente.getListNotificaciones().size() == 3);
        assertTrue(cliente.getListNotificaciones().get(0).equals("Lista para recoger"));
        assertTrue(cliente.getListNotificaciones().get(2).equals("Recibida en el taller"));
    }

    @Test
    void testNotificarConMensajeVacioNoSeGuarda() {
        cliente.notificarEstadoBicicleta("   ");
        assertTrue(cliente.getListNotificaciones().isEmpty());
        assertTrue(cliente.recibirEstadoBicicleta().equals("Sin notificaciones"));
    }

    @Test
    void testNotificarConMensajeNuloNoSeGuarda() {
        cliente.notificarEstadoBicicleta(null);
        assertTrue(cliente.getListNotificaciones().isEmpty());
    }

    @Test
    void testToStringContieneNombreEId() {
        String texto = cliente.toString();
        assertTrue(texto.contains("Juan Perez"));
        assertTrue(texto.contains("1001"));
    }

    @Test
    void testAgregarBicicletaALaLista() {
        Bicicleta b = new Bicicleta("Trek", "Rojo", 55555, 2022, TipoBicicleta.MTB);
        cliente.getListBicicleta().add(b);
        assertTrue(cliente.getListBicicleta().size() == 1);
        assertTrue(cliente.getListBicicleta().get(0).equals(b));
    }
}