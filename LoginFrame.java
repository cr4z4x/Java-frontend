import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.util.Map;
import org.json.JSONObject;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the panel and adding components
        JPanel loginPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Username:");
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField userTextField = new JTextField();
        userTextField.setColumns(10);
        loginPanel.add(userTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Password:");
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setColumns(10);
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton, gbc);

        // Adding ActionListener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                // Perform login authentication (replace with your authentication logic)
                if (authenticate(username, password)) {
                    // If login successful, open the chat frame
                    dispose(); // Close the login frame
                    openChatFrame();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding Components to the frame
        getContentPane().add(loginPanel, BorderLayout.CENTER);
        setSize(400, 200); // Set a smaller initial size
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private boolean authenticate(String username, char[] password) {
        // Example server URL (replace with your server URL)
        String serverUrl = "http://localhost:8000/authenticate";

        try {
            // Create a JSON payload
            String jsonPayload = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, new String(password));

            // Create an HttpClient
            HttpClient httpClient = HttpClient.newHttpClient();

            // Create an HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response code
            if (response.statusCode() == 200) {
                // Parse the JSON response
                String jsonResponse = response.body();
                JSONObject jsonObject = new JSONObject(jsonResponse);

                // Check if the returned username matches the entered username
                if (jsonObject.has("username") && username.equals(jsonObject.getString("username"))) {
                    return true; // Authentication successful
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false; // Authentication failed
    }

    private void openChatFrame() {
        // Code to open the ChatFrame (your existing code)
        // For simplicity, I'll just create a new instance of ChatFrame
        new gui();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
