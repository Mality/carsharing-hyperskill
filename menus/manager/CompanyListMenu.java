package carsharing.menus.manager;

import carsharing.Company;
import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.Menu;

import java.util.List;

public class CompanyListMenu implements Menu {

    private boolean isNew = true;

    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        List<Company> companies = databaseRemote.getAllCompanies();
        if (!companies.isEmpty()) {
            System.out.println("Choose the company:");
            for (int i = 0; i < companies.size(); i++) {
                System.out.println(i + 1 + ". " + companies.get(i).getName());
            }
            System.out.println("0. Back");
            String input = Main.scanner.nextLine();
            System.out.println();
            input = input.trim();
            try {
                int res = Integer.parseInt(input);
                if (res > companies.size() || res < 1) {
                    return null;
                }
                databaseRemote.selectCompany(companies.get(res - 1));
                return new CompanyMenu();
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
                return null;
            }

        } else {
            System.out.println("The company list is empty!\n");
            return null;
        }
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
