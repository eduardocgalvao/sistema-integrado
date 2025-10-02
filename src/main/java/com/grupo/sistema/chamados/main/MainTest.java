package com.grupo.sistema.chamados.main;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.model.Chamado;

import java.sql.SQLException;

public class MainTest {
    static void main(String[] args) {
        ChamadoDAO chDAO = new ChamadoDAO();
        Chamado ch = new Chamado();
        ch.setIdChamado(9);
        try {
            chDAO.deleteChamado(ch);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
