package com.grupo.sistema.login;

import com.grupo.sistema.config.DatabaseConfig;
import com.grupo.sistema.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Valida login e retorna Usuario se válido
    public Usuario validarLogin(String email, String senha) {
        String sql = "SELECT id, nome, email, role, senha FROM usuarios WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && BCrypt.checkpw(senha, rs.getString("senha"))) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setPapel(rs.getString("role"));
                    return u;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
        }
        return null;
    }

    // Cadastra novo usuário; retorna true se sucesso, false se email já existe
    public boolean cadastrarUsuario(String nome, String email, String senha, String telefone, String setor) {
        String sql = "INSERT INTO usuarios (nome, email, senha, telefone, setor, role) " +
                "SELECT ?, ?, ?, ?, ?, 'USER' WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, senhaHash);
            ps.setString(4, telefone);
            ps.setString(5, setor);
            ps.setString(6, email);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
        return false;
    }
}
