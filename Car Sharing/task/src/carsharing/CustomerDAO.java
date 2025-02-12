package carsharing;

import java.util.List;

public interface CustomerDAO {
    void addCustomer(String customerName,Integer rentedCarID);
    void createCustomerTable();
    List <Customer> getAllCustomers();
    List <Car> getRentedCarByCustomer(Customer customer);
    List<Company> getCompanyById(Customer customer);
    void updateCustomerRentedCar(int customerId, Integer carId);
    Customer getCustomerById(int customerId);
}
