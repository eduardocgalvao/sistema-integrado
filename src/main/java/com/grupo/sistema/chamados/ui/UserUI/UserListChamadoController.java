package com.grupo.sistema.chamados.ui.UserUI;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.model.Chamado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserListChamadoController implements Initializable {
    @FXML
    private TableView<Chamado> tableListChamado;
    @FXML
    private TableColumn<Chamado, String> equipamentoList;
    @FXML
    private TableColumn<Chamado, String> descricaoList;
    @FXML
    private TableColumn<Chamado, java.sql.Date> dateList;
    @FXML
    private TableColumn<Chamado, String> statusList;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            equipamentoList.setCellValueFactory(new PropertyValueFactory<>("equipamento"));
            descricaoList.setCellValueFactory(new PropertyValueFactory<>("descricao"));


            List<Chamado> chamados = new ChamadoDAO().searchChamado();
            tableListChamado.getItems().setAll(chamados);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switchWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chamadosFXML/user/newChamado.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
