package com.grupo.sistema.login;

import com.grupo.sistema.model.Usuario;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

    @FXML private VBox loginPane;
    @FXML private VBox cadastroPane;

    @FXML private TextField campoEmail;
    @FXML private PasswordField campoSenha;

    @FXML private TextField campoNome;
    @FXML private TextField campoEmailCadastro;
    @FXML private PasswordField campoSenhaCadastro;
    @FXML private TextField campoTelefoneCadastro;
    @FXML private TextField campoSetorCadastro;

    @FXML private Label mensagemLogin;
    @FXML private Label mensagemCadastro;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Alterna entre telas
    @FXML private void mostrarCadastro() { trocarTela(loginPane, cadastroPane); clearMensagens(); }
    @FXML private void mostrarLogin() { trocarTela(cadastroPane, loginPane); clearMensagens(); }

    private void trocarTela(VBox sair, VBox entrar) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), sair);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
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

    // Login
    @FXML
    private void fazerLogin() {
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText().trim();

        if (email.isEmpty() || senha.isEmpty()) {
            mostrarMensagem(mensagemLogin, "⚠️ Preencha todos os campos.", "mensagem-erro");
            return;
        }

        Usuario usuarioLogado = usuarioDAO.validarLogin(email, senha);
        if (usuarioLogado != null) {
            // >>> AQUI É A LINHA QUE FALTA <<<
            com.grupo.sistema.model.Sessao.getInstance().setUsuario(usuarioLogado);

            abrirMenu();
        } else {
            mostrarMensagem(mensagemLogin, "⚠️ Email ou senha inválidos.", "mensagem-erro");
        }
    }


    // Cadastro
    @FXML
    private void fazerCadastro() {
        String nome = campoNome.getText().trim();
        String email = campoEmailCadastro.getText().trim();
        String senha = campoSenhaCadastro.getText().trim();
        String telefone = campoTelefoneCadastro.getText().trim();
        String setor = campoSetorCadastro.getText().trim();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || telefone.isEmpty() || setor.isEmpty()) {
            mostrarMensagem(mensagemCadastro, "⚠️ Preencha todos os campos.", "mensagem-erro");
            return;
        }

        if (!email.contains("@")) {
            mostrarMensagem(mensagemCadastro, "⚠️ Email inválido.", "mensagem-erro");
            return;
        }

        boolean cadastrado = usuarioDAO.cadastrarUsuario(nome, email, senha, telefone, setor);
        if (cadastrado) {
            mostrarMensagem(mensagemCadastro, "✅ Usuário cadastrado! Faça login.", "mensagem-sucesso");
            mostrarLogin();
        } else {
            mostrarMensagem(mensagemCadastro, "⚠️ Email já cadastrado.", "mensagem-erro");
        }
    }


    // Mensagens centralizadas
    private void mostrarMensagem(Label label, String texto, String estilo) {
        label.getStyleClass().setAll(estilo);
        label.setText(texto);
    }

    // Abrir menu
    private void abrirMenu() {
        try {
            var resource = getClass().getResource("/fxml/menu.fxml");
            if (resource == null) throw new IllegalStateException("menu.fxml não encontrado!");

            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root, 1000, 600);

            var css = getClass().getResource("/css/menu.css");
            if (css != null) scene.getStylesheets().add(css.toExternalForm());

            Stage stage = (Stage) campoEmail.getScene().getWindow();
            stage.setTitle("Eduardo²Yan² - Menu");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(true);

        } catch (Exception e) {
            System.err.println("Erro ao abrir menu: " + e.getMessage());
        }
    }
}
