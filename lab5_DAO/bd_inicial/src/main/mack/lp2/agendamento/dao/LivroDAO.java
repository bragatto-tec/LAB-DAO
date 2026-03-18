package mack.lp2.agendamento.dao;

import mack.lp2.agendamento.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class LivroDAO extends AbstractDAO {

    @Override
    public Object create(Object obj) throws SQLException {
        try (Connection con = super.openConnection()) {
            if(obj instanceof Livro) {
                Livro livro = (Livro) obj;
                String sql = "insert into livros (titulo, autor) values (?, ?)";
                
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, livro.getTitulo());
                pstmt.setString(2, livro.getAutor());

                int linhas = pstmt.executeUpdate();
                if(linhas > 0)
                    System.out.println("Livro inserido com sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object read(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Object> readAll() throws SQLException {
        List<Object> listaLivros = new ArrayList<>();
        try (Connection con = super.openConnection()) {
            String sql = "select * from livros";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Livro aux = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"));
                listaLivros.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaLivros;
    }

    @Override
    public Object update(Object obj) throws SQLException {
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
    }
}
