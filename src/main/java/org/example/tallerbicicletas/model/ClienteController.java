package org.example.tallerbicicletas.model;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.tallerbicicletas.model.*;

public class ClienteController {

    private Taller taller;

    @FXML private TextField txtIdCliente;
    @FXML private TextField txtNombreCliente;
    @FXML private TextField txtTelefonoCliente;
    @FXML private TextField txtDireccionCliente;

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, Integer> colIdCliente;
    @FXML private TableColumn<Cliente, String> colNombreCliente;
    @FXML private TableColumn<Cliente, Integer> colTelefonoCliente;
    @FXML private TableColumn<Cliente, String> colDireccionCliente;

    @FXML private TextField txtSerialBici;
    @FXML private TextField txtMarcaBici;
    @FXML private TextField txtColorBici;
    @FXML private TextField txtAnoBici;
    @FXML private ComboBox<TipoBicicleta> cbTipoBici;

    @FXML private ListView<String> listNotificacionesView;
    @FXML private TableView<OrdenServicio> tblHistorialServicios;

    private ObservableList<Cliente> clientesObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbTipoBici.setItems(FXCollections.observableArrayList(TipoBicicleta.values()));

        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefonoCliente.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccionCliente.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        tblClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, clienteSeleccionado) -> {
            if (clienteSeleccionado != null) {
                mostrarDetallesCliente(clienteSeleccionado);
            }
        });
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
        actualizarTablaClientes();
    }


    @FXML
    private void onRegistrarCliente() {
        System.out.println(">>> ¡El evento onRegistrarCliente se ejecutó correctamente!");

        String idStr = txtIdCliente.getText().trim();
        String telefonoStr = txtTelefonoCliente.getText().trim();
        String nombre = txtNombreCliente.getText().trim();
        String direccion = txtDireccionCliente.getText().trim();

        if (idStr.isEmpty() || telefonoStr.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor diligencie todos los campos del cliente.");
            return;
        }

        if (!idStr.matches("\\d+") || !telefonoStr.matches("\\d+")) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Identificación y teléfono deben contener únicamente números.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int telefono = Integer.parseInt(telefonoStr);

            Cliente cliente = new Cliente(nombre, id, telefono, direccion);

            if (taller != null) {
                taller.registrarCliente(cliente);
                actualizarTablaClientes();
                limpiarCamposCliente();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente registrado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error Grave", "El objeto 'taller' es null. Revisa la navegación en MenuController.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Número muy grande", "La cédula o el teléfono superan el límite numérico de entero.");
        }
    }

    @FXML
    private void onActualizarCliente() {
        try {
            int id = Integer.parseInt(txtIdCliente.getText());
            String nombre = txtNombreCliente.getText();
            int telefono = Integer.parseInt(txtTelefonoCliente.getText());
            String direccion = txtDireccionCliente.getText();

            boolean exito = taller.actualizarCliente(id, nombre, telefono, direccion);
            if (exito) {
                actualizarTablaClientes();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente actualizado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "No existe un cliente registrado con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Revisar campos numéricos.");
        }
    }

    @FXML
    private void onEliminarCliente() {
        try {
            int id = Integer.parseInt(txtIdCliente.getText());
            boolean exito = taller.eliminarCliente(id);
            if (exito) {
                actualizarTablaClientes();
                limpiarCamposCliente();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente eliminado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "Cliente no existente.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ingrese una identificación válida.");
        }
    }

    @FXML
    private void onRegistrarBicicleta() {
        Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Debe seleccionar un cliente de la tabla.");
            return;
        }

        try {
            int serial = Integer.parseInt(txtSerialBici.getText().trim());
            String marca = txtMarcaBici.getText().trim();
            String color = txtColorBici.getText().trim();
            int año = Integer.parseInt(txtAnoBici.getText().trim());
            TipoBicicleta tipoBicicleta = cbTipoBici.getValue();

            if (marca.isEmpty() || color.isEmpty() || tipoBicicleta == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor complete todos los campos de la bicicleta.");
                return;
            }

            Bicicleta bici = new Bicicleta(marca, color, serial, año, tipoBicicleta);

            boolean exito = taller.asociarBicicletaACliente(seleccionado.getId(), bici);

            if (exito) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Bicicleta registrada y asociada al cliente " + seleccionado.getNombre() + " correctamente.");
                limpiarCamposBici();

            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo asociar la bicicleta al cliente.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "El serial y el año deben ser valores enteros.");
        }
    }

    @FXML
    private void onActualizarBicicleta() {
        try {
            int serial = Integer.parseInt(txtSerialBici.getText().trim());
            String marca = txtMarcaBici.getText().trim();
            String color = txtColorBici.getText().trim();
            int anio = Integer.parseInt(txtAnoBici.getText().trim());
            TipoBicicleta tipo = cbTipoBici.getValue();

            if (marca.isEmpty() || color.isEmpty() || tipo == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Complete todos los datos de la bicicleta.");
                return;
            }

            boolean exito = taller.actualizarBicicleta(marca, color, serial, anio, tipo);

            if (exito) {
                limpiarCamposBici();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Bicicleta actualizada correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No Encontrada", "No existe una bicicleta registrada con ese número serial.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "El serial y el año deben ser valores numéricos.");
        }
    }

    @FXML
    private void onEliminarBicicleta() {
        try {
            int serial = Integer.parseInt(txtSerialBici.getText().trim());

            boolean exito = taller.eliminarBicicleta(serial);

            if (exito) {
                limpiarCamposBici();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Bicicleta eliminada correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No Encontrada", "No existe una bicicleta con ese número serial.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Ingrese un número serial válido.");
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
            stage.setTitle("Sistema Taller de Bicicletas - Menú Principal");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Navegación", "No se pudo cargar la vista del menú principal.");
        }
    }


    private void actualizarTablaClientes() {
        if (taller != null && taller.getListCliente() != null) {
            clientesObservable.setAll(taller.getListCliente());
            tblClientes.setItems(clientesObservable);
        }
    }

    private void mostrarDetallesCliente(Cliente cliente) {
        txtIdCliente.setText(String.valueOf(cliente.getId()));
        txtNombreCliente.setText(cliente.getNombre());
        txtTelefonoCliente.setText(String.valueOf(cliente.getTelefono()));
        txtDireccionCliente.setText(cliente.getDireccion());

        if (cliente.getListNotificaciones() != null) {
            listNotificacionesView.setItems(FXCollections.observableArrayList(cliente.getListNotificaciones()));
        } else {
            listNotificacionesView.getItems().clear();
        }
    }

    private void limpiarCamposCliente() {
        txtIdCliente.clear();
        txtNombreCliente.clear();
        txtTelefonoCliente.clear();
        txtDireccionCliente.clear();
    }

    private void limpiarCamposBici() {
        txtSerialBici.clear();
        txtMarcaBici.clear();
        txtColorBici.clear();
        txtAnoBici.clear();
        cbTipoBici.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
