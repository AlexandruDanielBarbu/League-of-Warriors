import Account.Account;
import Account.Credentials;
import GameMap.Grid;

import java.util.ArrayList;

public class Game {
    ArrayList<Account> accounts;
    Grid gameMap;

    public Game() {
        accounts = new ArrayList<Account>();
    }

    public boolean tryAddAccount(Account account) {
        if (accounts.contains(account)) {
            return false;
        } else {
            accounts.add(account);
        }
        return true;
    }

    public void listAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public void run() {
        System.out.println("Enter your credentials: email password");

        String email = System.console().readLine();
        String password = System.console().readLine();
        Credentials loggedInUserCredentials = new Credentials(email, password);

        System.out.println("Welcome " + email + " " + password);

        boolean wasFound = false;
        for (int i = 0; i < accounts.size(); i++) {
            Credentials accountCredentials = accounts.get(i).getPlayerCredentials();

            if (loggedInUserCredentials.equals(accountCredentials)) {
                wasFound = true;
                System.out.println("You have an account");
            }
        }

        if (!wasFound) {
            System.out.println("You have not logged in");
        }
    }

    // method for getting the cell commands and next available command
}
