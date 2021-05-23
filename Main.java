package carsharing;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {


    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String dbURL;
        if (args.length >= 2 && "-databaseFileName".equals(args[0])) {
            dbURL = "jdbc:h2:.\\src\\carsharing\\db\\" + args[1];
        } else {
            dbURL = "jdbc:h2:.\\src\\carsharing\\db\\carsharing";
        }
        Database database = new Database();
        database.setUrl(dbURL);
        database.openConnectionAndCreateTables();
        DatabaseRemote databaseRemote = new DatabaseRemote(database);
        MenuHandler menuHandler = new MenuHandler(databaseRemote);
        menuHandler.run();
        try {
            database.closeConnection();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}