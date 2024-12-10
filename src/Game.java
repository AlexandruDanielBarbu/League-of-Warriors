import Account.Account;
import Account.Credentials;
import Characters.Character;
import Enums.GameState;
import GameMap.Grid;

import java.util.ArrayList;

public class Game {
    ArrayList<Account> accounts;
    Grid gameMap;

    private Account runningAccount;
    private Character playerCharacter;
    private GameState gameState = GameState.RUNNING;

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

    private void chooseCharacter() {
        System.out.print("Choose a character by typing his number: ");

        int characterNumber = Integer.parseInt(System.console().readLine());
        playerCharacter = runningAccount.getPlayerCharacter(characterNumber);
        System.out.println(playerCharacter);
    }

    private void gameLoop(Grid gameMap){
        while (gameState == GameState.RUNNING) {
            System.out.println("Press key to move character: W S A D");
            char choice = System.console().readLine().charAt(0);

            switch(choice){
                case 'w':
                case 'W':
                    gameMap.goNorth();
                    break;

                case 's':
                case 'S':
                    gameMap.goSouth();
                    break;

                case 'a':
                case 'A':
                    gameMap.goWest();
                    break;

                case 'd':
                case 'D':
                    gameMap.goEast();
                    break;

                default:
                    System.out.println("Invalid choice");
            }

            System.out.println(gameMap.getCharacterCell());
            System.out.println(gameMap);
        }
    }
    public void run() {
        boolean loggedIn = logIn();
        if (loggedIn) {
            runningAccount.printCharactersCreated();
            chooseCharacter();

            gameMap = Grid.createHardcodedGrid(playerCharacter, runningAccount);
            System.out.println(gameMap.toString());
            gameLoop(gameMap);
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
