package com.grupo.sistema.util;

import com.grupo.sistema.config.DatabaseConfig;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CriarAdmin {
    public static void main(String[] args) {
        // Dados do administrador
        String nome = "Yan eduardo";
        String email = "yanedu117@gmail.com";
        String senha = "y11092001Y@"; // senha inicial (pode mudar depois)
        String telefone = "(91) 98302-0280";
        String setor = "TI";

        // Gera hash da senha com BCrypt
        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

        String sql = "INSERT INTO usuarios (nome, email, senha, telefone, setor, role) VALUES (?, ?, ?, ?, ?, 'ADMIN')";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, senhaHash);
            ps.setString(4, telefone);
            ps.setString(5, setor);

            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("✅ Admin criado com sucesso!");
                System.out.println("Login: " + email);
                System.out.println("Senha: " + senha);
            } else {
                System.out.println("⚠️ Nenhum registro inserido. Verifique se já existe admin com esse email.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
