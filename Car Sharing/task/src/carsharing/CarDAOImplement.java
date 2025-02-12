package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImplement implements CarDAO {
    private final Connection conn;
    public CarDAOImplement(Connection conn) {
        this.conn = conn;
    }
    public static final String INSERT_CAR = "INSERT INTO car (name, company_ID) values('%s',%d)";
    public static final String SELECT_ALL = "SELECT * FROM car ORDER BY ID";
    public static final String CREATE_CAR = """
    CREATE TABLE IF NOT EXISTS car (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) UNIQUE NOT NULL,
        company_ID INT NOT NULL,
        FOREIGN KEY (company_ID) REFERENCES company(ID)
    )
""";
    public static final String CAR_BY_COMPANY = "SELECT * FROM car WHERE COMPANY_ID = %d ORDER BY ID";
    public static final String CAR_AVAILABLE_FOR_RENT = """
SELECT id, name, company_ID FROM car 
WHERE company_ID = %d 
AND id NOT IN (SELECT rented_car_ID FROM customer WHERE rented_car_ID IS NOT NULL)
""";

    @Override
    public void createCarTable(){
        try(Statement stmt = conn.createStatement ()){
            stmt.executeUpdate ( CREATE_CAR );
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
    }
    @Override
    public void addCar(String carName, int companyID){
        try( Statement stmt = conn.createStatement ()){
            stmt.executeUpdate ( String.format(INSERT_CAR, carName, companyID) );
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
    }
    @Override
    public List<Car> getAllCars(){
        List <Car> cars = new ArrayList <> ();
        try( Statement stmt = conn.createStatement ()){
            ResultSet rs = stmt.executeQuery ( SELECT_ALL );
            while(rs.next()){
                cars.add ( new Car (rs.getInt ( "id" ), rs.getString ( "name"), rs.getInt ( "company_ID" )));
            }
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
        return cars;
    }

    @Override
    public List < Car > getCarsByCompanyID ( int companyID ) {
        List <Car> cars = new ArrayList <> ();
        if (companyID <= 0) {
            System.err.println("Error: Invalid companyID passed: " + companyID);
            return cars;
        }
        try(Statement stmt = conn.createStatement ()){
            ResultSet rs = stmt.executeQuery (String.format(CAR_BY_COMPANY, companyID));
            while(rs.next ()){
                int id = rs.getInt ("ID");
                String name = rs.getString ( "name" );
                cars.add(new Car(id, name, companyID));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException ( e );
        }
        return cars;

    }
    @Override
    public List<Car> getAvailableCarForRented(Company company){
        List <Car> cars = new ArrayList <> ();
        try(Statement stmt = conn.createStatement ()){
            ResultSet rs = stmt.executeQuery (String.format(CAR_AVAILABLE_FOR_RENT, company.getID()));
            while(rs.next ()){
                int id = rs.getInt ("ID");
                String name = rs.getString ( "name" );
                int companyID = rs.getInt ("company_ID");
                cars.add(new Car(id, name, companyID));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException ( e );
        }
        return cars;

    }
}



