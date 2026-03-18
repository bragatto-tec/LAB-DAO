package br.mackenzie.bd_inicial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.mackenzie.bd_inicial.model.Livro;

public class LivroDAO extends AbstractDAO {

    @Override
    public Object create(Object obj) throws SQLException {
        try (Connection con = openConnection()) {
            if(obj instanceof Livro) {
                Livro livro = (Livro) obj;
                String sql = "insert into livros (titulo, autor) values (?, ?)";
                
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setString(1, livro.getTitulo());
                    pstmt.setString(2, livro.getAutor());
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
            String sql = "select * from livros where id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if(rs.next()) {
                        return new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"));
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
        List<Object> listaLivros = new ArrayList<>();
        try (Connection con = openConnection()) {
            String sql = "select * from livros";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next()) {
                    Livro aux = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"));
                    listaLivros.add(aux);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaLivros;
    }

    @Override
    public Object update(Object obj) throws SQLException {
        try (Connection con = openConnection()) {
            if(obj instanceof Livro) {
                Livro livro = (Livro) obj;
                String sql = "update livros set titulo = ?, autor = ? where id = ?";
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setString(1, livro.getTitulo());
                    pstmt.setString(2, livro.getAutor());
                    pstmt.setInt(3, livro.getId());
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
            String sql = "delete from livros where id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
