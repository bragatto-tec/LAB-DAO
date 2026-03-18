import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
   public static void main(String[] args) throws Exception {
      Class.forName("org.postgresql.Driver");

      String url = "jdbc:postgresql://db.qeypqmbqwvnlimpkmmnn.supabase.co:5432/postgres";
      String username = "postgres";
      String password = "1710Forca#"; 

      Connection con = DriverManager.getConnection(url, username, password);
      System.out.println("Conexão estabelecida!");

      String sqlInsert = "INSERT INTO livros (titulo, autor) VALUES (?, ?)";
      PreparedStatement pstmtInsert = con.prepareStatement(sqlInsert);
      pstmtInsert.setString(1, "O Alquimista");
      pstmtInsert.setString(2, "Paulo Coelho");
      pstmtInsert.executeUpdate();
      System.out.println("Livro inserido com sucesso!");

      String sqlUpdate = "UPDATE livros SET autor = ? WHERE titulo = ?";
      PreparedStatement pstmtUpdate = con.prepareStatement(sqlUpdate);
      pstmtUpdate.setString(1, "P. Coelho ");
      pstmtUpdate.setString(2, "O Alquimista");
      pstmtUpdate.executeUpdate();
      System.out.println("Livro atualizado!");

      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM livros");
      System.out.println("\n--- Lista de Livros ---");
      while(rs.next()) {
         System.out.println("ID: " + rs.getString("id") + " | Título: " + rs.getString("titulo") + " | Autor: " + rs.getString("autor"));
      }

      
      String sqlDelete = "DELETE FROM livros WHERE titulo = ?";
      PreparedStatement pstmtDelete = con.prepareStatement(sqlDelete);
      pstmtDelete.setString(1, "O Alquimista");
      pstmtDelete.executeUpdate();
      System.out.println("\nLivro removido para limpar o teste.");

      con.close();
   }
}
