package com.grupo.sistema.chamados.ui.UserUI;

import com.grupo.sistema.chamados.dao.UserDAO;
import com.grupo.sistema.chamados.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class UserSingingController {
    @FXML
    private TextField inputName, inputEmail, inputSetor;
    @FXML
    private Button enterButton;
    @FXML
    private ImageView logoImage;

    public void cadastro(ActionEvent event) throws IOException {
        User user = new User();
        user.setNome(inputName.getText());
        user.setEmail(inputEmail.getText());
        user.setSetor(inputSetor.getText());

        UserDAO userDAO = new UserDAO();
        userDAO.cadastrar(user);


        System.out.println("CADASTRADO COM SUCESSO");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chamadosFXML/user/chamados.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }



}
