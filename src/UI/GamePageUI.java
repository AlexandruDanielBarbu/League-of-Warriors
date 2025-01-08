package UI;

import Characters.Character;
import Common.Enemy;
import Common.Game;
import Enums.CellEntityType;
import Enums.GameState;
import GameMap.Grid;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePageUI extends GameWindow {
    static private GameState gameState = GameState.RUNNING;

    static public void setGameState(GameState gameState) {
        GamePageUI.gameState = gameState;
    }

    private int gridSize;
    private JPanel leftPanel;
    private JButton btnNorth, btnSouth, btnEast, btnWest;
    private JLabel lblLevel, lblExperience, lblHP, lblMana, lblCharacterName;

    private JPanel gridPanel;
    private JButton[][] gridButtons;

    public GamePageUI(int gridSize) {
        this.gridSize = gridSize;
        gridButtons = new JButton[gridSize][gridSize];

        setTitle("Game Page");
        setSize(WINDOW_W, WINDOW_H);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        leftPanel = new JPanel();
//        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setLayout(new GridLayout(10, 1, H_GAP, V_GAP));
        leftPanel.setBorder(new EmptyBorder(H_GAP, V_GAP, H_GAP, V_GAP));

        add(leftPanel, BorderLayout.WEST);

        //region Moves Buttons
        btnNorth = new JButton("North");
        btnNorth.setBackground(Color.BLACK);
        btnNorth.setForeground(Color.WHITE);
        btnNorth.addActionListener(e -> {
            // move character
            Game.getInstance().getGameMap().goNorth();
            updateVisualsSpawnBattleFrame();

        });

        btnSouth = new JButton("South");
        btnSouth.setBackground(Color.BLACK);
        btnSouth.setForeground(Color.WHITE);
        btnSouth.addActionListener(e -> {
            // move character
            Game.getInstance().getGameMap().goSouth();
            updateVisualsSpawnBattleFrame();

        });

        btnEast = new JButton("East");
        btnEast.setBackground(Color.BLACK);
        btnEast.setForeground(Color.WHITE);
        btnEast.addActionListener(e -> {
            // move character
            Game.getInstance().getGameMap().goEast();
            updateVisualsSpawnBattleFrame();

        });

        btnWest = new JButton("West");
        btnWest.setBackground(Color.BLACK);
        btnWest.setForeground(Color.WHITE);
        btnWest.addActionListener(e -> {
            // move character
            Game.getInstance().getGameMap().goWest();
            updateVisualsSpawnBattleFrame();
        });

        leftPanel.add(btnNorth);
        leftPanel.add(btnSouth);
        leftPanel.add(btnEast);
        leftPanel.add(btnWest);
        //endregion

        //region Stats Panel
        lblLevel = new JLabel("Level: 0");
        lblExperience = new JLabel("Experience: 0");
        lblHP = new JLabel("HP: 100");
        lblMana = new JLabel("Mana: 100");
        lblCharacterName = new JLabel("Character Name: Default");

        leftPanel.add(lblLevel);
        leftPanel.add(lblExperience);
        leftPanel.add(lblHP);
        leftPanel.add(lblMana);
        leftPanel.add(lblCharacterName);
        //endregion

        // map part
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));
        add(gridPanel, BorderLayout.CENTER);

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                // make myself a new button at these coordinates
                gridButtons[row][col] = new JButton("?");
                // style the button
                gridButtons[row][col].setBackground(Color.LIGHT_GRAY);
                // add the button to the UI
                gridPanel.add(gridButtons[row][col]);
            }
        }
    }

    private void updateVisualsSpawnBattleFrame() {
        System.out.println(Game.getInstance().getGameMap().getCharacterCell().getCellType());
        if (Game.getInstance().getGameMap().getCharacterCell().getCellType() == CellEntityType.ENEMY
            && !Game.getInstance().getGameMap().getCharacterCell().isVisited()) {
            FightUI fightUI = new FightUI(Game.getInstance().getPlayerCharacter(), new Enemy());
            fightUI.updateStatsVisuals();
            fightUI.setVisible(true);
        }
        if (gameState == GameState.FINISHED_BAD) {
            System.exit(0);
        }
        // update visuals
        updateVisuals(Game.getInstance().getPlayerCharacter());
    }

    private void updateVisuals(Character playerCharacter) {
        // Update player visuals
        setLevelLbl(playerCharacter.getLevel());
        setExperienceLbl(playerCharacter.getExperience());
        setHpLbl(playerCharacter.getCurrentHP());
        setManaLbl(playerCharacter.getCurrentMana());

        // Update map
        Grid gameMap = Game.getInstance().getGameMap();

        updateGameMapVisuals(gameMap);

        // Highlight player on the map
        updateGridPanel(gameMap.getCharacterCell().getX(), gameMap.getCharacterCell().getY(), "PLAYER", Color.ORANGE);

        if (gameMap.get(gameMap.getCharacterCell().getX()).get(gameMap.getCharacterCell().getY()).getCellType() == CellEntityType.PORTAL){
            boolean reset = showPopup("Do you want to continue?");
            if (reset) {
                resetMap();
            }
            else{
                System.exit(0);
            }
        }
    }

    private void resetMap() {
        // generate new map
        Grid gameMap = Grid.createHardcodedGrid(Game.getInstance().getPlayerCharacter(), Game.getInstance().getRunningAccount());
        Game.getInstance().setGameMap(gameMap);

        // Update the visuals
        updateGameMapVisuals(gameMap);
    }

    private void updateGameMapVisuals(Grid gameMap) {
        for (int row = 0; row < gameMap.getWidth(); row++) {
            for (int col = 0; col < gameMap.getLength(); col++) {
                if (gameMap.get(row).get(col).isVisited())
                    updateGridPanel(row,col,gameMap.get(row).get(col).getCellType().toString(), Color.LIGHT_GRAY);
                else
                    updateGridPanel(row,col,"?");
            }
        }
    }



    public void setLevelLbl(int level) {
        lblLevel.setText("Level: " + level);
    }

    public void setExperienceLbl(int experience) {
        lblExperience.setText("Experience: " + experience);
    }

    public void setHpLbl(int hp) {
        lblHP.setText("HP: " + hp);
    }

    public void setManaLbl(int mana) {
        lblMana.setText("Mana: " + mana);
    }

    public void setCharacterNameLbl(String characterName) {
        lblCharacterName.setText("Character Name: " + characterName);
    }

    public void updateGridPanel(int row, int col, String symbol) {
        gridButtons[row][col].setText(symbol);
        gridButtons[row][col].setBackground(Color.LIGHT_GRAY);
    }
    public void updateGridPanel(int row, int col, String symbol, Color color) {
        gridButtons[row][col].setText(symbol);
        gridButtons[row][col].setBackground(color);
    }

    public void initializeStats(){
        setLevelLbl(Game.getInstance().getPlayerCharacter().getLevel());
        setExperienceLbl(Game.getInstance().getPlayerCharacter().getExperience());
        setHpLbl(Game.getInstance().getPlayerCharacter().getCurrentHP());
        setManaLbl(Game.getInstance().getPlayerCharacter().getCurrentMana());
        setCharacterNameLbl(Game.getInstance().getPlayerCharacter().getName());
    }
    public static void main(String[] args) {
        GamePageUI gui = new GamePageUI(10);
        gui.setVisible(true);
    }
}
