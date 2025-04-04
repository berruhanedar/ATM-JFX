package com.berru.app.atmjfx.controller;

import com.berru.app.atmjfx.dao.UserDAO;
import com.berru.app.atmjfx.dto.UserDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Optional;

import static com.berru.app.atmjfx.utils.Session.currentUser;

public class ProfileController {

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField oldPasswordField;

    public void updateUserProfile(UserDTO user) {
        // Fetch updated user profile from database
        System.out.println("UserProfileController#updateUserProfile received user: " + user);
        Optional<UserDTO> dbUser = fetchUserProfile(user.getId());

        if (dbUser.isPresent()) {
            UserDTO updatedUser = dbUser.get();
            usernameLabel.setText(updatedUser.getUsername());
            emailLabel.setText(updatedUser.getEmail());
            roleLabel.setText(updatedUser.getRole().toString());
        } else {
            usernameLabel.setText(user.getUsername());
            emailLabel.setText(user.getEmail());
            roleLabel.setText(user.getRole().toString());
        }
    }

    @FXML
    public void updatePassword() throws SQLException {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        updateUserProfile(currentUser);

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("Warning: Password fields cannot be empty.");
            return;
        }

        // Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Warning: New passwords do not match!");
            return;
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        currentUser.setPassword(hashedPassword);
        System.out.println("Password updated successfully.");
        System.out.println("🟢 User to be updated: " + currentUser.getUsername());
        System.out.println("🟢 New hashed password: " + hashedPassword);

        System.out.println("Success: Password changed successfully.");

        userDAO.updatePassword(currentUser);

        closeWindow();
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) usernameLabel.getScene().getWindow();
        stage.close();
    }

    public Optional<UserDTO> fetchUserProfile(int userId) {
        return userDAO.findById(userId);
    }

}
