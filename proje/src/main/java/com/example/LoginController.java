package com.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtMail;

    public static User activeUser;
    
    @FXML
    void loginButton(ActionEvent event) throws SQLException{
        //checking is areas empty
        if (txtMail.getText().isEmpty() || txtPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Be careful!");
            alert.setContentText("Username and password areas should be filled.");
            alert.showAndWait();
        }
        else {
            //checking is this user in database
            DbHelper db = new DbHelper();
            User loginUser = new User();                         
            loginUser = db.sqlCheckUser(txtMail.getText(), txtPassword.getText());
            if (loginUser == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Ooops!");
                alert.setContentText("User couldn't be found.");

                alert.showAndWait();
            }
            else{
                // login
                activeUser = loginUser;
                toMainStage();
            }
        }
    }

    void toMainStage(){
        //Closing CurrentScene
        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        currentStage.close();

        //Opening MainPanel Scene
        Stage stage = new Stage();
        Scene scene = null;
        FXMLLoader fxmlLoader = new FXMLLoader(PrimaryController.class.getResource("primary.fxml"));

        try{
            scene = new Scene((Parent) fxmlLoader.load());
        }
        catch (IOException var6){
            System.out.println(var6);
        }
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }
    
}
