package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImplement implements CompanyDAO {
    private final Connection conn;
    public CompanyDAOImplement(Connection conn) {
        this.conn = conn;
    }
    private static final String INSERT_COM = "INSERT INTO COMPANY (NAME) VALUES ('%s')";
    private static final String SELECT_ALL = "SELECT * FROM COMPANY ORDER BY ID";


    @Override
        public void addCompany(String companyName) {
        try(Statement stmt = conn.createStatement ()){
            stmt.executeUpdate ( String.format(INSERT_COM,companyName));
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
    }
    @Override
        public List<Company> getAllCompanies(){
        List<Company> companies = new ArrayList<>();
        try(Statement stmt = conn.createStatement ();
            ResultSet rs = stmt.executeQuery ( SELECT_ALL )){
            while (rs.next()) {
                companies.add ( new Company (rs.getInt("ID"), rs.getString("NAME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
        return companies;
    }
    @Override
    public void createCompanyTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS COMPANY (
                ID INT AUTO_INCREMENT PRIMARY KEY,
                NAME VARCHAR(255) UNIQUE NOT NULL
            )
        """;
        try (Statement stmt = conn.createStatement ()){
            stmt.executeUpdate ( sql );
        }
        catch (SQLException e) {
            e.printStackTrace ();
        }
    }
}

