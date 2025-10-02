package com.grupo.sistema.chamados.main;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.dao.StatusDAO;
import com.grupo.sistema.chamados.model.Chamado;
import com.grupo.sistema.chamados.model.StatusChamado;

import java.sql.SQLException;

public class MainTest {
    static void main(String[] args) {
        ChamadoDAO chDAO = new ChamadoDAO();
        Chamado ch = new Chamado();
        StatusChamado status = new StatusChamado();
        status.setIdStatus(4);
        ch.setIdChamado(12);
        ch.setStatus(status);

        try {
            chDAO.updateStatus(ch);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
