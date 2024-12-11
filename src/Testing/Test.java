import Common.Game;
import Common.JsonInput;

public class Test {
    public static void main(String[] args) {

        Game game = Game.getInstance();
//        game.tryAddAccount(new Account(
//                "a@gmail.com",
//                "1234",
//                "Decebal",
//                "Romania",
//                null,
//                10
//        ));
//
//        boolean rez = game.tryAddAccount(new Account(
//                "a@gmail.com",
//                "1234",
//                "Decebal",
//                "Romania",
//                null,
//                10
//        ));
//        System.out.println(rez + " could not create similar account");


        game.setAccounts(JsonInput.deserializeAccounts());
        game.printAccounts();

        game.runTest();
    }
}
