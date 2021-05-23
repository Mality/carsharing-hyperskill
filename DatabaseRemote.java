package carsharing;

import java.util.List;

public class DatabaseRemote {

    private Database database;

    private Company selectedCompany;

    private Customer selectedCustomer;

    public DatabaseRemote(Database database) {
        this.database = database;
    }

    public List<Customer> getAllCustomers() {
        return database.getAllCustomers();
    }

    public void addCustomer(Customer customer) {
        database.addCustomer(customer);
    }

    public void returnCar(Customer customer) {
        database.returnCar(customer);
    }

    public void rentCar(Customer customer, Car car) {
        database.rentCar(customer, car);
    }

    public List<Car> getAllNotRentedCarsInCompany(Company company) {
        return database.getAllNotRentedCarsInCompany(company);
    }

    public Company getCompanyById(int id) {
        return database.getCompanyById(id);
    }

    public Car getCarById(int id) {
        return database.getCarById(id);
    }

    public List<Car> getAllNotRentedCars() {
        return database.getAllNotRentedCars();
    }

    public List<Car> getAllCars() {
        return database.getAllCars();
    }

    public List<Car> getAllCarsInCompany(Company company) {
        return database.getAllCarsInCompany(company);
    }

    public void addCar(Car car) {
        database.addCar(car);
    }

    public List<Company> getAllCompanies() {
        return database.getAllCompanies();
    }

    public void addCompany(Company company) {
        database.addCompany(company);
    }

    public Company getSelectedCompany() {
        return selectedCompany;
    }

    public void selectCompany(Company selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void selectCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
}
