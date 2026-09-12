module org.example.tallerbicicletas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tallerbicicletas to javafx.fxml;
    exports org.example.tallerbicicletas;
}