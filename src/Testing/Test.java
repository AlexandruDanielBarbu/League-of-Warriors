import Account.Account;

public class Test {
    public static void main(String[] args) {

        Game game = new Game();
        game.tryAddAccount(new Account(
                "a@gmail.com",
                "1234",
                "Decebal",
                "Romania",
                null,
                10
        ));

        boolean rez = game.tryAddAccount(new Account(
                "a@gmail.com",
                "1234",
                "Decebal",
                "Romania",
                null,
                10
        ));
        System.out.println(rez + " could not create similar account");

        game.printAccounts();

        game.run();
    }
}
