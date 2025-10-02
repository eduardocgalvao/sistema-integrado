package com.grupo.sistema.chamados.ui.AdminUI;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.model.Chamado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
    private Button deleteButton;
    @FXML
    private ComboBox<String> comboBoxStatus;

    @FXML
    public void initialize(){
        comboBoxStatus.getItems().addAll("Aberto", "Em andamento", "Fechado");
    }



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
}
