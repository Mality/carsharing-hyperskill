package carsharing.menus.customer;

import carsharing.*;
import carsharing.menus.Menu;

import java.util.List;

public class RentCarMenu implements Menu {
    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        Customer customer = databaseRemote.getSelectedCustomer();
        if (customer.getRentedCarId() != -1) {
            System.out.println("You've already rented a car!\n");
            return null;
        }
        while (true) {
            System.out.println("Choose a company:");
            List<Company> companies = databaseRemote.getAllCompanies();
            for (int i = 0; i < companies.size(); i++) {
                System.out.println(i + 1 + ". " + companies.get(i).getName());
            }
            System.out.println("0. Back");
            String input = Main.scanner.nextLine();
            System.out.println();
            int num = Integer.parseInt(input);
            if (num > companies.size() || num < 1) {
                return null;
            }
            Company selectedCompany = companies.get(num - 1);
            System.out.println("Choose a car:");
            List<Car> cars = databaseRemote.getAllNotRentedCarsInCompany(selectedCompany);
            for (int i = 0; i < cars.size(); i++) {
                System.out.println(i + 1 + ". " + cars.get(i).getName());
            }
            System.out.println("0. Back");
            input = Main.scanner.nextLine();
            System.out.println();
            num = Integer.parseInt(input);
            if (num > cars.size() || num < 1) {
                continue;
            }
            Car selectedCar = cars.get(num - 1);
            customer.setRentedCarId(selectedCar.getId());
            databaseRemote.rentCar(customer, selectedCar);

            System.out.println("You rented '" + selectedCar.getName() + "'\n");
            break;
        }
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
