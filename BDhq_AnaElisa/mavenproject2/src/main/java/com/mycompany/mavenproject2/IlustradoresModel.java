import com.mycompany.mavenproject2.IlustradoresBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashSet;

public class IlustradoresModel {

    static void createIlustrador(IlustradoresBean ilustrador, Connection con) throws SQLException {
        String sql = "INSERT INTO Ilustradores (nome_ilustrador, telefone_ilustrador, email_ilustrador) VALUES (?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, ilustrador.getNome());
            st.setString(2, ilustrador.getTelefone());
            st.setString(3, ilustrador.getEmail());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ilustrador inserido com sucesso!");
            } else {
                System.out.println("Nenhuma linha foi inserida.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir ilustrador: " + e.getMessage());
            con.rollback();
        }
    }

    static HashSet<IlustradoresBean> listAll(Connection con) throws SQLException {
        String sql = "SELECT idilustrador, nome_ilustrador, telefone_ilustrador, email_ilustrador FROM Ilustradores";
        HashSet<IlustradoresBean> list = new HashSet<>();
        try (Statement st = con.createStatement(); ResultSet result = st.executeQuery(sql)) {
            while (result.next()) {
                IlustradoresBean ilustrador = new IlustradoresBean(
                    result.getInt("idilustrador"),
                    result.getString("nome_ilustrador"),
                    result.getString("telefone_ilustrador"),
                    result.getString("email_ilustrador")
                );
                list.add(ilustrador);
            }
        }
        return list;
    }
    
    static void alterar(IlustradoresBean a, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE ilustradores SET nome_ilustrador=?, telefone_ilustrador=?, email_ilustrador=? WHERE idilustrador=?");
        st.setString(1, a.getNome());
        st.setString(2, a.getTelefone());
        st.setString(3, a.getEmail());
        st.setInt(4, a.getIdIlustrador());
        
        st.execute();
        st.close();          
    }
    
    static void remove(int n, Connection con) throws SQLException {
        String sql = "DELETE FROM ilustradores where idilustrador=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, n);
        
        st.execute();
        st.close();
    }
}
