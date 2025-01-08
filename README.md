# League of Warriors

Barbu Alexandru Daniel 324CC - Faculty of Automatic Control and Computer Science

## Development time

### Part I

The assignment took me **5 full days** of writing code. The hardest part was writing all the necessarily classes and
methods to start using the json parser provided with the homework. The logic of the game was not complicated to
implement work went **smooth from start to finish**.

### Part II

The second part was fairly quick, I have already used some design patterns and implementing new ones was easy for
the most part. The only design pattern that was difficult was visitor, but I managed to do this.

The UI of the app is the weakest link. I wanted to modify the original code as little as possible. UI clearly lack
beauty but fulfills the basic needs for user feedback.

Estimated time of implementation: ~3 days or so.

## What I have learned

### Part I

- Apply correctly the principles of object-oriented programming studied in the course;
- Build a class hierarchy based on a proposed scenario;
- Use an object-oriented design;
- Handle exceptions that may occur during the application's execution;
- Translate a real-life problem into an application.

### Part II

- Design patterns:
  - Singleton;
  - Factory;
  - Visitor;
- Use of `java swing` for UI

## How to play

### Part I (old version)

#### Login

After opening the project in an IDE of choice run the `main` function of the `Test` class. Choose an account form the
list printed above in the terminal, entering the `mail` and `password`. After a successful login you are prompted to
choose a character to play as.

#### Player navigation

Once the character was chosen the testing map is displayed and you can move. Type `W A S` or `D` key followed by enter
to select the command. Pressing `x` will bring up player stats and completed achievements.

#### Enemy battle

When battling an enemy you will be the first to attack. You can choose **one normal attack** or one **spell attack**. 

>Note: Each enemy (**even you - the player**) have one immunity (`ice`, `fire`, `earth`). If you choose an attack that
>is blocked by the immunity nothing will happen, the game continues with the opponent's turn. 

If you won the game moves on, otherwise the `GAME OVER!` will show.

#### Continue game or quit

If the player dies or if a portal is discovered the player can choose whether to continue by pressing either `y`
or not by pressing `n`.

### Part II (new version with UI)

The app is pretty straight forward, just log in, select your available character and start playing.

The only different thing now is the UI the rest is still applicable: achievements, player stats and other functions
that can be called from the terminal.

## Custom work to the game

I have added `achievements`, used the `singleton pattern` for the `Game` class changed the logic of the map printing
methode and altered the logic of the `stats`, `mana cost`, `getDamage` and so on...

The player spawns at position `(0,0)` and the portal at position `(width, length)`. I wanted the player to be forced to
explore the entire map for sanctuaries before reaching the end.

## Missing features

The game does not have an end screen (if you do not count the popup window as an end screen).

> Note: If you prefer the old version of the game you may find it on GitHub, right before the commits that
> uses a design pattern. Design patterns were commited fully working so either of the commits works just fine.