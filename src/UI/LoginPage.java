package UI;

import Common.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends GameWindow {
    private final String emailPlaceholder = "Enter your email";
    private final String passwordPlaceholder = "Enter your password";

    private JLabel lblEmail, lblPassword,lblCharacterIndex;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtCharacterIndex;
    
    private JButton btnSubmit;

    public LoginPage() {
        setTitle("Login Page");
        setSize(WINDOW_W, WINDOW_H);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2, H_GAP, V_GAP));

        lblEmail = new JLabel("Email");
        JTextField txtEmail = new JTextField(20);
//        txtEmail.setText(emailPlaceholder);
//        txtEmail.setForeground(Color.GRAY);

        lblPassword = new JLabel("Password");
        JPasswordField txtPassword = new JPasswordField(20);
//        txtPassword.setText(passwordPlaceholder);
//        txtPassword.setForeground(Color.GRAY);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.WHITE);
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().logIn(txtEmail.getText(), txtPassword.getText());
                System.out.println(Game.getInstance().getRunningAccount());

                SelectCharacterUI selectCharacterUI = new SelectCharacterUI();
                selectCharacterUI.setVisible(true);

            }
        });

        add(lblEmail);
        add(txtEmail);
        add(lblPassword);
        add(txtPassword);
        add(btnSubmit);

    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}
