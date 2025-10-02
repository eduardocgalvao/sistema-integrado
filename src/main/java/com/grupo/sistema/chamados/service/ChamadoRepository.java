package com.grupo.sistema.chamados.service;

import com.grupo.sistema.chamados.model.Chamado;

import java.sql.SQLException;
import java.util.List;

public interface ChamadoRepository {
    public abstract void abrirChamado(Chamado chamado);
    List<Chamado> searchChamado() throws SQLException;
    public abstract void editChamado(Chamado chamado);
//    public abstract void deleteChamado(int idChamado) throws SQLException;

}
