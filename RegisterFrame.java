import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {
        // Set up the registration frame
        setTitle("Register");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the panel and adding components
        JPanel registerPanel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userTextField = new JTextField();
        userTextField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setColumns(10);

        JButton registerButton = new JButton("Register");

        registerPanel.add(userLabel);
        registerPanel.add(userTextField);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordField);
        registerPanel.add(new JLabel()); // Placeholder for layout
        registerPanel.add(registerButton);

        // Adding ActionListener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                // Perform registration logic (you can replace this with your registration logic)
                if (registerUser(username, password)) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding Components to the frame]
        setLocationRelativeTo(null);
        getContentPane().add(BorderLayout.CENTER, registerPanel);
        setVisible(true);

    }

    private boolean registerUser(String username, char[] password) {
        // Replace this with your registration logic
        // For simplicity, let's assume any non-empty username and password is valid
        return username.length() > 0 && password.length > 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterFrame());
    }
}
