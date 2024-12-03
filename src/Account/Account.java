package Account;

import Characters.Character;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Account {
    public static class Information {
        Credentials playerCredentials;
        String playerName;
        String playerCountry;
        SortedSet<String> favoriteGames;

        public Information(String email, String password, String playerName, String playerCountry) {
            this.playerCredentials = new Credentials(email, password);
            favoriteGames = new TreeSet<>();
            this.playerName = playerName;
            this.playerCountry = playerCountry;
        }

        public Information(Credentials playerCredentials, SortedSet<String> favoriteGames, String playerName, String playerCountry) {
            this.playerCredentials = playerCredentials;
            this.favoriteGames = favoriteGames;
            this.playerName = playerName;
            this.playerCountry = playerCountry;
        }

        public Information(){
            this.playerCredentials = new Credentials();
            favoriteGames = new TreeSet<>();
            this.playerName = playerName;
            this.playerCountry = playerCountry;
        }

        @Override
        public String toString() {
            return "Player: " + playerName +
                    "\nCountry: " + playerCountry +
                    "\n" + playerCredentials +
                    "\n" + favoriteGames;
        }
    }

    Information playerInfo;
    ArrayList<Character> playerCharacters;
    private int noPlayedGames;

    public Account(String email,
                   String password,
                   String playerName,
                   String country,
                   ArrayList<Character> playerCharacters,
                   int noPlayedGames) {

        this.playerInfo = new Information(email, password, playerName, country);
        this.playerCharacters = playerCharacters;
        this.noPlayedGames = noPlayedGames;
    }

    public Account(ArrayList<Character> playerCharacters, int noPlayedGames, Information playerInfo) {
        this.playerCharacters = playerCharacters;
        this.noPlayedGames = noPlayedGames;
        this.playerInfo = playerInfo;
    }
    public Account(){
        this.playerInfo = new Information();
        this.playerCharacters = new ArrayList<>();
        this.noPlayedGames = 0;
    }

    public void printCharactersCreated(){
        for(Character character : playerCharacters){
            System.out.println(character);
        }
    }

    public int getNoPlayedGames() {
        return noPlayedGames;
    }

    public void setNoPlayedGames(int noPlayedGames) {
        this.noPlayedGames = noPlayedGames;
    }

    public Credentials getPlayerCredentials() {
        return playerInfo.playerCredentials;
    }

    @Override
    public String toString() {
        return playerInfo +
                "\nPlayed Games: " + noPlayedGames +
                "\nCharacters: " + playerCharacters +
                "\n";
    }
}
