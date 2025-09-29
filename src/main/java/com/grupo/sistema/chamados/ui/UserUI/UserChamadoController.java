package com.grupo.sistema.chamados.ui.UserUI;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.model.Chamado;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;




public class UserChamadoController {
    @FXML
    private TextField inputEquipamento;
    @FXML
    private TextArea inputDescricao;
    @FXML
    private Button okButton;


    public void cadastrarChamado(ActionEvent event){
        try{
            Chamado ch = new Chamado();

            ch.setEquipamento(inputEquipamento.getText());
            ch.setDescricao(inputDescricao.getText());
            long tempoAtual = System.currentTimeMillis();
            ch.setData_abertura(new java.sql.Date(tempoAtual));



            ChamadoDAO chamadoDAO = new ChamadoDAO();
            chamadoDAO.abrirChamado(ch);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
