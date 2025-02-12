package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImplement implements CustomerDAO {
    public final Connection conn;
    public CustomerDAOImplement ( Connection conn ) {this.conn = conn;}
    public static final String CREATE_CUSTOMER = """
            CREATE TABLE IF NOT EXISTS customer ( ID int PRIMARY KEY AUTO_INCREMENT NOT NULL, 
            name VARCHAR(255) UNIQUE NOT NULL,rented_car_ID int , 
            FOREIGN KEY(rented_car_ID) REFERENCES car(ID))""";
    public static final String INSERT_CUSTOMER = "INSERT INTO customer (name) VALUES ('%s')";
    public static final String SELECT_CUSTOMERS = "SELECT * FROM customer ORDER BY ID";
    public static final String SELECT_CAR_BY_CUSTOMER = "SELECT * FROM car WHERE ID = %d ";
    public static final String GET_COMPANY_BY_ID = "SELECT * FROM company WHERE id = %d";

    @Override
    public void addCustomer ( String customerName, Integer rentedCarID) {
        try (Statement stmt = conn.createStatement ()) {
            stmt.executeUpdate ( String.format ( INSERT_CUSTOMER , customerName ) );
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
    }

    @Override
    public void createCustomerTable () {
        try (Statement stmt = conn.createStatement ()) {
            stmt.executeUpdate ( CREATE_CUSTOMER );
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
    }

    @Override
    public List < Customer > getAllCustomers () {
        List < Customer > customerList = new ArrayList <> ();
        try (Statement stmt = conn.createStatement ()) {
            ResultSet rs = stmt.executeQuery ( SELECT_CUSTOMERS );
            while (rs.next ()) {
                customerList.add ( new Customer ( rs.getInt ( "ID" ) , rs.getString ( "name" ) , rs.getObject ( "rented_car_ID" , Integer.class ) ) );
            }
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
        return customerList;
    }

    @Override
    public List < Car > getRentedCarByCustomer ( Customer customer ) {
        List < Car > rentedCar = new ArrayList <> ();
        if (customer.getRented_car_ID () == null) {
            return rentedCar;
        }
        try (Statement stmt = conn.createStatement ()) {
            ResultSet rs = stmt.executeQuery ( String.format ( SELECT_CAR_BY_CUSTOMER , customer.getRented_car_ID () ) );
            while (rs.next ()) {
                rentedCar.add ( new Car (
                        rs.getInt ( "ID" ) ,
                        rs.getString ( "name" ) ,
                        rs.getInt ( "company_ID" )
                ) );
            }
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }

        return rentedCar;
    }
    @Override
    public List<Company> getCompanyById(Customer customer) {
        List<Company> companies = new ArrayList<>();
        List<Car> rentedCar = getRentedCarByCustomer(customer);
        // Prevents IndexOutOfBoundsException
        if (rentedCar.isEmpty()) {
            return companies;
        }
        try (Statement stmt = conn.createStatement ()){
            ResultSet rs =  stmt.executeQuery ( String.format(GET_COMPANY_BY_ID, rentedCar.get(0).getCompany_ID()));
            while (rs.next()) {
                companies.add ( new Company (rs.getInt("ID"), rs.getString("NAME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException ( e );
        }
        return companies;

    }

    @Override
    public void updateCustomerRentedCar ( int customerId , Integer carId)  {
        String updateQuery = "UPDATE CUSTOMER SET RENTED_CAR_ID = " + carId + " WHERE ID = " + customerId;

        try (Statement stmt = conn.createStatement ()) {
            stmt.executeUpdate ( updateQuery );
        } catch (SQLException e) {
            throw new RuntimeException ( "Error updating customer's rented car" , e );
        }
    }
    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT id, name, rented_car_id FROM customer WHERE id = " + customerId;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Integer rentedCarId = rs.getObject("rented_car_id", Integer.class); // Handle NULL values

                return new Customer(id, name, rentedCarId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}