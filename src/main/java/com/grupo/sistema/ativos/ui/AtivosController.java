package com.grupo.sistema.ativos.ui;

import com.grupo.sistema.ativos.dao.AtivoDAO;
import com.grupo.sistema.ativos.model.Ativo;
import com.grupo.sistema.ativos.service.RelatorioService;
import com.grupo.sistema.model.Sessao;
import com.grupo.sistema.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AtivosController {
    @FXML private TableView<Ativo> tabelaAtivos;

    @FXML private TableColumn<Ativo, Integer> colId;
    @FXML private TableColumn<Ativo, String> colTipo;
    @FXML private TableColumn<Ativo, String> colMarca;
    @FXML private TableColumn<Ativo, String> colModelo;
    @FXML private TableColumn<Ativo, String> colNumSerie;
    @FXML private TableColumn<Ativo, String> colSetor;
    @FXML private TableColumn<Ativo, Integer> colResponsavel;
    @FXML private TableColumn<Ativo, String> colStatus;
    @FXML private TableColumn<Ativo, BigDecimal> colValor;
    @FXML private TableColumn<Ativo, String> colGarantia;
    @FXML private TableColumn<Ativo, String> colDescricao;

    @FXML private Button btnNovo;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private Label lblUsuario;

    private AtivoDAO dao = new AtivoDAO();

    @FXML
    public void initialize() {
        Usuario u = Sessao.getInstance().getUsuario();
        if (u != null) {
            lblUsuario.setText("Logado: " + u.getNome() + " (" + u.getPapel() + ")");
        } else {
            lblUsuario.setText("Usuário não identificado");
        }

        configurarColunas();

        boolean isAdmin = Sessao.getInstance().isAdmin();
        btnNovo.setVisible(isAdmin);
        btnEditar.setVisible(isAdmin);
        btnExcluir.setVisible(isAdmin);

        carregarAtivos();
    }

    private void configurarColunas() {
        colId.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getId()));
        colTipo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTipo()));
        colMarca.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMarca()));
        colModelo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getModelo()));
        colNumSerie.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNumSerie()));
        colSetor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSetor()));

        colResponsavel.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getResponsavelId()));
        colStatus.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatus()));
        colValor.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getValor()));

        colGarantia.setCellValueFactory(cell -> {
            if (cell.getValue().getGarantiaFim() != null) {
                return new SimpleStringProperty(cell.getValue().getGarantiaFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            return new SimpleStringProperty("");
        });

        colDescricao.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescricao()));
    }

    private void carregarAtivos() {
        try {
            Usuario u = Sessao.getInstance().getUsuario();
            List<Ativo> lista = Sessao.getInstance().isAdmin() ?
                    dao.listarTodos() :
                    dao.listarPorResponsavel(u.getId());
            tabelaAtivos.getItems().setAll(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void novoAtivo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ativo_form.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Novo Ativo");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            carregarAtivos(); // atualiza tabela após cadastro

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editarAtivo() {
        Ativo selecionado = tabelaAtivos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            new Alert(Alert.AlertType.WARNING, "Selecione um ativo para editar!").showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ativo_form.fxml"));
            Parent root = loader.load();

            AtivoFormController formController = loader.getController();
            formController.setAtivo(selecionado);

            Stage stage = new Stage();
            stage.setTitle("Editar Ativo");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            carregarAtivos();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void excluirAtivo() {
        Ativo selecionado = tabelaAtivos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            new Alert(Alert.AlertType.WARNING, "Selecione um ativo para excluir!").showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Tem certeza que deseja excluir o ativo: " + selecionado.getTipo() + "?",
                ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            try {
                dao.excluir(selecionado.getId());
                carregarAtivos();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Erro ao excluir ativo: " + e.getMessage()).showAndWait();
            }
        }
    }

    @FXML
    private void exportarCSVPorSetor() {
        try {
            List<Ativo> lista = tabelaAtivos.getItems();
            RelatorioService.exportarCSVPorSetor(lista, "relatorio_ativos_por_setor.csv");
            new Alert(Alert.AlertType.INFORMATION, "Relatório CSV por setor gerado com sucesso!").showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao gerar relatório CSV: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void exportarTXTPorResponsavel() {
        try {
            List<Ativo> lista = tabelaAtivos.getItems();
            RelatorioService.exportarTXTPorResponsavel(lista, "relatorio_ativos_por_responsavel.txt");
            new Alert(Alert.AlertType.INFORMATION, "Relatório TXT por responsável gerado com sucesso!").showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao gerar relatório TXT: " + e.getMessage()).showAndWait();
        }
    }



}
