package UI;

import Characters.Character;
import Characters.Mage;
import Common.Enemy;
import Spells.Spell;

import javax.swing.*;
import java.awt.*;

public class FightUI extends GameWindow {
    private JPanel leftPanel, rightPanel, centerPanel;
    private JLabel hpLabelPlayer;
    private JLabel manaLabelPlayer;
    private JLabel hpLabelEnemy;
    private JLabel manaLabelEnemy;

    private Character player;
    private Enemy enemy;

    public FightUI(Character player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;

        setTitle("Fight");
        setSize(WINDOW_W, WINDOW_H);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Player panel - left
        leftPanel = new JPanel(new BorderLayout());
        JLabel leftImage = new JLabel("Image Player");
        leftImage.setBackground(Color.gray);
        hpLabelPlayer = new JLabel("Healt:    100");
        manaLabelPlayer = new JLabel("Mana:    100");

        leftPanel.add(leftImage, BorderLayout.NORTH);
        leftPanel.add(hpLabelPlayer, BorderLayout.CENTER);
        leftPanel.add(manaLabelPlayer, BorderLayout.SOUTH);

        // Enemy panel - right
        rightPanel = new JPanel(new BorderLayout());
        JLabel rightImage = new JLabel("Image enemy");
        rightImage.setBackground(Color.ORANGE);
        hpLabelEnemy = new JLabel("Healt:    100");
        manaLabelEnemy = new JLabel("Mana:    100");

        rightPanel.add(rightImage, BorderLayout.NORTH);
        rightPanel.add(hpLabelEnemy, BorderLayout.CENTER);
        rightPanel.add(manaLabelEnemy, BorderLayout.SOUTH);

        // Attack panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JButton attackButton = new JButton("ATTACK");
        attackButton.addActionListener(e -> {
            // feed the normal attack to the battle code;
            updateStatsVisuals();
        });

        JButton abilityButton = new JButton("ABILITY");
        abilityButton.addActionListener(e -> {
            // feed the selected spell to the battle code;
            updateStatsVisuals();
        });

        JComboBox<Spell> spellComboBox = new JComboBox<>(player.getAbilities().toArray(new Spell[0]));
        spellComboBox.setMaximumSize(new Dimension(350, 20));

        attackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        abilityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        spellComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(attackButton);
        centerPanel.add(Box.createVerticalStrut(V_GAP));
        centerPanel.add(abilityButton);
        centerPanel.add(Box.createVerticalStrut(V_GAP));
        centerPanel.add(spellComboBox);
        centerPanel.add(Box.createVerticalGlue());

        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

    }

    public void updateStatsVisuals() {
        hpLabelPlayer.setText("Health: " + player.getCurrentHP());
        manaLabelPlayer.setText("Mana: " + player.getCurrentMana());

        hpLabelEnemy.setText("Health: " + enemy.getCurrentHP());
        manaLabelEnemy.setText("Mana: " + enemy.getCurrentMana());
    }


    public static void main(String[] args) {
        FightUI frame = new FightUI(new Mage("Andrei", 10, 1), new Enemy());
        frame.setVisible(true);
    }
}
