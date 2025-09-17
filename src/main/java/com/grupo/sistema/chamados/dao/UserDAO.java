package com.grupo.sistema.chamados.dao;

import com.grupo.sistema.chamados.db.ConnectionFactory;
import com.grupo.sistema.chamados.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO{
    public void cadastrar(User user) {

        String sql = "INSERT INTO usuário (Email, setor, nome) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getSetor());
            ps.setString(3, user.getNome());

            ps.execute();
            ps.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}