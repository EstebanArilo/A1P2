package org.example.tallerbicicletas.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Taller taller;

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    @FXML
    private void onAbrirMecanicos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tallerbicicletas/MecanicoView.fxml"));
            Parent root = loader.load(); // 1. Carga el FXML

            // 2. Obtiene el controlador de Mecánico e inyecta la instancia de Taller
            MecanicoController mecanicoController = loader.getController();
            mecanicoController.setTaller(this.taller);

            // 3. Muestra la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Mecánicos");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAbrirClientes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tallerbicicletas/ClienteView.fxml"));
            Parent root = loader.load();

            // Obtiene el controlador de Cliente e inyecta la instancia de Taller
            ClienteController clienteController = loader.getController();
            clienteController.setTaller(this.taller);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Clientes");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAbrirNotificaciones(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tallerbicicletas/NotificacionView.fxml"));
            Parent root = loader.load();

            NotificacionController controller = loader.getController();
            controller.setTaller(this.taller);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSalir() {
        System.exit(0);
    }

    private void abrirVentana(String fxmlPath, String titulo, ActionEvent event, String tipoControlador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            if ("cliente".equals(tipoControlador)) {
                ClienteController controller = loader.getController();
                controller.setTaller(this.taller);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    }
