package com.berru.app.atmjfx.controller;

import com.berru.app.atmjfx.dao.UserDAO;
import com.berru.app.atmjfx.dto.UserDTO;
import com.berru.app.atmjfx.utils.ERole;
import com.berru.app.atmjfx.utils.FXMLPath;
import com.berru.app.atmjfx.utils.SceneHelper;
import com.berru.app.atmjfx.utils.SpecialColor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.Optional;

public class RegisterController {
    private UserDAO userDAO;

    public RegisterController() {
        userDAO = new UserDAO();
    }

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void specialOnEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            register();
        }
    }

    @FXML
    public void register() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String email = emailField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            showAlert("Error", "Please fill in all the fields", Alert.AlertType.ERROR);
            return;
        }

        // Username check
        if (userDAO.isUsernameExists(username)) {
            showAlert("Error", "This username is already registered!", Alert.AlertType.WARNING);
            return;
        }

        // Email check
        if (userDAO.isEmailExists(email)) {
            showAlert("Error", "This email address is already registered!", Alert.AlertType.WARNING);
            return;
        }

        UserDTO userDTO = UserDTO.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(ERole.USER) // enum value
                .build();

        Optional<UserDTO> createdUser = userDAO.create(userDTO);
        if (createdUser.isPresent()) {
            showAlert("Success", "Registration successful", Alert.AlertType.INFORMATION);
            switchToLoginPane();
        } else {
            showAlert("Error", "Registration failed", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void switchToLoginPane() {
        try {
            //METHOD 1
            /*
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPath.LOGIN));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.setTitle("Log In");
            stage.show();
             */
            //METHOD 2
            SceneHelper.switchScene(FXMLPath.LOGIN, usernameField, "Log In");
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "Failed to redirect to the Login Page" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Error", "Login screen could not be loaded", Alert.AlertType.ERROR);
        }
    }
}
