package org.example.tallerbicicletas.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.tallerbicicletas.model.Cliente;
import org.example.tallerbicicletas.model.Taller;

import java.io.IOException;

public class NotificacionController {

    private Taller taller;

    // --- COMPONENTES DE INTERFAZ ---
    @FXML private ComboBox<Cliente> cbClientesNotificables;
    @FXML private TextArea txtMensajeNotificacion;
    @FXML private ListView<String> listHistorialGeneral;

    // --- TABLA DE ESTADOS POR CLIENTE ---
    @FXML private TableView<Cliente> tblClientesEstado;
    @FXML private TableColumn<Cliente, Integer> colIdCliente;
    @FXML private TableColumn<Cliente, String> colNombreCliente;
    @FXML private TableColumn<Cliente, String> colUltimoEstado;

    private ObservableList<String> historialObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colUltimoEstado.setCellValueFactory(cellData -> {
            Cliente c = cellData.getValue();
            String estado = (c != null) ? c.recibirEstadoBicicleta() : "";
            return new javafx.beans.property.SimpleStringProperty(estado != null ? estado : "Sin estado");
        });

        listHistorialGeneral.setItems(historialObservable);
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
        cargarClientes();
        actualizarTablaEstados();
        cargarHistorialGeneral();
    }

    private void cargarHistorialGeneral() {
        if (taller != null) {
            historialObservable.setAll(taller.getHistorialNotificacionesGeneral());
        }
    }

    @FXML
    private void onEnviarNotificacionCliente() {
        Cliente clienteSeleccionado = cbClientesNotificables.getValue();
        String mensaje = txtMensajeNotificacion.getText();

        if (clienteSeleccionado == null || mensaje == null || mensaje.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Seleccione un cliente y escriba un mensaje.");
            return;
        }

        clienteSeleccionado.notificarEstadoBicicleta(mensaje);

        String registro = "Enviado a [" + clienteSeleccionado.getNombre() + "]: " + mensaje;
        taller.registrarNotificacionEnHistorial(registro);

        cargarHistorialGeneral();
        actualizarTablaEstados();
        txtMensajeNotificacion.clear();

        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Notificación enviada.");
    }

    @FXML
    private void onNotificarATodos() {
        String mensaje = txtMensajeNotificacion.getText();

        if (mensaje == null || mensaje.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Escriba un mensaje para notificar a todos.");
            return;
        }

        if (taller != null && taller.getListCliente() != null) {
            taller.notificarTodos(mensaje);

            historialObservable.add(0, "NOTIFICACIÓN GENERAL: " + mensaje);
            actualizarTablaEstados();
            txtMensajeNotificacion.clear();

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Notificación enviada a todos los clientes registrados.");
        }
    }

    @FXML
    private void onVolverMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tallerbicicletas/MenuView.fxml"));
            Parent root = loader.load();

            MenuController menuController = loader.getController();
            menuController.setTaller(this.taller);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Sistema de Gestión de Taller");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo regresar al menú principal.");
        }
    }

    // --- AUXILIARES ---

    private void cargarClientes() {
        if (taller != null && taller.getListCliente() != null) {
            cbClientesNotificables.setItems(FXCollections.observableArrayList(taller.getListCliente()));
        }
    }

    private void actualizarTablaEstados() {
        if (taller != null && taller.getListCliente() != null) {
            tblClientesEstado.setItems(FXCollections.observableArrayList(taller.getListCliente()));
            tblClientesEstado.refresh();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}