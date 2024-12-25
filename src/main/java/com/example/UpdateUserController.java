package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class UpdateUserController {

    @FXML
    private Button btnSave;

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

    private User currentUser = null;
    @FXML
    public void initialize() {
        currentUser = LoginController.activeUser;
        loadUserDatas();
    }

    @FXML
    private void saveUser() {
        int id = LoginController.activeUser.getID();
        String name = txtName.getText();
        String surname = txtSurname.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String password = txtPassword.getText();


        User updatedUser = new User(id, name, surname, email, password, phone);
        try {
            DbHelper dbHelper = new DbHelper();
            dbHelper.updateUser(updatedUser);
            LoginController.activeUser = updatedUser;

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Congrats");
            alert.setHeaderText("");
            alert.setContentText("User updated succesfully.");

            alert.showAndWait();
            loadUserDatas();
            // Create a new stage
            Stage stage = new Stage();
            
            // Load the FXML file for the user profile
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
            // Set the new scene to the stage
            Scene scene = new Scene(fxmlLoader.load());
            
            // Set the scene to the stage
            stage.setScene(scene);
            
            // Optionally, set the title of the new window (user profile)
            stage.setTitle("User Profile");
            
            // Show the new window
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Stage currentStage = (Stage) btnSave.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root = loader.load();

        }catch(Exception ex){
            
        }
    }
    private void loadUserDatas(){
        txtName.setText(currentUser.getName());
        txtSurname.setText(currentUser.getSurname());
        txtEmail.setText(currentUser.getMail());
        txtPhone.setText(currentUser.getTelNo());
        txtPassword.setText(currentUser.getPassword());
        
    }
}

