package org.example.tallerbicicletas.model;


    public class Mecanico extends Persona{
        private TipoEspecializacion tipoEspecializacion;
        private int numeroCertificacion;

        public Mecanico(String nombre, int id, int telefono, TipoEspecializacion tipoEspecializacion, int numeroCertificacion) {
            super(nombre, id, telefono);
            tipoEspecializacion = tipoEspecializacion;
            this.numeroCertificacion = numeroCertificacion;
        }

        public TipoEspecializacion getTipoEspecializacion() {
            return tipoEspecializacion;
        }

        public void setTipoEspecializacion(TipoEspecializacion tipoEspecializacion) {
            this.tipoEspecializacion = tipoEspecializacion;
        }

        public int getNumeroCertificacion() {
            return numeroCertificacion;
        }

        public void setNumeroCertificacion(int numeroCertificacion) {
            this.numeroCertificacion = numeroCertificacion;
        }

        @Override
        public String toString() {
            return "Mecanico{" +
                    "numeroCertificacion=" + numeroCertificacion +
                    ", tipoEspecializacion=" + tipoEspecializacion +
                    '}';
        }
    }
