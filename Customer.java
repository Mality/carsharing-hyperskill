package carsharing;

public class Customer {

    private int id = -1;
    private int rentedCarId = -1;
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(int id, int rentedCarId, String name) {
        this.id = id;
        this.rentedCarId = rentedCarId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getRentedCarId() {
        return rentedCarId;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRentedCarId(int rentedCarId) {
        this.rentedCarId = rentedCarId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
