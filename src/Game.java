import Account.Account;
import Account.Credentials;
import Characters.Character;
import GameMap.Grid;

import java.util.ArrayList;

public class Game {
    ArrayList<Account> accounts;
    Grid gameMap;

    private Account runningAccount;
    private Character playerCharacter;

    public Game() {
        accounts = new ArrayList<Account>();
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public boolean tryAddAccount(Account account) {
        if (accounts.contains(account)) {
            return false;
        } else {
            accounts.add(account);
        }
        return true;
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void printAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public void swapRunningAccount(Account account) {
        runningAccount = account;
    }

    private boolean logIn() {
        System.out.println("Enter your credentials:");

        System.out.print("Email: ");
        String email = System.console().readLine();

        System.out.print("Password: ");
        String password = System.console().readLine();

        Credentials loggedInUserCredentials = new Credentials(email, password);

        boolean wasFound = false;
        for (int i = 0; i < accounts.size(); i++) {
            Credentials accountCredentials = accounts.get(i).getPlayerCredentials();

            if (loggedInUserCredentials.equals(accountCredentials)) {
                wasFound = true;
                System.out.println("Welcome " + email + "!");
                swapRunningAccount(accounts.get(i));
            }
        }

        if (!wasFound) {
            System.out.println("You do not have an account!");
        }

        return wasFound;
    }

    private void chooseCharacter(){
        System.out.print("Choose a character by typing his number: ");

        int characterNumber = Integer.parseInt(System.console().readLine());
        playerCharacter = runningAccount.getPlayerCharacter(characterNumber);
        System.out.println(playerCharacter);
    }
    public void run() {
        boolean loggedIn = logIn();
        if (loggedIn) {
            runningAccount.printCharactersCreated();
            chooseCharacter();
            gameMap = Grid.createHarcodedGrid(playerCharacter);
            System.out.println(gameMap.toString(true));
        }

    }

    public Character getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(Character playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    // method for getting the cell commands and next available command
}
