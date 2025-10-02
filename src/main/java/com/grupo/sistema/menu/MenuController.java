package com.grupo.sistema.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.animation.FadeTransition;
import javafx.stage.Stage;
import javafx.util.Duration;



public class MenuController {


    @FXML
    private StackPane conteudoArea;

    @FXML
    private void abrirAtivos() throws Exception {
        carregarConteudo("/fxml/ativos.fxml");
    }

    @FXML
    private void abrirChamados() throws Exception {
        carregarConteudo("/fxml/chamados.fxml");
    }

    @FXML
    private void logout() {
        try {
            // Limpa a sessão
            com.grupo.sistema.model.Sessao.getInstance().logout();

            // Carrega o FXML do login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            // Recupera o Stage atual
            Stage stage = (Stage) conteudoArea.getScene().getWindow();

            // Cria nova Scene com CSS aplicado
            Scene scene = new Scene(root, 1000, 600);
            scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

            stage.setTitle("Eduardo²Yan² - Login");
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void carregarConteudo(String caminho) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent tela = loader.load();

        conteudoArea.getChildren().setAll(tela);


        // Animação fade-in
        FadeTransition ft = new FadeTransition(Duration.millis(500), tela);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
