package com.example;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

public class RegisterController {

    @FXML
    private Button btnRegister;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtPhone;

    @FXML
    void registerButton(ActionEvent event) throws SQLException {
        String name = txtName.getText();
        String surname = txtSurname.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String phone = txtPhone.getText();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            showAlert("Warning", "All fields must be filled!", Alert.AlertType.WARNING);
            return;
        }

        User newUser = new User(150, name, surname, email, password, phone);
        DbHelper db = new DbHelper();
        int result = db.sqlInsertUser(newUser);

        if (result > 0) {
            showAlert("Success", "User registered successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Failed to register user.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
