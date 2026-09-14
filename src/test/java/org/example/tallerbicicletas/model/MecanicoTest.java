package org.example.tallerbicicletas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MecanicoTest {

    private Mecanico mecanico;

    @BeforeEach
    void setUp() {
        mecanico = new Mecanico("Carlos Ruiz", 2001, 310555111, TipoEspecializacion.SUSPENSION, 9876);
    }

    @Test
    void testConstructorAsignaDatosCorrectamente() {
        assertTrue(mecanico.getNombre().equals("Carlos Ruiz"));
        assertTrue(mecanico.getId() == 2001);
        assertTrue(mecanico.getTelefono() == 310555111);
        assertTrue(mecanico.getTipoEspecializacion() == TipoEspecializacion.SUSPENSION);
        assertTrue(mecanico.getNumeroCertificacion() == 9876);
    }

    @Test
    void testSetTipoEspecializacionActualizaCorrectamente() {
        mecanico.setTipoEspecializacion(TipoEspecializacion.BICICLETAS_ELECTRICAS);
        assertTrue(mecanico.getTipoEspecializacion() == TipoEspecializacion.BICICLETAS_ELECTRICAS);
    }

    @Test
    void testSetNumeroCertificacionActualizaCorrectamente() {
        mecanico.setNumeroCertificacion(1234);
        assertTrue(mecanico.getNumeroCertificacion() == 1234);
    }

    @Test
    void testSetNombreHeredadoDePersona() {
        mecanico.setNombre("Carlos Andres Ruiz");
        assertTrue(mecanico.getNombre().equals("Carlos Andres Ruiz"));
    }

    @Test
    void testSetTelefonoHeredadoDePersona() {
        mecanico.setTelefono(300999888);
        assertTrue(mecanico.getTelefono() == 300999888);
    }

    @Test
    void testToStringContieneCertificacionYEspecializacion() {
        String texto = mecanico.toString();
        assertTrue(texto.contains("9876"));
        assertTrue(texto.contains("SUSPENSION"));
    }
}