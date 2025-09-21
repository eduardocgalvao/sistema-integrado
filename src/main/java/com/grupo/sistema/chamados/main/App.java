package com.grupo.sistema.chamados.main;
import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.dao.UserDAO;
import com.grupo.sistema.chamados.model.Chamado;
import com.grupo.sistema.chamados.model.User;

public class App {
    public static void main(String[] args) {
        new UserDAO().SearchUser();

    }
}
