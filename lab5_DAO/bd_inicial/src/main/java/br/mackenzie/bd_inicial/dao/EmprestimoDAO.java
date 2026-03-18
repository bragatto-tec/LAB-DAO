package br.mackenzie.bd_inicial.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.mackenzie.bd_inicial.model.Emprestimo;

public class EmprestimoDAO extends AbstractDAO {

    @Override
    public Object create(Object obj) throws SQLException {
        try (Connection con = openConnection()) {
            if(obj instanceof Emprestimo) {
                Emprestimo emprestimo = (Emprestimo) obj;
                String sql = "insert into emprestimos (livro_id, data_retirada) values (?, ?)";
                
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setInt(1, emprestimo.getLivroId());
                    pstmt.setDate(2, Date.valueOf(emprestimo.getDataRetirada()));
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object read(int id) throws SQLException {
        try (Connection con = openConnection()) {
            String sql = "select * from emprestimos where id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if(rs.next()) {
                        return new Emprestimo(rs.getInt("id"), rs.getInt("livro_id"), rs.getDate("data_retirada").toLocalDate());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object> readAll() throws SQLException {
        List<Object> emprestimos = new ArrayList<>();
        try (Connection con = openConnection()) {
            String sql = "select * from emprestimos";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next()) {
                    Emprestimo aux = new Emprestimo(rs.getInt("id"), rs.getInt("livro_id"), rs.getDate("data_retirada").toLocalDate());
                    emprestimos.add(aux);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    @Override
    public Object update(Object obj) throws SQLException {
        try (Connection con = openConnection()) {
            if(obj instanceof Emprestimo) {
                Emprestimo emprestimo = (Emprestimo) obj;
                String sql = "update emprestimos set livro_id = ?, data_retirada = ? where id = ?";
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setInt(1, emprestimo.getLivroId());
                    pstmt.setDate(2, Date.valueOf(emprestimo.getDataRetirada()));
                    pstmt.setInt(3, emprestimo.getId());
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection con = openConnection()) {
            String sql = "delete from emprestimos where id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
