package com.grupo.sistema.login;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.animation.FadeTransition;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;

public class LoginController {

    @FXML private VBox loginPane;
    @FXML private VBox cadastroPane;

    @FXML private TextField campoEmail;
    @FXML private PasswordField campoSenha;

    @FXML private TextField campoNome;
    @FXML private TextField campoEmailCadastro;
    @FXML private PasswordField campoSenhaCadastro;

    @FXML private Label mensagemLogin;
    @FXML private Label mensagemCadastro;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO(); // 🔹 marcado como final

    // Trocar entre Login e Cadastro
    @FXML
    private void mostrarCadastro() { trocarTela(loginPane, cadastroPane); clearMensagens(); }

    @FXML
    private void mostrarLogin() { trocarTela(cadastroPane, loginPane); clearMensagens(); }

    private void trocarTela(VBox sair, VBox entrar) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), sair);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> { // 🔹 trocado de 'e' para 'event' (sem warning)
            sair.setVisible(false);
            entrar.setVisible(true);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), entrar);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    private void clearMensagens() {
        mensagemLogin.setText("");
        mensagemCadastro.setText("");
    }

    @FXML
    private void fazerLogin() {
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        if (usuarioDAO.validarLogin(email, senha)) {
            abrirMenu();
        } else {
            mensagemLogin.getStyleClass().setAll("mensagem-erro");
            mensagemLogin.setText("⚠️ Email ou senha inválidos.");
        }
    }

    @FXML
    private void fazerCadastro() {
        String nome = campoNome.getText();
        String email = campoEmailCadastro.getText();
        String senha = campoSenhaCadastro.getText();

        if (!email.contains("@")) {
            mensagemCadastro.getStyleClass().setAll("mensagem-erro");
            mensagemCadastro.setText("⚠️ Email inválido.");
            return;
        }
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            mensagemCadastro.getStyleClass().setAll("mensagem-erro");
            mensagemCadastro.setText("⚠️ Preencha todos os campos.");
            return;
        }
        if (usuarioDAO.cadastrarUsuario(nome, email, senha)) {
            mensagemCadastro.getStyleClass().setAll("mensagem-sucesso");
            mensagemCadastro.setText("✅ Usuário cadastrado! Faça login.");
            mostrarLogin();
        } else {
            mensagemCadastro.getStyleClass().setAll("mensagem-erro");
            mensagemCadastro.setText("⚠️ Email já cadastrado.");
        }
    }

    private void abrirMenu() {
        try {
            var resource = getClass().getResource("/fxml/menu.fxml");
            if (resource == null) {
                throw new IllegalStateException("menu.fxml não encontrado!");
            }
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root, 1000, 600);

            var css = getClass().getResource("/css/menu.css");
            if (css != null) {
                scene.getStylesheets().add(css.toExternalForm());
            }

            Stage stage = (Stage) campoEmail.getScene().getWindow();
            stage.setTitle("Eduardo²Yan² - Menu");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(true);

        } catch (Exception e) {
            System.err.println("Erro ao abrir menu: " + e.getMessage()); // 🔹 substitui printStackTrace
        }
    }
}
