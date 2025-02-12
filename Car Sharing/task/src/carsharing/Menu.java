package carsharing;

import java.util.List;
import java.util.Scanner;

public class Menu {
    final Scanner sc = new Scanner(System.in);
    final CompanyDAO companyDAO;
    final CarDAO carDAO;
    final CustomerDAO customerDAO;
    public Menu(CompanyDAO companyDAO, CarDAO carDAO, CustomerDAO customerDAO) {
        this.companyDAO = companyDAO;
        this.carDAO = carDAO;
        this.customerDAO = customerDAO;
    }
    List <Company> companies;

    public void showMenu() {
        while (true) {
            System.out.println("1. Log in as a manager");
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    managerMenu();
                    break;
                case "2":
                    customerMenu();
                    break;
                case "3":
                    createCustomer();
                    break;
                    case "0":
                        return;
                        default:
                            System.out.println("Invalid choice");
            }

        }
    }

    public void managerMenu() {
        while (true) {
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    displayCompanies();
                    break;
                    case "2":
                        createCompany();
                        break;
                        case "0":
                            showMenu ();
                            break;
                            default:
                                System.out.println("Invalid choice");
            }
        }
    }
    public void customerMenu() {
        List<Customer> customers = customerDAO.getAllCustomers();
        if ( customers == null || customers.isEmpty()) {
            System.out.println("The customer list is empty!");
            return;
        }
            System.out.println("Choose a customer:");
        for(int i = 0; i < customers.size(); i++){
            System.out.println((i+1) + ". " + customers.get(i).getName());
        }
        System.out.println("0. Back ");
        String choice = sc.nextLine();
        if(choice.equals("0")){
            showMenu ();
            return;
        }
        int index = Integer.parseInt(choice) -1;
        if(index >=0 && index < customers.size()){
            displayMenuOfCustomer(customers.get(index));
        }
        else {System.out.println("Invalid choice");}

    }
    public void createCustomer() {
        System.out.print("Enter the customer name: ");
        String customerName = sc.nextLine();
        customerDAO.addCustomer(customerName, null);
        System.out.println ("The customer was added!");
    }

    public void displayMenuOfCustomer(Customer customer){
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                rentACar(customer);
                break;
                case "2":
                    returnARentedCar(customer);
                    break;
                    case "3":
                        myRentedCar(customer);
                        break;
                        case "0":
                            showMenu ();
                            break;
        }

    }
    public void rentACar(Customer customer){
        List <Company> companies = companyDAO.getAllCompanies();
        if (customer.getRented_car_ID() != null) {
            System.out.println("You've already rented a car!");
            displayMenuOfCustomer (customer);
            return;
        }
        if (companies == null || companies.isEmpty()) {
            System.out.println("The company list is empty!");
            displayMenuOfCustomer (customer);
            return;
        }
        System.out.println("Choose a company: ");
        for(int i = 0; i < companies.size(); i++) {
            System.out.println((i+1) + ". " + companies.get(i).getName ());
        }
        System.out.println("0. Back ");
        String choice = sc.nextLine();
        if (choice.equals("0")) {
            managerMenu();
            return;
        }
        int index = Integer.parseInt(choice) -1;
        if(index >=0 && index <companies.size()){
            displayCarsAvailableOfCompany (companies.get(index), customer, customerDAO  );
        }
    }
    public void myRentedCar(Customer customer){
        List<Car> rentedCar = customerDAO.getRentedCarByCustomer(customer);
        List<Company> company = customerDAO.getCompanyById(customer);
        if (rentedCar == null || rentedCar.isEmpty()) {
            System.out.println("You didn't rent a car!");
            displayMenuOfCustomer (customer);
            return;
        }
        else{
            for (int i = 0; i < rentedCar.size(); i++) {
                System.out.println("Your rented car:");
                System.out.println(rentedCar.get(i).getName());
            }
        }
        System.out.println("Company:");
        System.out.println(company.get(0).getName());
        displayMenuOfCustomer( customer );
    }

    public void returnARentedCar(Customer customer){
        if(customer.getRented_car_ID() != null){
            customerDAO.updateCustomerRentedCar(customer.getID(), null);
          customer.setRented_car_ID(null);
          System.out.println("You've returned a rented car!");
            displayMenuOfCustomer( customer );
        }
        else if (customer.getRented_car_ID() == null && customer.hasRentedBefore()){
            System.out.println("You've returned a rented car!");
            displayMenuOfCustomer( customer );
                    }
        else {
            System.out.println("You didn't rent a car!");
            displayMenuOfCustomer( customer );
        }
    }

    public void createCompany() {
        System.out.print("Enter the company name: ");
        String companyName = sc.nextLine();
        companyDAO.addCompany(companyName);
        System.out.println ("The company was created!");
    }

    public void displayCompanies() {
        List <Company> companies = companyDAO.getAllCompanies();
        if (companies == null || companies.isEmpty()) {
            System.out.println("The company list is empty!");
            return;
        }
        System.out.println("Choose a company: ");
        for(int i = 0; i < companies.size(); i++) {
            System.out.println((i+1) + ". " + companies.get(i).getName ());
        }
        System.out.println("0. Back ");
        String choice = sc.nextLine();
        if (choice.equals("0")) {
            managerMenu();
            return;
        }
        int index = Integer.parseInt ( choice ) -1;
         if (index >= 0 && index < companies.size ()) {
            displayMenuOfCompany(companies.get(index));
        }
        else {System.out.println("Invalid choice");}
    }

    public void displayMenuOfCompany(Company company) {
        String companyName = company.getName();
        int companyID = company.getID();
       while(true) {
           System.out.println ( "'" + companyName + "' company" );
           System.out.println ( "1. Car list" );
           System.out.println ( "2. Create a car" );
           System.out.println ( "0. Back" );

           String choice = sc.nextLine ();

           switch (choice) {
               case "1":
                   displayCarsListOfCompany (companyID, companyName );
                   break;
               case "2":
                   createCar ( companyID );
                   break;
               case "0":
                   managerMenu ();
                   break;
               default:
                   System.out.println ( "Invalid choice" );
           }
       }
    }

    public void displayCarsListOfCompany(int companyID, String companyName) {
        List<Car> cars = carDAO.getCarsByCompanyID ( companyID);
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
        }
        else {
            System.out.println( "Car list: ");
            for(int i = 0; i < cars.size(); i++) {
                System.out.println((i+1) + ". " + cars.get(i).getName ());
            }
        }
    }

    public void createCar(int companyID) {
        System.out.print("Enter the car name: ");
        String carName = sc.nextLine();
        carDAO.addCar (carName, companyID  );
        System.out.println("The car was added!");
    }

public void displayCarsAvailableOfCompany(Company company, Customer customer, CustomerDAO customerDAO) {
    List<Car> availableCar = carDAO.getAvailableCarForRented(company);

    if (availableCar.isEmpty()) {
        System.out.println("No available cars in the " + company.getName() + " company");
        return;
    }

    System.out.println("Choose a car:");
    for (int i = 0; i < availableCar.size(); i++) {
        System.out.println((i + 1) + ". " + availableCar.get(i).getName());
    }
    System.out.println("0. Back");

    String choice = sc.nextLine();
    if (choice.equals("0")) {
        displayMenuOfCustomer(customer);
        return;
    }

    int index = Integer.parseInt(choice) - 1;
    if (index >= 0 && index < availableCar.size()) {
        Car selectedCar = availableCar.get(index);
        //  update the database
        customerDAO.updateCustomerRentedCar(customer.getID(), selectedCar.getID());
        customer.setRented_car_ID(selectedCar.getID());

        System.out.println("You rented '" + selectedCar.getName() + "'");
        displayMenuOfCustomer(customer);
    }
}
}
