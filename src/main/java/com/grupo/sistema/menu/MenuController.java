package com.grupo.sistema.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.animation.FadeTransition;
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
