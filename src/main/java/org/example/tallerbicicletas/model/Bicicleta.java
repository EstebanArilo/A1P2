package org.example.tallerbicicletas.model;


    public class Bicicleta {
        private String marca;
        private String color;
        private int numeroSerial;
        private int año;
        private TipoBicicleta tipoBicicleta;

        public Bicicleta(String marca, String color, int numeroSerial, int año, TipoBicicleta tipoBicicleta) {
            this.marca = marca;
            this.color = color;
            this.numeroSerial = numeroSerial;
            this.año = año;
            this.tipoBicicleta = tipoBicicleta;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getNumeroSerial() {
            return numeroSerial;
        }

        public void setNumeroSerial(int numeroSerial) {
            this.numeroSerial = numeroSerial;
        }

        public int getAño() {
            return año;
        }

        public void setAño(int año) {
            this.año = año;
        }

        public TipoBicicleta getTipoBicicleta() {
            return tipoBicicleta;
        }

        public void setTipoBicicleta(TipoBicicleta tipoBicicleta) {
            this.tipoBicicleta = tipoBicicleta;
        }

        @Override
        public String toString() {
            return "Bicicleta{" +
                    "marca='" + marca + '\'' +
                    ", color='" + color + '\'' +
                    ", numeroSerial=" + numeroSerial +
                    ", año=" + año +
                    ", tipoBicicleta=" + tipoBicicleta +
                    '}';
        }
    }

