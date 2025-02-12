package carsharing;

public class Car {
    private int ID;
    private String name;
    private int company_ID;
    public Car(int ID, String name, int company_ID) {
        this.ID = ID;
        this.name = name;
        this.company_ID = company_ID;
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
    public int getCompany_ID() {
        return company_ID;
    }
    public void setCompany_ID(int company_ID) {
        this.company_ID = company_ID;
    }

}
