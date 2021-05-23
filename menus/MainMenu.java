package carsharing.menus;

import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.customer.CustomerListMenu;
import carsharing.menus.customer.CreateCustomerMenu;
import carsharing.menus.manager.LogInManagerMenu;

public class MainMenu implements Menu {

    final private String text = "1. Log in as a manager\n" +
            "2. Log in as a customer\n" +
            "3. Create a customer\n" +
            "0. Exit";

    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        System.out.println(text);
        String result = Main.scanner.nextLine();
        result = result.trim();
        if ("0".equals(result)) {
            return null;
        }
        System.out.println();
        if ("3".equals(result)) {
            return new CreateCustomerMenu();
        } if ("2".equals(result)) {
            return new CustomerListMenu();
        } if ("1".equals(result)) {
            return new LogInManagerMenu();
        }
        return this;
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
