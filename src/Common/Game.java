package Common;

import Account.Account;
import Account.Credentials;
import Characters.Character;
import CustomExceptions.InvalidPlayerMove;
import Enums.GameState;
import GameMap.Grid;

import java.util.ArrayList;

public class Game {
    //region Singleton
    private static Game game_instance = null;

    private Game() {
        accounts = new ArrayList<Account>();
    }

    public static synchronized Game getInstance() {
        if (game_instance == null) {
            game_instance = new Game();
            return game_instance;
        }
        return game_instance;
    }
    //endregion

    private Grid gameMap;
    private Character playerCharacter;

    //region Common.Game State
    private GameState gameState = GameState.RUNNING;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    //endregion


    //region Account management
    private Account runningAccount;
    ArrayList<Account> accounts;

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    // does not work yet
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
    //endregion

    private void chooseCharacter() {
        System.out.print("Choose a character by typing his number: ");

        int characterNumber = Integer.parseInt(System.console().readLine());
        playerCharacter = runningAccount.getPlayerCharacter(characterNumber);
        System.out.println(playerCharacter);
    }

    private void gameLoop(){
        while (gameState == GameState.RUNNING) {
            System.out.println("Press key to move character: W A S D");
            char choice = System.console().readLine().charAt(0);

            try {
                switch (choice) {
                    case 'w', 'W' -> gameMap.goNorth();
                    case 's', 'S' -> gameMap.goSouth();
                    case 'a', 'A' -> gameMap.goWest();
                    case 'd', 'D' -> gameMap.goEast();
                    default -> throw new InvalidPlayerMove("Invalid player input!");
                }

                System.out.println(gameMap.getCharacterCell());
                System.out.println(gameMap);
            } catch (InvalidPlayerMove e){
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println("Some other random error occured\n" + e.getMessage());
            }
        }
   }

    private void determineNextGameLoop() {
       switch (gameState) {
           case FINISHED_BAD -> System.out.println("GAME OVER!");
           case FINISHED_GOOD -> {
               System.out.println("MAP COMPLETED!");
               gameMap = Grid.createRandomGrid(playerCharacter, runningAccount);
               gameState = GameState.RUNNING;
               gameLoop();
           }
       }
   }

   public void run() {
        boolean loggedIn = logIn();
        if (loggedIn) {
            runningAccount.printCharactersCreated();
            chooseCharacter();

            gameMap = Grid.createHardcodedGrid(playerCharacter, runningAccount);
            System.out.println(gameMap.toString());
            gameLoop();
            determineNextGameLoop();
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
