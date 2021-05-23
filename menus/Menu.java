package carsharing.menus;

import carsharing.DatabaseRemote;
import carsharing.MenuHandler;

public interface Menu {

    Menu run(DatabaseRemote databaseRemote);
    Menu continueRun(DatabaseRemote databaseRemote);
    boolean isNew();
}
