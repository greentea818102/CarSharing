package carsharing;

import java.util.List;

public interface CompanyDAO {
    void addCompany(String companyName);
    void createCompanyTable();
    List <Company> getAllCompanies();
}
