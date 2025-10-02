package com.grupo.sistema.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private static String url;
    private static String user;
    private static String pass;

    static {
        // Primeiro tenta variáveis de ambiente
        url = System.getenv("DB_URL");
        user = System.getenv("DB_USER");
        pass = System.getenv("DB_PASS");

        // Se não achar, tenta carregar do application.properties
        if (url == null || user == null || pass == null) {
            try (InputStream input = DatabaseConfig.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {

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
            throw new RuntimeException("Configurações de banco não encontradas em variáveis de ambiente nem em application.properties!");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
