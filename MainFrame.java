import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        // Set up the main frame
        setTitle("Main Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons to open the login and register pages
        JButton loginButton = new JButton("Open Login Page");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });

        JButton registerButton = new JButton("Open Register Page");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterPage();
            }
        });

        // Use GridBagLayout to center buttons
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        gbc.gridx = 1;
        add(registerButton, gbc);

        // Display the main frame
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void openLoginPage() {
        // Create and display the login page
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);

        dispose();
    }

    private void openRegisterPage() {
        // Create and display the register page (you can implement this method similarly to openLoginPage)
        // For simplicity, I'm just calling openLoginPage as a placeholder
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        // Create and display the main page
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
