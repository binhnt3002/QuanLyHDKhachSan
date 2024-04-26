package newlogin;
import javax.swing.*;

// import QLKS.persistence.DAO;
// import QLKS.persistence.DaoImpl;
// import QLKS.presentation.Controller.Controller;
// import QLKS.presentation.GUI.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import regisration.RegisrationForm;



public class LGform extends JFrame implements ActionListener {

    private JLabel usernameLabel, passwordLabel, logheadLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LGform() {
        // Set up the JFrame
        setTitle("Login Form");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and configure the components
        logheadLabel = new JLabel("Đăng nhập");
        logheadLabel.setBounds(110, 0, 250, 70);
        logheadLabel.setFont(new Font(null, Font.PLAIN, 48));

        usernameLabel = new JLabel("Tên đăng nhập:");
        usernameLabel.setBounds(20, 50, 200, 70);
        usernameLabel.setFont(new Font(null, Font.PLAIN, 20));
        usernameField = new JTextField();
        usernameField.setBounds(200, 78, 200, 20);

        passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(20, 100, 200, 70);
        passwordLabel.setFont(new Font(null, Font.PLAIN, 20));
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 128, 200, 20);

        loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(220, 170, 95, 20);
        loginButton.addActionListener(this);

        registerButton = new JButton("Đăng ký");
        registerButton.setBounds(320, 170, 95, 20);
        registerButton.addActionListener(this);

        // Add the components to the JFrame
        setLayout(null);
        add(logheadLabel);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        // Make the JFrame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            dispose();
            new RegisrationForm();
        } else if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (LoginService.authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}