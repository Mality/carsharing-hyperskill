package carsharing.menus.manager;

import carsharing.Company;
import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.Menu;

public class CompanyMenu implements Menu {

    private boolean isNew = true;

    private String text = "1. Car list\n" +
            "2. Create a car\n" +
            "0. Back";

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        Company company = databaseRemote.getSelectedCompany();
        System.out.println("'" + company.getName() + "' company\n" + text);
        String input = Main.scanner.nextLine();
        System.out.println();
        if ("1".equals(input)) {
            return new CarListMenu();
        } else if ("2".equals(input)) {
            return new CreateCarMenu();
        } else {
            return null;
        }
    }

    @Override
    public Menu continueRun(DatabaseRemote databaseRemote) {
        isNew = false;
        System.out.println(text);
        String input = Main.scanner.nextLine();
        System.out.println();
        if ("1".equals(input)) {
            return new CarListMenu();
        } else if ("2".equals(input)) {
            return new CreateCarMenu();
        } else {
            return null;
        }
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
