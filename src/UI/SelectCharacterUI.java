package UI;

import Common.Game;

import Characters.Character;
import GameMap.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectCharacterUI extends GameWindow {
    public SelectCharacterUI() {
        setTitle("Select Character");
        setSize(WINDOW_W, WINDOW_H);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Select Character");

        ArrayList<Character> charactersList = Game.getInstance().getRunningAccount().getPlayerCharacters();
        JComboBox<Character> comboBox = new JComboBox<>(charactersList.toArray(new Character[0]));
        JButton button = new JButton("Select");
        button.addActionListener(e -> {
            Game.getInstance().chooseCharacter((Character) comboBox.getSelectedItem());

            GamePageUI gui = new GamePageUI(5);
            gui.initializeStats();

            // 3. generate hardcoded map
            Grid gameMap = Grid.createHardcodedGrid(Game.getInstance().getPlayerCharacter(), Game.getInstance().getRunningAccount());
            Game.getInstance().setGameMap(gameMap);

            System.out.println(gameMap.toString());
            gui.setVisible(true);


        });

        add(label, BorderLayout.NORTH);
        add(comboBox, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

    }
}
