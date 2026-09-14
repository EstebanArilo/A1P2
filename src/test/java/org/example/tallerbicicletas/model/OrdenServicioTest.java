package org.example.tallerbicicletas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrdenServicioTest {

    private OrdenServicio orden;
    private Bicicleta bicicleta;
    private Mecanico mecanico;
    private LocalDate fecha;
    private LocalTime hora;

    @BeforeEach
    void setUp() {
        bicicleta = new Bicicleta("Trek", "Rojo", 55555, 2022, TipoBicicleta.MTB);
        mecanico = new Mecanico("Carlos Ruiz", 2001, 310555111, TipoEspecializacion.SUSPENSION, 9876);
        fecha = LocalDate.of(2026, 9, 14);
        hora = LocalTime.of(10, 30);
        orden = new OrdenServicio(fecha, hora, bicicleta, mecanico, "Frenos chillan",
                "Pastillas desgastadas", "Cambio de pastillas", 85000.0, 1);
    }

    @Test
    void testConstructorAsignaDatosCorrectamente() {
        assertTrue(orden.getFechaIngreso().equals(fecha));
        assertTrue(orden.getHora().equals(hora));
        assertTrue(orden.getBicicleta().equals(bicicleta));
        assertTrue(orden.getMecanico().equals(mecanico));
        assertTrue(orden.getMotivoServicio().equals("Frenos chillan"));
        assertTrue(orden.getDiagnostico().equals("Pastillas desgastadas"));
        assertTrue(orden.getTrabajosRealizados().equals("Cambio de pastillas"));
        assertTrue(orden.getCostoTotal() == 85000.0);
        assertTrue(orden.getIdOrden() == 1);
    }

    @Test
    void testSetFechaIngresoActualizaCorrectamente() {
        LocalDate nuevaFecha = LocalDate.of(2026, 10, 1);
        orden.setFechaIngreso(nuevaFecha);
        assertTrue(orden.getFechaIngreso().equals(nuevaFecha));
    }

    @Test
    void testSetHoraActualizaCorrectamente() {
        LocalTime nuevaHora = LocalTime.of(15, 0);
        orden.setHora(nuevaHora);
        assertTrue(orden.getHora().equals(nuevaHora));
    }

    @Test
    void testSetCostoTotalActualizaCorrectamente() {
        orden.setCostoTotal(120000.0);
        assertTrue(orden.getCostoTotal() == 120000.0);
    }

    @Test
    void testSetMecanicoActualizaCorrectamente() {
        Mecanico otroMecanico = new Mecanico("Laura Gomez", 2002, 311123456, TipoEspecializacion.FRENOS_Y_TRANSIMISION, 5555);
        orden.setMecanico(otroMecanico);
        assertTrue(orden.getMecanico().equals(otroMecanico));
    }

    @Test
    void testSetBicicletaActualizaCorrectamente() {
        Bicicleta otraBici = new Bicicleta("Giant", "Verde", 99999, 2021, TipoBicicleta.URBANA);
        orden.setBicicleta(otraBici);
        assertTrue(orden.getBicicleta().equals(otraBici));
    }

    @Test
    void testSetDiagnosticoYTrabajosRealizados() {
        orden.setDiagnostico("Cadena desgastada");
        orden.setTrabajosRealizados("Cambio de cadena y piñón");
        assertTrue(orden.getDiagnostico().equals("Cadena desgastada"));
        assertTrue(orden.getTrabajosRealizados().equals("Cambio de cadena y piñón"));
    }

    @Test
    void testSetIdOrdenActualizaCorrectamente() {
        orden.setIdOrden(2);
        assertTrue(orden.getIdOrden() == 2);
    }

    @Test
    void testToStringContieneMotivoServicio() {
        String texto = orden.toString();
        assertTrue(texto.contains("Frenos chillan"));
    }
}