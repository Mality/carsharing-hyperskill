package carsharing.menus.manager;

import carsharing.Car;
import carsharing.Company;
import carsharing.DatabaseRemote;
import carsharing.menus.Menu;

import java.util.List;

public class CarListMenu implements Menu {

    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        Company company = databaseRemote.getSelectedCompany();
        List<Car> cars = databaseRemote.getAllCarsInCompany(company);
        if (!cars.isEmpty()) {
            System.out.println("Car list:");
            for (int i = 0; i < cars.size(); i++) {
                System.out.println(i + 1 + ". " + cars.get(i).getName());
            }
        } else {
            System.out.println("The car list is empty!");
        }
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
