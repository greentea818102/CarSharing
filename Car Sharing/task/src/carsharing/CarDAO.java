package carsharing;

import java.util.List;

public interface CarDAO {
    void addCar(String carName, int companyID);
    void createCarTable();
    List <Car> getAllCars();
    List <Car> getCarsByCompanyID(int companyID);
    List <Car> getAvailableCarForRented(Company company);
}
