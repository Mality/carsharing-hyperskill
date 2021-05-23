package carsharing;

import carsharing.menus.MainMenu;
import carsharing.menus.Menu;

import java.util.Stack;

public class MenuHandler {

    Stack<Menu> menuStack;
    DatabaseRemote databaseRemote;

    public MenuHandler(DatabaseRemote databaseRemote) {
        menuStack = new Stack<>();
        menuStack.add(new MainMenu());
        this.databaseRemote = databaseRemote;
    }

    public void run() {
        while (!menuStack.isEmpty()) {
            if (menuStack.peek() == null) {
                menuStack.pop();
                continue;
            }
            Menu currentMenu = menuStack.peek();
            Menu resultMenu;
            if (currentMenu.isNew()) {
                resultMenu = currentMenu.run(databaseRemote);
            } else {
                resultMenu = currentMenu.continueRun(databaseRemote);
            }
            if (resultMenu == null) {
                menuStack.pop();
            }  else if (resultMenu != currentMenu) {
                menuStack.add(resultMenu);
            }
        }
    }


}
