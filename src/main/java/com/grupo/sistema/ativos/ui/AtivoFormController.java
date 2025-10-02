package com.grupo.sistema.ativos.ui;

import com.grupo.sistema.ativos.dao.AtivoDAO;
import com.grupo.sistema.ativos.model.Ativo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AtivoFormController {

    @FXML private TextField txtTipo;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtNumSerie;
    @FXML private TextField txtSetor;
    @FXML private TextField txtResponsavel;
    @FXML private ComboBox<String> cbStatus;
    @FXML private TextField txtValor;
    @FXML private DatePicker dpGarantia;
    @FXML private TextArea txtDescricao;

    private final AtivoDAO dao = new AtivoDAO();

    @FXML
    public void initialize() {
        cbStatus.getItems().addAll("Em uso", "Em manutenção", "Baixado");
        cbStatus.getSelectionModel().selectFirst();
    }

    @FXML
    private void salvar() {
        try {
            Ativo a = new Ativo();
            a.setTipo(txtTipo.getText());
            a.setMarca(txtMarca.getText());
            a.setModelo(txtModelo.getText());
            a.setNumSerie(txtNumSerie.getText());
            a.setSetor(txtSetor.getText());
            if (!txtResponsavel.getText().isBlank()) {
                a.setResponsavelId(Integer.parseInt(txtResponsavel.getText()));
            }
            a.setStatus(cbStatus.getValue());
            if (!txtValor.getText().isBlank()) {
                a.setValor(new BigDecimal(txtValor.getText()));
            }
            LocalDate garantia = dpGarantia.getValue();
            if (garantia != null) a.setGarantiaFim(garantia);
            a.setDescricao(txtDescricao.getText());

            dao.salvar(a);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ativo salvo com sucesso!");
            alert.showAndWait();

            fecharJanela();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao salvar ativo: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private Ativo ativoEdicao; // se != null então esta editando

    public void setAtivo(Ativo ativo) {
        this.ativoEdicao = ativo;

        if (ativo != null) {
            txtTipo.setText(ativo.getTipo());
            txtMarca.setText(ativo.getMarca());
            txtModelo.setText(ativo.getModelo());
            txtNumSerie.setText(ativo.getNumSerie());
            txtSetor.setText(ativo.getSetor());
            if (ativo.getResponsavelId() != null)
                txtResponsavel.setText(String.valueOf(ativo.getResponsavelId()));
            cbStatus.setValue(ativo.getStatus());
            if (ativo.getValor() != null)
                txtValor.setText(ativo.getValor().toString());
            if (ativo.getGarantiaFim() != null)
                dpGarantia.setValue(ativo.getGarantiaFim());
            txtDescricao.setText(ativo.getDescricao());
        }
    }



    @FXML
    private void cancelar() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) txtTipo.getScene().getWindow();
        stage.close();
    }
}
