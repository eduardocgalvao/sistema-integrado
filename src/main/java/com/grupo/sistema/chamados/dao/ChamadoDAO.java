package com.grupo.sistema.chamados.dao;

import com.grupo.sistema.chamados.model.Chamado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Date;

import com.grupo.sistema.chamados.db.ConnectionFactory;
import com.grupo.sistema.chamados.model.StatusChamado;

import javax.xml.transform.Result;

public class ChamadoDAO {
    public void abrirChamado(Chamado chamado){
        // Comando MySQL para inserir dados no banco
        String sql = "INSERT INTO chamado (equipamento, descricao, data_abertura, data_fechamento, status ) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

            try {
                ps = ConnectionFactory.getConexao().prepareStatement(sql);
                ps.setString(1, chamado.getEquipamento());
                ps.setString(2, chamado.getDescricao());
                ps.setDate(3, (Date) chamado.getData_abertura());
                ps.setDate(4, (Date)(chamado.getData_fechamento());
                ps.setString(5, chamado.getStatus());

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int idGerado = rs.seti

                ps.execute();
                ps.close();

            }  catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
