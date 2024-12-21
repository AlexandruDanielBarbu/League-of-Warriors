package Common;

import Enums.CharacterType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import Account.Account;
import Account.Credentials;
import Characters.Character;
import Characters.CharacterFactory;
import Characters.Mage;
import Characters.Rogue;
import Characters.Warrior;

import javax.xml.stream.events.Characters;

public class JsonInput {
    public static ArrayList<Account> deserializeAccounts() {
        String accountPath = "D:\\University\\Anul II\\POO\\League-of-Warriors\\usersData\\accounts.json";
        try {
            String content = new String((Files.readAllBytes(Paths.get(accountPath))));
            JSONObject obj = new JSONObject(content);
            JSONArray accountsArray = (JSONArray) obj.get("accounts");

            ArrayList<Account> accounts = new ArrayList<>();
            for (int i=0; i < accountsArray.length(); i++) {
                JSONObject accountJson = (JSONObject) accountsArray.get(i);
                // name, country, games_number
                String name = (String) accountJson.get("name");
                String country = (String) accountJson.get("country");
                int gamesNumber = Integer.parseInt((String)accountJson.get("maps_completed"));

                // Credentials
                Credentials credentials = null;
                try {
                    JSONObject credentialsJson = (JSONObject) accountJson.get("credentials");
                    String email = (String) credentialsJson.get("email");
                    String password = (String) credentialsJson.get("password");

                    credentials = new Credentials(email, password);
                } catch (JSONException e) {
                    System.out.println("! This account doesn't have all credentials !");
                }

                // Favorite games
                SortedSet<String> favoriteGames = new TreeSet();
                try {
                    JSONArray games = (JSONArray) accountJson.get("favorite_games");
                    for (int j = 0; j < games.length(); j++) {
                        favoriteGames.add((String) games.get(j));
                    }
                } catch (JSONException e) {
                    System.out.println("! This account doesn't have favorite games !");
                }

                // Characters
                ArrayList<Character> characters = new ArrayList<>();
                try {
                    JSONArray charactersListJson = (JSONArray) accountJson.get("characters");
                    for (int j = 0; j < charactersListJson.length(); j++) {
                        JSONObject charJson = (JSONObject) charactersListJson.get(j);
                        String cname = (String) charJson.get("name");
                        String profession = (String) charJson.get("profession");
                        String level = (String) charJson.get("level");
                        int lvl = Integer.parseInt(level);
                        Integer experience = (Integer) charJson.get("experience");

                        Character newCharacter = null;
                        if (profession.equals("Warrior"))
                            newCharacter = CharacterFactory.buildCharacter(CharacterType.WARRIOR, cname, experience, lvl);
                        if (profession.equals("Rogue"))
                            newCharacter = CharacterFactory.buildCharacter(CharacterType.ROGUE, cname, experience, lvl);
                        if (profession.equals("Mage"))
                            newCharacter = CharacterFactory.buildCharacter(CharacterType.MAGE, cname, experience, lvl);
                        characters.add(newCharacter);
                    }
                } catch (JSONException e) {
                    System.out.println("! This account doesn't have characters !");
                }

                Account.Information information = new Account.Information()
                                                    .configPlayerCredentials(credentials)
                                                    .configFavoriteGames(favoriteGames)
                                                    .configPlayerName(name)
                                                    .configPlayerCountry(country);

                Account account = new Account(characters, gamesNumber, information);
                accounts.add(account);
            }
            return accounts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}