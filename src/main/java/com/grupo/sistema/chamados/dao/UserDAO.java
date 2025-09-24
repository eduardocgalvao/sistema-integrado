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
    //Cadastro do usuario (user)
    public void cadastrar(User user) {
        //Comando SQL para inserção de dados
        String sql = "INSERT INTO usuario (nome, email, setor) VALUES (?, ?, ?)";


        PreparedStatement ps = null;

        try {
            //Parâmetros para a execução
            ps = ConnectionFactory.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSenha());
            ps.setString(4, user.getSetor());

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

    //Listar todos os usuários (admin)
    public List<User> SearchUser(){
        List<User> users = new ArrayList<>();
        //Comando SQL para consulta na tabela usuario
        String sql = "SELECT * FROM usuario";

        try {
            //Estabelece conexão
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            //Executa a query
            rs = ps.executeQuery();
            //Percorre as linhas do db
            while(rs.next()){
                User u = new User();
                //Consulta dados no db
                u.setId(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setSetor(rs.getString("setor"));
                users.add(u);


            }
            //Imprime a lista
            System.out.println(users.toString());

            ps.close();
            //Tratamento de erro
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;


    }

    //Edição do usuário (admin)
    public void editUser(User user){
        //Comando SQL para update(edit) na tabela usuario
        String sql = "UPDATE usuario SET nome = ?, email = ?, setor = ? where idUsuario = ?";

        try {
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSetor());
            ps.setInt(4, user.getIdUser());
            ps.executeUpdate();
            ps.close();
            System.out.println("Usuário atualizado com sucesso");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Exclusão de usuário (admin)
    public void deleteUser(User user){
        //Comando SQL para remoção de dados na tabela usuario
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";

        try {
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ps.execute();


            if (ps.execute() == false){
                System.out.println("Usuario excluido com sucesso.");
            }else{
                System.out.println("Usuario não encontrado");
            }

            ps.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}