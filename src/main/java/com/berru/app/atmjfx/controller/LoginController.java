package com.berru.app.atmjfx.controller;

import com.berru.app.atmjfx.dao.UserDAO;
import com.berru.app.atmjfx.dto.UserDTO;
import com.berru.app.atmjfx.utils.ERole;
import com.berru.app.atmjfx.utils.FXMLPath;
import com.berru.app.atmjfx.utils.SceneHelper;
import com.berru.app.atmjfx.utils.SpecialColor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;


public class LoginController {
    // Injection

    // Field
    private UserDAO userDAO;

    // Parameterless Constructor
    public LoginController() {
        userDAO = new UserDAO();
    }

    ///////////////////////////////////////
    /// FXML Field
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void login() {
        String username = this.usernameField.getText().trim();
        String password = this.passwordField.getText().trim();

        Optional<UserDTO> optionalLoginUserDTO = userDAO.loginUser(username, password);

        if (optionalLoginUserDTO.isPresent()) {
            UserDTO userDTO = optionalLoginUserDTO.get();
            showAlert("Success", "Login Successful: " + userDTO.getUsername(), Alert.AlertType.INFORMATION);

            if (userDTO.getRole() == ERole.ADMIN) {
                openAdminPane();
            } else {
                openUserHomePane();
            }
        } else {
            showAlert("Failed", "Login credentials are incorrect", Alert.AlertType.ERROR);
        }
    }

    private void openAdminPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPath.ADMIN));
            Parent parent = fxmlLoader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.setTitle("Admin Panel");
            stage.show();
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "Failed to redirect to the Admin Page" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Error", "Failed to load the Admin screen", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void switchToRegister(ActionEvent actionEvent) {
        try {
            // METHOD 1
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPath.REGISTER));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.setTitle("Sign Up");
        stage.show();
        */

            // METHOD 2
            SceneHelper.switchScene(FXMLPath.REGISTER, usernameField, "Sign Up");
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "Failed to redirect to the Register Page" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Error", "Failed to load the Register screen", Alert.AlertType.ERROR);
        }
    }

}