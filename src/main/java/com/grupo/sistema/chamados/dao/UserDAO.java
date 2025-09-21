package com.grupo.sistema.chamados.dao;

import com.grupo.sistema.chamados.db.ConnectionFactory;
import com.grupo.sistema.chamados.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// Classe responsável por operar a tabela usuario
public class UserDAO{

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    public void cadastrar(User user) {
        //Comando SQL para inserção de dados
        String sql = "INSERT INTO usuario (nome, email, setor) VALUES (?, ?, ?)";


        PreparedStatement ps = null;

        try {
            //Parâmetros para a execução
            ps = ConnectionFactory.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSetor());

            ps.executeUpdate();
            //Obtém o ID gerado pela primakey (auto_increment)
            ResultSet rs = ps.getGeneratedKeys();
            // pula para a próxima linha do banco de dados
            rs.next();
            // armazenando o id gerado (auto_increment) em uma variável
            int idGerado = rs.getInt(1);
            user.setId(idGerado);

            //fecha
            ps.close();

        //tratamento de erro
        } catch (SQLException e){
            e.printStackTrace();
        }



    }

    public List<User> SearchUser(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try {
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                User u = new User();

                u.setId(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSetor(rs.getString("setor"));
                users.add(u);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}