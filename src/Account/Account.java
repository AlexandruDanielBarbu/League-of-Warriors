package Account;

import Characters.Character;

import java.util.ArrayList;

public class Account {
    class Information {
        // favourite games - sorted alphabetically

        Credentials playerCredentials;
        String playerName;
        String playerCountry;

        public Information(String email, String password, String playerName, String playerCountry) {
            this.playerCredentials = new Credentials(email, password);
            this.playerName = playerName;
            this.playerCountry = playerCountry;
        }

        public Information(){
            this(null,null,null,null);
        }

        @Override
        public String toString() {
            return "Player: " + playerName + ", Country: " + playerCountry + playerCredentials;
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
        return playerInfo.toString() + "Played Games: " + noPlayedGames;
    }
}
