package org.example.tallerbicicletas.model;


    import java.time.LocalDate;

    public class OrdenServicio {
        private LocalDate fechaIngreso;
        private String hora;
        private Bicicleta bicicleta;
        private Mecanico mecanico;
        private String motivoServicio;
        private String diagnostico;
        private int trabajosRealizados;
        private double costoTotal;

        public OrdenServicio(LocalDate fechaIngreso, String hora, Bicicleta bicicleta, Mecanico mecanico, String motivoServicio, String diagnostico, int trabajosRealizados, double costoTotal) {
            this.fechaIngreso = fechaIngreso;
            this.hora = hora;
            this.bicicleta = bicicleta;
            this.mecanico = mecanico;
            this.motivoServicio = motivoServicio;
            this.diagnostico = diagnostico;
            this.trabajosRealizados = trabajosRealizados;
            this.costoTotal = costoTotal;
        }

        public LocalDate getFechaIngreso() {
            return fechaIngreso;
        }

        public void setFechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public Mecanico getMecanico() {
            return mecanico;
        }

        public void setMecanico(Mecanico mecanico) {
            this.mecanico = mecanico;
        }

        public Bicicleta getBicicleta() {
            return bicicleta;
        }

        public void setBicicleta(Bicicleta bicicleta) {
            this.bicicleta = bicicleta;
        }

        public String getMotivoServicio() {
            return motivoServicio;
        }

        public void setMotivoServicio(String motivoServicio) {
            this.motivoServicio = motivoServicio;
        }

        public String getDiagnostico() {
            return diagnostico;
        }

        public void setDiagnostico(String diagnostico) {
            this.diagnostico = diagnostico;
        }

        public int getTrabajosRealizados() {
            return trabajosRealizados;
        }

        public void setTrabajosRealizados(int trabajosRealizados) {
            this.trabajosRealizados = trabajosRealizados;
        }

        public double getCostoTotal() {
            return costoTotal;
        }

        public void setCostoTotal(double costoTotal) {
            this.costoTotal = costoTotal;
        }

        @Override
        public String toString() {
            return "OrdenServicio{" +
                    "fechaIngreso=" + fechaIngreso +
                    ", hora='" + hora + '\'' +
                    ", bicicleta=" + bicicleta +
                    ", mecanico=" + mecanico +
                    ", motivoServicio='" + motivoServicio + '\'' +
                    ", diagnostico='" + diagnostico + '\'' +
                    ", trabajosRealizados=" + trabajosRealizados +
                    ", costoTotal=" + costoTotal +
                    '}';
        }
    }

