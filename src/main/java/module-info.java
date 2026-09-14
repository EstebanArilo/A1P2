module org.example.tallerbicicletas {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.tallerbicicletas to javafx.fxml;
    exports org.example.tallerbicicletas;

    // Agrega estas dos líneas:
    opens org.example.tallerbicicletas.model to javafx.fxml;
    exports org.example.tallerbicicletas.model;
}