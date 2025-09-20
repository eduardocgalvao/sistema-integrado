package com.grupo.sistema.chamados.dao;

import com.grupo.sistema.chamados.db.ConnectionFactory;
import com.grupo.sistema.chamados.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Classe responsável por operar a tabela usuario
public class UserDAO{
    public void cadastrar(User user) {
        //Comando SQL para inserção de dados
        String sql = "INSERT INTO usuario (nome, email, setor) VALUES (?, ?, ?)";


        PreparedStatement ps = null;

        try {
            //Parâmetros para a execução
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSetor());

            //Obtém o ID gerado pela primakey (auto_increment)
            ResultSet rs = ps.getGeneratedKeys();
            // pula para a próxima linha do banco de dados
            rs.next();
            // armazenando o id gerado (auto_increment) em uma variável
            int idGerado = rs.getInt(1);
            user.setId(idGerado);


            //executa
            ps.execute();
            //fecha
            ps.close();

        //tratamento de erro
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}