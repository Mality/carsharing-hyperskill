package carsharing.menus.customer;

import carsharing.Customer;
import carsharing.DatabaseRemote;
import carsharing.menus.Menu;

public class ReturnRentedCarMenu implements Menu {
    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        Customer customer = databaseRemote.getSelectedCustomer();
        if (customer.getRentedCarId() == -1) {
            System.out.println("You didn't rent a car!\n");
            return null;
        }
        databaseRemote.returnCar(customer);
        System.out.println("You've returned a rented car!\n");
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
