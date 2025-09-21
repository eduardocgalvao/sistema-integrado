package com.grupo.sistema.chamados.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory{

    // Dados para a conexão com o banco de dados
    private static final String url = "jdbc:mysql://root:naNIJbOCSJpQELMIpExGicuIfwuQcNLi@yamabiko.proxy.rlwy.net:57757/sistema_chamado";
    private static final String user = "root";
    private static final String password = "naNIJbOCSJpQELMIpExGicuIfwuQcNLi";

    private static Connection conn;

    public static Connection getConexao(){

        try{
            if(conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else{
                return conn;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }
}