package carsharing.menus.customer;

import carsharing.Car;
import carsharing.Company;
import carsharing.Customer;
import carsharing.DatabaseRemote;
import carsharing.menus.Menu;

public class RentedCarMenu implements Menu {
    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        Customer customer = databaseRemote.getSelectedCustomer();
        if (customer.getRentedCarId() == -1) {
            System.out.println("You didn't rent a car!\n");
            return null;
        }
        System.out.println("Your rented car:");
        Car rentedCar = databaseRemote.getCarById(customer.getRentedCarId());
        System.out.println(rentedCar.getName());
        System.out.println("Company:");
        Company company = databaseRemote.getCompanyById(rentedCar.getCompany_id());
        System.out.println(company.getName());
        System.out.println();
        return null;
    }

    @Override
    public Menu continueRun(DatabaseRemote databaseRemote) {
        return run(databaseRemote);
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
