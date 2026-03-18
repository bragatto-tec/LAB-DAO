package br.mackenzie.bd_inicial.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO {
    String databaseURL = "file:./data/banco_dados";

    protected Connection openConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:" + databaseURL, "admin", "admin");
        ensureSchema(conn);
        return conn;
    }

    private void ensureSchema(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS livros (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "titulo VARCHAR(256) NOT NULL, " +
                            "autor VARCHAR(256) NOT NULL" +
                            ")"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS emprestimos (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "livro_id INT NOT NULL, " +
                            "data_retirada DATE NOT NULL, " +
                            "CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES livros(id) ON DELETE CASCADE" +
                            ")"
            );
        }
    }

    public abstract Object create(Object obj) throws SQLException;

    public abstract Object read(int id) throws SQLException;
    
    public abstract List<Object> readAll() throws SQLException;

    public abstract Object update(Object obj) throws SQLException;

    public abstract void delete(int id) throws SQLException;

}
