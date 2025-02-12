package carsharing;
import java.sql.Connection;

public class Main {
    public static void main ( String[] args ) {
        String databaseFileName = "default";

        // Parse command-line arguments for the database file name
        for (int i = 0; i < args.length; i++) {
            if ("-databaseFileName".equals ( args[i] ) && i + 1 < args.length) {
                databaseFileName = args[i + 1];
            }
        }
        CarsharingDB carsharingDB = new CarsharingDB( databaseFileName );
        Connection conn = carsharingDB.getConnection();
        CompanyDAO companyDAO = new CompanyDAOImplement(conn);
        CarDAO carDAO = new CarDAOImplement(conn);
        CustomerDAO customerDAO = new CustomerDAOImplement(conn);
        companyDAO.createCompanyTable ();
        carDAO.createCarTable ();
        customerDAO.createCustomerTable();
        Menu mainMenu = new Menu(companyDAO, carDAO, customerDAO);
        mainMenu.showMenu ();
    }
}






























