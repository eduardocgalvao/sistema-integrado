package com.grupo.sistema.chamados.ui.AdminUI;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.model.Chamado;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
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
}
