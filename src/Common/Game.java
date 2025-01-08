package Common;

import Account.Account;
import Account.Credentials;
import Achievements.*;
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

        achievements = new ArrayList<Achievement>();
        achievements.add(new DevoidOfLife());
        achievements.add(new Oasis());
        achievements.add(new Wasteland());
        achievements.add(new Death());
    }

    public static synchronized Game getInstance() {
        if (game_instance == null) {
            game_instance = new Game();
        }

        return game_instance;
    }
    //endregion

    public Grid getGameMap() {
        return gameMap;
    }

    public void setGameMap(Grid gameMap) {
        this.gameMap = gameMap;
    }

    private Grid gameMap;
    private Character playerCharacter;

    //region Game State Handle
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

    public Account getRunningAccount() {
        return runningAccount;
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

    public boolean logIn(String email, String password) {
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
//            return logIn();

        }

        return wasFound;
    }
    //endregion

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    private ArrayList<Achievement> achievements;

    public void chooseCharacter(Character character) {
        ArrayList<Character> characterArrayList = runningAccount.getPlayerCharacters();
        for (int i = 0; i < characterArrayList.size(); i++) {
            if (characterArrayList.get(i).getName().equals(character.getName())) {
                playerCharacter = characterArrayList.get(i);
                System.out.println(playerCharacter);
                System.out.println("Success!!");
            }
        }
    }

    private void chooseCharacter(int characterNumber) {
        if (characterNumber <= 0 || characterNumber >= runningAccount.getPlayerCharacters().size()) {
           System.out.println("Invalid character number!");
        }
        else {
            playerCharacter = runningAccount.getPlayerCharacter(characterNumber);
            System.out.println(playerCharacter);
        }
    }

    private void chooseCharacter() {
        System.out.print("Choose a character by typing his number: ");
        int characterNumber = 0;

        try {
            characterNumber = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            characterNumber = 0;
        }

        if (characterNumber <= 0 || characterNumber >= runningAccount.getPlayerCharacters().size()) {
            System.out.println("Invalid character number!");
            chooseCharacter();
            return;
        }

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
                    case 'a', 'A' -> gameMap.goWest();
                    case 's', 'S' -> gameMap.goSouth();
                    case 'd', 'D' -> gameMap.goEast();
                    case 'x' -> {
                        System.out.printf("HP: %s MANA: %s XP: %s LVL: %s%n",
                                playerCharacter.getCurrentHP(),
                                playerCharacter.getCurrentMana(),
                                playerCharacter.getExperience(),
                                playerCharacter.getLevel());
                        for (Achievement achievement : achievements) {
                            System.out.println(achievement);
                        }
                    }
                    default -> throw new InvalidPlayerMove("Invalid player input!");
                }

                System.out.println(gameMap.getCharacterCell());
                System.out.println(gameMap);
            } catch (InvalidPlayerMove e){
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println("Some other random error occurred\n" + e.getMessage());
            }
        }

        // determine next game loop
        determineNextGameLoop();
   }

    private void determineNextGameLoop() {
       switch (gameState) {
           case FINISHED_BAD ->{
               System.out.println("GAME OVER!\n\nContinue? (Y/N)");
           }
           case FINISHED_GOOD -> {
               System.out.println("MAP COMPLETED!\n\nContinue? (Y/N)");
           }
       }

        char choice = System.console().readLine().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            if (gameState == GameState.FINISHED_BAD) {
                achievements.get(3).setCompleted(true);
                playerCharacter.healthRegen(playerCharacter.MAX_HP);
                playerCharacter.manaRegen(playerCharacter.MAX_MANA);
            }

            gameMap = Grid.createHardcodedGrid(playerCharacter, runningAccount);
            gameState = GameState.RUNNING;
            gameLoop();
        }
   }

   public void run() {
       // 1. log in
//       boolean loggedIn = logIn();
       boolean loggedIn = true;
       if (loggedIn) {
           // 2. choose character
           runningAccount.printCharactersCreated();
           chooseCharacter();

           // 3. generate the map
           gameMap = Grid.createHardcodedGrid(playerCharacter, runningAccount);
           System.out.println(gameMap.toString());
           gameLoop();
           determineNextGameLoop();
       }
   }

   public void runTest() {
        // 1. log in
//        boolean loggedIn = logIn();
       boolean loggedIn = true;
        if (loggedIn) {
            // 2. choose character
            runningAccount.printCharactersCreated();
            chooseCharacter();

            // 3. generate the map
            gameMap = Grid.createHardcodedGrid(playerCharacter, runningAccount);
            System.out.println(gameMap.toString());

            // 4. enter game loop
            gameLoop();
        }
    }

    public Character getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(Character playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

}
