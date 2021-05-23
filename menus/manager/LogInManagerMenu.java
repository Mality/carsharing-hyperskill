package carsharing.menus.manager;

import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.Menu;
import carsharing.menus.manager.CompanyListMenu;
import carsharing.menus.manager.CreateCompanyMenu;

public class LogInManagerMenu implements Menu {

    private final String text = "1. Company list\n" +
            "2. Create a company\n" +
            "0. Back";

    boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        System.out.println(text);
        String result = Main.scanner.nextLine();
        result = result.trim();
        System.out.println();
        if ("2".equals(result)) {
            return new CreateCompanyMenu();
        } if ("1".equals(result)) {
            return new CompanyListMenu();
        } else if ("0".equals(result)) {
            return null;
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
