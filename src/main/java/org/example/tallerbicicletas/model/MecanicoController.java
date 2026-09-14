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

import java.time.LocalDate;
import java.time.LocalTime;

public class MecanicoController {

    private Taller taller;

    @FXML private TextField txtIdMecanico;
    @FXML private TextField txtNombreMecanico;
    @FXML private TextField txtTelefonoMecanico;
    @FXML private TextField txtCertificacionMecanico;
    @FXML private ComboBox<TipoEspecializacion> cbEspecializacion;

    @FXML private TableView<Mecanico> tblMecanicos;
    @FXML private TableColumn<Mecanico, Integer> colIdMecanico;
    @FXML private TableColumn<Mecanico, String> colNombreMecanico;
    @FXML private TableColumn<Mecanico, Integer> colTelefonoMecanico;
    @FXML private TableColumn<Mecanico, TipoEspecializacion> colEspecializacion;
    @FXML private TableColumn<Mecanico, Integer> colCertificacion;

    @FXML private TextField txtIdOrden;
    @FXML private DatePicker dpFechaIngreso;
    @FXML private TextField txtHoraIngreso;
    @FXML private ComboBox<Bicicleta> cbBicicletaOrden;
    @FXML private TextArea txtMotivoServicio;
    @FXML private TextArea txtDiagnostico;
    @FXML private TextArea txtTrabajosRealizados;
    @FXML private TextField txtCostoTotal;

    @FXML private TableView<OrdenServicio> tblOrdenesMecanico;
    @FXML private TableColumn<OrdenServicio, Integer> colIdOrden;
    @FXML private TableColumn<OrdenServicio, LocalDate> colFechaOrden;
    @FXML private TableColumn<OrdenServicio, String> colMotivoOrden;
    @FXML private TableColumn<OrdenServicio, Double> colCostoOrden;

    private ObservableList<Mecanico> mecanicosObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbEspecializacion.setItems(FXCollections.observableArrayList(TipoEspecializacion.values()));

        colIdMecanico.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreMecanico.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefonoMecanico.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEspecializacion.setCellValueFactory(new PropertyValueFactory<>("tipoEspecializacion"));
        colCertificacion.setCellValueFactory(new PropertyValueFactory<>("numeroCertificacion"));
        colIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        colFechaOrden.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        colMotivoOrden.setCellValueFactory(new PropertyValueFactory<>("motivoServicio"));
        colCostoOrden.setCellValueFactory(new PropertyValueFactory<>("costoTotal"));

        tblMecanicos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, mecanicoSeleccionado) -> {
            if (mecanicoSeleccionado != null) {
                mostrarDetallesMecanico(mecanicoSeleccionado);
                cargarOrdenesDelMecanico(mecanicoSeleccionado.getId());
            }
        });
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
        actualizarTablaMecanicos();
        cargarComboBicicletas();
    }


    @FXML
    private void onRegistrarMecanico() {
        System.out.println(">>> ¡El botón Registrar Mecánico fue presionado!");

        String idStr = txtIdMecanico.getText().trim();
        String nombre = txtNombreMecanico.getText().trim();
        String telefonoStr = txtTelefonoMecanico.getText().trim();
        String certStr = txtCertificacionMecanico.getText().trim();
        TipoEspecializacion especialidad = cbEspecializacion.getValue();

        if (idStr.isEmpty() || nombre.isEmpty() || telefonoStr.isEmpty() || certStr.isEmpty() || especialidad == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor llene todos los campos y seleccione la especialidad.");
            return;
        }

        if (!idStr.matches("\\d+") || !telefonoStr.matches("\\d+") || !certStr.matches("\\d+")) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Cédula, teléfono y certificación deben ser valores numéricos.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int telefono = Integer.parseInt(telefonoStr);
            int certificacion = Integer.parseInt(certStr);

            Mecanico nuevoMecanico = new Mecanico(nombre, id,telefono, especialidad, certificacion);

            if (taller != null) {
                taller.registrarMecanico(nuevoMecanico);
                actualizarTablaMecanicos();
                limpiarCamposMecanico();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Mecánico registrado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error Grave", "El objeto 'taller' es null. Revisa la navegación desde MenuController.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Rango", "Los valores numéricos superan el límite permitido.");
        }
    }

    @FXML
    private void onActualizarMecanico() {
        try {
            int id = Integer.parseInt(txtIdMecanico.getText());
            String nombre = txtNombreMecanico.getText();
            int telefono = Integer.parseInt(txtTelefonoMecanico.getText());
            int certificacion = Integer.parseInt(txtCertificacionMecanico.getText());
            TipoEspecializacion esp = cbEspecializacion.getValue();

            boolean exito = taller.actualizarMecanico(id, nombre, telefono, esp, certificacion);
            if (exito) {
                actualizarTablaMecanicos();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Mecánico actualizado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "No se encontró el mecánico con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Verifique los datos numéricos.");
        }
    }

    @FXML
    private void onEliminarMecanico() {
        try {
            int id = Integer.parseInt(txtIdMecanico.getText());
            boolean exito = taller.eliminarMecanico(id);
            if (exito) {
                actualizarTablaMecanicos();
                limpiarCamposMecanico();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Mecánico eliminado.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "Mecánico no existe.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ingrese una identificación válida.");
        }
    }

    @FXML
    private void onCrearOrdenServicio() {
        Mecanico mecanicoSeleccionado = tblMecanicos.getSelectionModel().getSelectedItem();
        if (mecanicoSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Debe seleccionar un mecánico de la tabla para asignarle la orden.");
            return;
        }

        Bicicleta bicicleta = cbBicicletaOrden.getValue();
        LocalDate fecha = dpFechaIngreso.getValue();
        String idOrdenStr = txtIdOrden.getText().trim();
        String horaStr = txtHoraIngreso.getText().trim();
        String motivo = txtMotivoServicio.getText().trim();
        String costoStr = txtCostoTotal.getText().trim();

        if (bicicleta == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Bicicleta Requerida", "Debe seleccionar una bicicleta para la orden de servicio.");
            return;
        }

        if (idOrdenStr.isEmpty() || fecha == null || horaStr.isEmpty() || motivo.isEmpty() || costoStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Complete el N° de orden, fecha, hora, motivo y costo.");
            return;
        }

        try {
            int idOrden = Integer.parseInt(idOrdenStr);
            LocalTime hora = LocalTime.parse(horaStr);
            double costo = Double.parseDouble(costoStr);
            String diagnostico = txtDiagnostico.getText().trim();
            String trabajos = txtTrabajosRealizados.getText().trim();

            OrdenServicio orden = new OrdenServicio(fecha, hora, bicicleta, mecanicoSeleccionado, motivo, diagnostico, trabajos, costo, idOrden);

            taller.crearOrdenServicio(orden);

            cargarOrdenesDelMecanico(mecanicoSeleccionado.getId());
            limpiarCamposOrden();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Orden de servicio registrada y asignada correctamente.");

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Verifique el formato de la hora (HH:mm) y que el N° de orden y costo sean numéricos.");
        }
    }


    @FXML
    private void onActualizarOrdenServicio() {
        try {
            int idOrden = Integer.parseInt(txtIdOrden.getText().trim());
            LocalDate fecha = dpFechaIngreso.getValue();
            LocalTime hora = LocalTime.parse(txtHoraIngreso.getText().trim());
            Bicicleta bicicleta = cbBicicletaOrden.getValue();
            Mecanico mecanico = tblMecanicos.getSelectionModel().getSelectedItem();
            String motivo = txtMotivoServicio.getText().trim();
            String diagnostico = txtDiagnostico.getText().trim();
            String trabajos = txtTrabajosRealizados.getText().trim();
            double costo = Double.parseDouble(txtCostoTotal.getText().trim());

            boolean exito = taller.actualizarOrdenServicio(fecha, hora, bicicleta, mecanico, motivo, diagnostico, trabajos, costo, idOrden);

            if (exito) {
                if (mecanico != null) {
                    cargarOrdenesDelMecanico(mecanico.getId());
                }
                limpiarCamposOrden();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Orden de servicio actualizada correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "No Encontrada", "No se encontró una orden con ese ID.");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Verifique el formato de los campos (hora HH:mm, valores numéricos).");
        }
    }

    @FXML
    private void onEliminarOrdenServicio() {
        OrdenServicio seleccionada = tblOrdenesMecanico.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Seleccione una orden de la tabla para eliminar.");
            return;
        }

        boolean exito = taller.eliminarOrdenServicio(seleccionada.getIdOrden());

        if (exito) {
            Mecanico mecanico = tblMecanicos.getSelectionModel().getSelectedItem();
            if (mecanico != null) {
                cargarOrdenesDelMecanico(mecanico.getId());
            }
            limpiarCamposOrden();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Orden de servicio eliminada correctamente.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar la orden.");
        }
    }

    @FXML
    private void onReasignarMecanicoAOrden() {
        OrdenServicio ordenSeleccionada = tblOrdenesMecanico.getSelectionModel().getSelectedItem();
        Mecanico nuevoMecanico = tblMecanicos.getSelectionModel().getSelectedItem();

        if (ordenSeleccionada == null || nuevoMecanico == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Incompleta", "Seleccione un mecánico y una orden para realizar la asignación.");
            return;
        }

        boolean exito = taller.asignarMecanicoAOrden(ordenSeleccionada.getIdOrden(), nuevoMecanico.getId());

        if (exito) {
            cargarOrdenesDelMecanico(nuevoMecanico.getId());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Orden reasignada al mecánico " + nuevoMecanico.getNombre() + ".");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo reasignar la orden.");
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
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Navegación", "No se pudo volver al menú principal.");
        }
    }

    private void actualizarTablaMecanicos() {
        if (taller != null && taller.getListMecanico() != null) {
            mecanicosObservable.setAll(taller.getListMecanico());
            tblMecanicos.setItems(mecanicosObservable);
        }
    }

    private void cargarComboBicicletas() {
        if (taller != null && taller.getListBicicleta() != null) {
            cbBicicletaOrden.setItems(FXCollections.observableArrayList(taller.getListBicicleta()));
        }
    }

    private void cargarOrdenesDelMecanico(int idMecanico) {
        if (taller != null) {
            tblOrdenesMecanico.setItems(FXCollections.observableArrayList(taller.buscarOrdenesDelMecanico(idMecanico)));
        }
    }

    private void mostrarDetallesMecanico(Mecanico mecanico) {
        txtIdMecanico.setText(String.valueOf(mecanico.getId()));
        txtNombreMecanico.setText(mecanico.getNombre());
        txtTelefonoMecanico.setText(String.valueOf(mecanico.getTelefono()));
        txtCertificacionMecanico.setText(String.valueOf(mecanico.getNumeroCertificacion()));
        cbEspecializacion.setValue(mecanico.getTipoEspecializacion());
    }

    private void limpiarCamposMecanico() {
        txtIdMecanico.clear();
        txtNombreMecanico.clear();
        txtTelefonoMecanico.clear();
        txtCertificacionMecanico.clear();
        cbEspecializacion.getSelectionModel().clearSelection();
    }

    private void limpiarCamposOrden() {
        txtIdOrden.clear();
        dpFechaIngreso.setValue(null);
        txtHoraIngreso.clear();
        cbBicicletaOrden.getSelectionModel().clearSelection();
        txtMotivoServicio.clear();
        txtDiagnostico.clear();
        txtTrabajosRealizados.clear();
        txtCostoTotal.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
