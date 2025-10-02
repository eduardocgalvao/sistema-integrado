package com.grupo.sistema.chamados.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UserOrAdminController {

    @FXML
    private Button userButton, adminButton;

    public  void switchToUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(UserOrAdminController.class.getResource("/fxml/chamadosFXML/user/chamados.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public  void switchToAdmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(UserOrAdminController.class.getResource("/fxml/chamadosFXML/admin/AdminChamadosList.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}
