package com.grupo.sistema.chamados.service;

import com.grupo.sistema.chamados.dao.ChamadoDAO;
import com.grupo.sistema.chamados.model.Chamado;

import java.sql.SQLException;
import java.util.List;

public class ChamadoService  implements ChamadoRepository{

    private ChamadoDAO chamadoDAO = new ChamadoDAO();


    @Override
    //Validações para abrir um chamado
    public void abrirChamado(Chamado chamado){
        if (chamado.getEquipamento() == null || chamado.getEquipamento().isBlank()){
            throw new IllegalArgumentException("Favor, especificar o equipamento");
        }

        if (chamado.getDescricao() == null || chamado.getDescricao().isBlank()){
            throw new IllegalArgumentException("Favor, inserir uma descrição");
        }

        chamadoDAO.abrirChamado(chamado);
    }

    @Override
    public List<Chamado> searchChamado() throws SQLException {
        return chamadoDAO.searchChamado();
    }

    @Override
    //Validações para editar um chamado
    public void editChamado(Chamado chamado){
        if (chamado.getIdChamado() <= 0){
            throw new IllegalArgumentException("Favor, inserir um ID válido para  realizar a edição");
        }

        chamadoDAO.editChamado(chamado);
    }

    @Override
    //Validações para deletar um chamado
    public void deleteChamado(Chamado chamado) throws SQLException {
        if (chamado.getIdChamado() <= 0){
            throw new IllegalArgumentException("Favor, inserir um ID válido para a exclusão");
        }

        chamadoDAO.deleteChamado(chamado);
    }
}
