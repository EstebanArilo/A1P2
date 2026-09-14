package org.example.tallerbicicletas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.tallerbicicletas.model.MenuController;
import org.example.tallerbicicletas.model.Taller;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Taller taller = new Taller();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tallerbicicletas/MenuView.fxml"));
        Parent root = loader.load();
        Taller taller = new Taller();

        MenuController menuController = loader.getController();
        menuController.setTaller(taller);

        stage.setScene(new Scene(root));
        stage.setTitle("Sistema Taller de Bicicletas");
        stage.show();
    }

    public static Taller getTaller() {
        return taller;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
