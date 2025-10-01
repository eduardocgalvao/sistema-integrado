package com.grupo.sistema.login;

import java.sql.*;
import java.io.InputStream;
import java.util.Properties;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {

    private Connection conectar() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");

        // Se não encontrar no ambiente, tenta no application.properties
        if (url == null || user == null || pass == null) {
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
                if (input == null) {
                    throw new RuntimeException("Arquivo application.properties não encontrado!");
                }
                Properties prop = new Properties();
                prop.load(input);

                if (url == null) url = prop.getProperty("DB_URL");
                if (user == null) user = prop.getProperty("DB_USER");
                if (pass == null) pass = prop.getProperty("DB_PASS");
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar configuração do banco", e);
            }
        }

        if (url == null || user == null || pass == null) {
            throw new RuntimeException("Configurações do banco não encontradas em variáveis de ambiente nem no properties!");
        }

        return DriverManager.getConnection(url, user, pass);
    }

    public boolean validarLogin(String email, String senha) {
        String sql = "SELECT senha FROM usuarios WHERE email = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaHash = rs.getString("senha");
                    return BCrypt.checkpw(senha, senhaHash);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao validar login: " + e.getMessage()); // 🔹 remove printStackTrace
        }
        return false;
    }

    public boolean cadastrarUsuario(String nome, String email, String senha) {
        String sqlCheck = "SELECT 1 FROM usuarios WHERE email = ?";
        String sqlInsert = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {

            checkStmt.setString(1, email);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    return false; // email já existe
                }
            }

            String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

            try (PreparedStatement insertStmt = conn.prepareStatement(sqlInsert)) {
                insertStmt.setString(1, nome);
                insertStmt.setString(2, email);
                insertStmt.setString(3, senhaHash);
                insertStmt.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage()); // 🔹 remove printStackTrace
        }
        return false;
    }
}
