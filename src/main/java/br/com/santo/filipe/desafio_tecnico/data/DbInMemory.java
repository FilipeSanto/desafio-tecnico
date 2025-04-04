package br.com.santo.filipe.desafio_tecnico.data;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;

public class DbInMemory {
    public static void main(String[] args) {
        // URL de conexão para o banco de dados H2 em memória
        String jdbcUrl = "jdbc:h2:mem:testdb";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            // Criar uma tabela de exemplo
            String createTableSql = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, login VARCHAR(255), pass VARCHAR(255))";
            try (PreparedStatement createTableStmt = connection.prepareStatement(createTableSql)) {
                createTableStmt.execute();
            }

            // Inserir dados de exemplo
            String insertDataSql = "INSERT INTO users (id, login, pass) VALUES (?, ?, ?)";
            try (PreparedStatement insertDataStmt = connection.prepareStatement(insertDataSql)) {
                insertDataStmt.setInt(1, 1); // id
                insertDataStmt.setString(2, "Filipe"); // login
                String pass = new BCryptPasswordEncoder().encode("senhaTeste"); // criptografando senha
                insertDataStmt.setString(3, pass); // senha criptografada
                insertDataStmt.executeUpdate();
            }

            System.out.println("Banco de dados em memória configurado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
