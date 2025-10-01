package com.grupo.sistema.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o FXML da tela de login
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

        // Cria a Scene
        Scene scene = new Scene(root, 1000, 600);

        // Adiciona o CSS do login
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

        // Configura o Stage
        primaryStage.setTitle("Eduardo²Yan² - Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.getIcons().add(new javafx.scene.image.Image(
                getClass().getResourceAsStream("/icons/icone.png"))
        );
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
