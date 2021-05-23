package carsharing.menus.customer;

import carsharing.Customer;
import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.Menu;

public class CreateCustomerMenu implements Menu {
    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        System.out.println("Enter the customer name:");
        String input = Main.scanner.nextLine();
        databaseRemote.addCustomer(new Customer(input));
        System.out.println("The customer was added!\n");
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
