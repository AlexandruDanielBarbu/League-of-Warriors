package UI;

import Common.Game;
import Common.JsonInput;

public class MainMenu {
    public static void main(String[] args) {
        // load the users database
        Game.getInstance().setAccounts(JsonInput.deserializeAccounts());
        Game.getInstance().printAccounts();

        // login page
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}
