package carsharing.menus.customer;

import carsharing.Customer;
import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.Menu;

import java.util.List;

public class CustomerListMenu implements Menu {
    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        List<Customer> customers = databaseRemote.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!\n");
            return null;
        }
        System.out.println("Customer list:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(i + 1 + ". " + customers.get(i).getName());
        }
        System.out.println("0. Back");
        String input = Main.scanner.nextLine();
        System.out.println();
        int num = Integer.parseInt(input);
        if (num > customers.size() || num < 1) {
            return null;
        }
        databaseRemote.selectCustomer(customers.get(num - 1));
        return new CustomerMenu();
    }

    @Override
    public Menu continueRun(DatabaseRemote databaseRemote) {
        return null;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
