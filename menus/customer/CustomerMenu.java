package carsharing.menus.customer;

import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.Menu;

public class CustomerMenu implements Menu {

    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        System.out.println("1. Rent a car\n" +
                "2. Return a rented car\n" +
                "3. My rented car\n" +
                "0. Back");
        String input = Main.scanner.nextLine();
        System.out.println();
        if ("1".equals(input)) {
            return new RentCarMenu();
        } else if ("2".equals(input)) {
            return new ReturnRentedCarMenu();
        } else if ("3".equals(input)) {
            return new RentedCarMenu();
        } else {
            return null;
        }
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
