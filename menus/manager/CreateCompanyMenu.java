package carsharing.menus.manager;

import carsharing.Company;
import carsharing.DatabaseRemote;
import carsharing.Main;
import carsharing.menus.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateCompanyMenu implements Menu {

    boolean isNew = true;


    @Override
    public Menu run(DatabaseRemote databaseRemote) {
        isNew = false;
        System.out.println("Enter the company name:");
        String result = Main.scanner.nextLine();
        result = result.trim();
        databaseRemote.addCompany(new Company(result));
        System.out.println("The company was created!\n");
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
