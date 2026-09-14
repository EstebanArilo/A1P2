package org.example.tallerbicicletas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BicicletaTest {

    private Bicicleta bicicleta;

    @BeforeEach
    void setUp() {
        bicicleta = new Bicicleta("Specialized", "Azul", 778899, 2023, TipoBicicleta.RUTA);
    }

    @Test
    void testConstructorAsignaDatosCorrectamente() {
        assertTrue(bicicleta.getMarca().equals("Specialized"));
        assertTrue(bicicleta.getColor().equals("Azul"));
        assertTrue(bicicleta.getNumeroSerial() == 778899);
        assertTrue(bicicleta.getAño() == 2023);
        assertTrue(bicicleta.getTipoBicicleta() == TipoBicicleta.RUTA);
    }

    @Test
    void testSetMarcaActualizaCorrectamente() {
        bicicleta.setMarca("Trek");
        assertTrue(bicicleta.getMarca().equals("Trek"));
    }

    @Test
    void testSetColorActualizaCorrectamente() {
        bicicleta.setColor("Negro");
        assertTrue(bicicleta.getColor().equals("Negro"));
    }

    @Test
    void testSetNumeroSerialActualizaCorrectamente() {
        bicicleta.setNumeroSerial(111222);
        assertTrue(bicicleta.getNumeroSerial() == 111222);
    }

    @Test
    void testSetAñoActualizaCorrectamente() {
        bicicleta.setAño(2020);
        assertTrue(bicicleta.getAño() == 2020);
    }

    @Test
    void testSetTipoBicicletaActualizaCorrectamente() {
        bicicleta.setTipoBicicleta(TipoBicicleta.ELECTRICA);
        assertTrue(bicicleta.getTipoBicicleta() == TipoBicicleta.ELECTRICA);
    }

    @Test
    void testToStringContieneMarcaYSerial() {
        String texto = bicicleta.toString();
        assertTrue(texto.contains("Specialized"));
        assertTrue(texto.contains("778899"));
    }
}