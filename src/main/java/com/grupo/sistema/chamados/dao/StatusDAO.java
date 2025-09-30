package com.grupo.sistema.chamados.dao;

import com.grupo.sistema.chamados.db.ConnectionFactory;
import com.grupo.sistema.chamados.model.StatusChamado;

import javax.xml.transform.Result;
import java.sql.*;

public class StatusDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public void definirStatus(StatusChamado status){
        String sql = "INSERT INTO statuschamado (nome, idChamado) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectionFactory.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, status.getStatus());
            ps.setInt(2, status.getChamado().getIdChamado());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idGerado = rs.getInt(1);
            status.getIdStatus();

            ps.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
