package mack.lp2.agendamento.dao;

import mack.lp2.agendamento.model.Emprestimo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO extends AbstractDAO {

    @Override
    public Object create(Object obj) throws SQLException {
        try (Connection con = super.openConnection()) {
            if(obj instanceof Emprestimo) {
                Emprestimo emprestimo = (Emprestimo) obj;
                String sql = "insert into emprestimos (livro_id, data_retirada) values (?, ?)";
                
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, emprestimo.getLivroId());
                pstmt.setDate(2, Date.valueOf(emprestimo.getDataRetirada()));

                int linhas = pstmt.executeUpdate();
                if(linhas > 0)
                    System.out.println("Emprestimo inserido com sucesso");
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
        List<Object> emprestimos = new ArrayList<>();
        try (Connection con = super.openConnection()) {
            String sql = "select * from emprestimos";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Emprestimo aux = new Emprestimo(rs.getInt("id"), rs.getInt("livro_id"), rs.getDate("data_retirada").toLocalDate());
                emprestimos.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    @Override
    public Object update(Object obj) throws SQLException {
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
    }
}
