package carsharing.menus.manager;

import carsharing.Car;
import carsharing.Company;
import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.Menu;

public class CreateCarMenu implements Menu {

    boolean isNew = true;


    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        Company company = databaseRemote.getSelectedCompany();
        System.out.println("Enter the car name:");
        String input = Main.scanner.nextLine();
        databaseRemote.addCar(new Car(company.getId(), input));
        System.out.println("The car was added!");
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
