package com.grupo.sistema.chamados.ui.AdminUI;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.model.Chamado;
import com.grupo.sistema.chamados.model.StatusChamado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminChamadosListController implements Initializable {
    @FXML
    private TableView<Chamado> tableListChamado;
    @FXML
    private TableColumn<Chamado, String> equipList;
    @FXML
    private TableColumn<Chamado, String> descList;
    @FXML
    private TableColumn<Chamado, java.sql.Date> dateList;
    @FXML
    private TableColumn<Chamado, String> statusList;
    @FXML
    private TableColumn<Chamado, String> userList;
    @FXML
    private TableColumn<Chamado, Integer> idChList;
    @FXML
    private Button deleteButton, updateButton;
    @FXML
    private ComboBox<String> comboBoxStatus;







    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {

            idChList.setCellValueFactory(new PropertyValueFactory<>("idChamado"));
            userList.setCellValueFactory(new PropertyValueFactory<>("usuarioNome"));
            equipList.setCellValueFactory(new PropertyValueFactory<>("equipamento"));
            descList.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            dateList.setCellValueFactory(new PropertyValueFactory<>("data_abertura"));
            statusList.setCellValueFactory(new PropertyValueFactory<>("statusNome"));




            List<Chamado> chamados = new ChamadoDAO().searchChamado();
            tableListChamado.getItems().setAll(chamados);



        } catch (Exception e){
            e.printStackTrace();
        }

        comboBoxStatus.getItems().addAll("Aberto", "Em andamento", "Fechado");
        try {
            refreshChamados();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    private void updateStatus( ActionEvent event) throws SQLException {
        Chamado chSelected = tableListChamado.getSelectionModel().getSelectedItem();
        ChamadoDAO chDAO = new ChamadoDAO();


        try {
            String novoStatusString = comboBoxStatus.getValue();
            StatusChamado novoStatus = new StatusChamado();
            if (chSelected != null && comboBoxStatus.getValue() != null) {

                if (Objects.equals(novoStatusString, "Aberto")) {
                    novoStatus.setIdStatus(1);

                } else {
                    if (Objects.equals(novoStatusString, "Em andamento")) {
                        novoStatus.setIdStatus(3);

                    } else {
                        novoStatus.setIdStatus(4);

                    }

                }
            }
            chSelected.setStatus(novoStatus);

            novoStatus.setStatus(novoStatusString);

            chDAO.updateStatus(chSelected);

            tableListChamado.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refresh(ActionEvent event) throws SQLException {
        refreshChamados();
    }

    public void refreshChamados() throws SQLException {
        try {
            ChamadoDAO chDAO = new ChamadoDAO();
            List<Chamado> chamadosList = chDAO.searchChamado();
            tableListChamado.setItems(FXCollections.observableArrayList(chamadosList));
            System.out.println("Tabela atualizada");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }


        @FXML
    private void deleteChamado(ActionEvent event) throws SQLException {
        Chamado chSelected = tableListChamado.getSelectionModel().getSelectedItem();

        if (chSelected != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação de exclusão");
            alerta.setHeaderText("Excluir chamado");
            alerta.setContentText("Tem certeza que quer excluir esse chamado?");

            if (alerta.showAndWait().get() == ButtonType.OK){
               try {
                   ChamadoDAO chDAO = new ChamadoDAO();
                   chDAO.deleteChamado(chSelected);

                   tableListChamado.getItems().remove(chSelected);
               } catch (SQLException e){
                   System.err.println("Falha ao excluir o chamado do banco de dados.");
                   e.printStackTrace();

                   Alert erro = new Alert(Alert.AlertType.ERROR);
                   erro.setTitle("ERRO DE BANCO DE DADOS");
                   erro.setHeaderText("Não foi possível excluir o chamado");
                   erro.setContentText("Detalhes do erro: " + e.getMessage());
                   erro.showAndWait();
               }
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("AVISO");
            alerta.setHeaderText("Nenhum chamado selecionado");
            alerta.setContentText("Selecione um chamado para excluir.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void exportarCSV(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.setTitle("Salvar relatório CSV");
        fc.setInitialFileName("relatorio_chamados.csv");
        FileChooser.ExtensionFilter extF = new FileChooser.ExtensionFilter("Arquivos CSV (*.csv)", ".csv");
        fc.getExtensionFilters().add(extF);

        File file = fc.showSaveDialog(null);

        if (file != null){
            try {
                ChamadoDAO chDAO = new ChamadoDAO();
                List<Chamado> chamados = chDAO.searchChamado();

                writeinCSV(chamados, file);

                System.out.println("Relatório CSV exportado com sucesso para: " + file.getAbsolutePath());

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Exportação cancelada");
        }


    }

    private void writeinCSV(List<Chamado> chamados, File file) throws IOException{

        try (FileWriter writer = new FileWriter(file)){

            writer.append("ID Chamado, Equipamento, Descrição, Data, Status\n");


            for (Chamado ch : chamados){
                writer.append(String.valueOf(ch.getIdChamado()));
                writer.append(",");
                writer.append(scapeEspecialC(ch.getEquipamento()));
                writer.append(",");
                writer.append(scapeEspecialC(ch.getDescricao()));
                writer.append(",");
                writer.append(ch.getData_abertura().toString());
                writer.append(",");
                writer.append(scapeEspecialC(ch.getStatusNome()));
                writer.append("\n");

            }

            writer.flush();
        }
    }

    private String scapeEspecialC(String data){
        if (data == null){
            return "";
        }

        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")){
            escapedData = data.replace("\"", "\"\"");
            return "\"" + escapedData + "\"";
        }

        return escapedData;
    }
}
