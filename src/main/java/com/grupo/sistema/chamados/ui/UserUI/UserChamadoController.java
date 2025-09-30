package com.grupo.sistema.chamados.ui.UserUI;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.dao.UserDAO;
import com.grupo.sistema.chamados.model.Chamado;
import com.grupo.sistema.chamados.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class UserChamadoController  {
    @FXML
    private TextField inputEquipamento;
    @FXML
    private TextArea inputDescricao;
    @FXML
    private Button okButton;


    public void cadastrarChamado(ActionEvent event){
        try{
            Chamado ch = new Chamado();
            User user = new User();
            user.setId(25);

            ch.setEquipamento(inputEquipamento.getText());
            ch.setDescricao(inputDescricao.getText());
            long tempoAtual = System.currentTimeMillis();
            ch.setData_abertura(new java.sql.Date(tempoAtual));
            ch.setUser(user);



            ChamadoDAO chamadoDAO = new ChamadoDAO();
            chamadoDAO.abrirChamado(ch);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switchWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chamadosFXML/user/chamados.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}
