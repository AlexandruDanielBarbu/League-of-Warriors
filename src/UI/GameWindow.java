package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

abstract class GameWindow extends JFrame {
    protected static final int WINDOW_W = 900;
    protected static final int WINDOW_H = 500;
    protected static final int H_GAP = 10;
    protected static final int V_GAP = 10;

    // @returns User choice (boolean)
    public static boolean showPopup(String message) {
        // Create popup window
        JDialog dialog = new JDialog((Frame) null, "Continue?", true);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Message
        JLabel label = new JLabel(message, JLabel.CENTER);
        dialog.add(label, BorderLayout.CENTER);

        // the Buttons
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        final boolean[] result = {false};

        yesButton.addActionListener((ActionEvent e) -> {
            result[0] = true;
            dialog.dispose();
        });

        noButton.addActionListener((ActionEvent e) -> {
            result[0] = false;
            dialog.dispose();
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return result[0];
    }
}
