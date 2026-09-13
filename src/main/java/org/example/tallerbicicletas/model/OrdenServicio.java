package org.example.tallerbicicletas.model;


    import java.time.LocalDate;
    import java.time.LocalTime;

public class OrdenServicio {
        private LocalDate fechaIngreso;
        private LocalTime hora;
        private Bicicleta bicicleta;
        private Mecanico mecanico;
        private String motivoServicio;
        private String diagnostico;
        private String trabajosRealizados;
        private double costoTotal;
        private int idOrden;

        public OrdenServicio(LocalDate fechaIngreso, LocalTime hora, Bicicleta bicicleta, Mecanico mecanico, String motivoServicio, String diagnostico, String trabajosRealizados, double costoTotal, int idOrden) {
            this.fechaIngreso = fechaIngreso;
            this.hora = hora;
            this.bicicleta = bicicleta;
            this.mecanico = mecanico;
            this.motivoServicio = motivoServicio;
            this.diagnostico = diagnostico;
            this.trabajosRealizados = trabajosRealizados;
            this.costoTotal = costoTotal;
            this.idOrden=idOrden;
        }

        public LocalDate getFechaIngreso() {
            return fechaIngreso;
        }

        public void setFechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

        public LocalTime getHora() {
            return hora;
        }

        public void setHora(LocalTime hora) {
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

        public String getTrabajosRealizados() {
            return trabajosRealizados;
        }

        public void setTrabajosRealizados(String trabajosRealizados) {
            this.trabajosRealizados = trabajosRealizados;
        }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
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

