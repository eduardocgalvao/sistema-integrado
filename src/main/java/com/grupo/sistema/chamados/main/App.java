package com.grupo.sistema.chamados.main;
import com.grupo.sistema.chamados.dao.UserDAO;
import com.grupo.sistema.chamados.model.User;

public class App {
    public static void main(String[] args) {
        User u1 = new User();

        u1.cadastro();

        new UserDAO().cadastrar(u1);
    }
}
