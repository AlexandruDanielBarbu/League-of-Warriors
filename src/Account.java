import java.util.ArrayList;

public class Account {
    Information playerInfo;
    ArrayList<Character> playerCharacters;
    private int noPlayedGames;

    public int getNoPlayedGames() {
        return noPlayedGames;
    }

    public void setNoPlayedGames(int noPlayedGames) {
        this.noPlayedGames = noPlayedGames;
    }

    class Information {
        Credentials playerCredentials;
        // favourite games - sorted alphabetically
        String playerName;
        String playerCountry;
    }
}
