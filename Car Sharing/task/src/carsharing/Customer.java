package carsharing;
public class Customer {
    private int ID;
    private String name;
    private Integer rented_car_ID;
    private boolean rentedBefore;
    public Customer(int ID, String name, Integer rented_car_ID) {
        this.ID = ID;
        this.name = name;
        this.rented_car_ID = rented_car_ID;
        this.rentedBefore = (rented_car_ID != null);
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;

    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRented_car_ID() {
        return rented_car_ID;
    }

    public void setRented_car_ID(Integer rented_car_ID) {
        this.rented_car_ID = rented_car_ID;
        if (rented_car_ID != null) {
            rentedBefore = true; // Mark as rented at least once
        }
    }
    public boolean hasRentedBefore() {
        return rentedBefore;
    }
}