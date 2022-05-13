package Mechanic;

public class Customer {
    private String customerName;
    private int phonenumber;
    private String brand;
    private String licensePLate;
    private int yearOfManufacture;
    private int cost;
    private final Problems problems;

    public Customer (String customerName, int phonenumber, String brand, String licensePLate,
                     int yearOfManufacture, int cost, Problems problems) {
        this.customerName = customerName;
        this.phonenumber = phonenumber;
        this.brand = brand;
        this.licensePLate = licensePLate;
        this.yearOfManufacture = yearOfManufacture;
        this.cost = cost;
        this.problems = problems;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Problems getProblems() {
        return problems;
    }

    public String getLicensePLate() {
        return licensePLate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLicensePLate(String licensePLate) {
        this.licensePLate = licensePLate;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return " " +
                customerName +
                ", " + phonenumber + ", " + brand + ", " + licensePLate + ", " + yearOfManufacture + ", " + cost +
                ", " + problems +
                "} \n";
    }
    public Problems getproblems(){
        return problems;
    }
}
