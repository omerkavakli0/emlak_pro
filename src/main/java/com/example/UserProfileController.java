package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Advert;
import model.User;

import java.io.IOException;
import java.util.List;
import javafx.geometry.Insets;


public class UserProfileController {

    @FXML
    private Label lblName;

    @FXML
    private Label lblSurname;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPhone;

    @FXML
    private ListView<String> listFavorites;

    @FXML
    private ListView<String> listMyAdverts;

    @FXML
    private Button btnUpdateAdvert;

    @FXML
    private Label lblLoggedInUser;

    @FXML
    private GridPane gridFavorites;

    @FXML
    private GridPane gridMyAdverts;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private Button btnDeleteFavorite;

    @FXML
    private Button btnAddAdvert;

    private List<Advert> favoriteAdverts;
    private List<Advert> myAdverts;

    public void initialize() {
        // Giriş yapan kullanıcının bilgilerini yükleme
        User activeUser = LoginController.activeUser;
        if (activeUser != null) {
            lblName.setText(activeUser.getName());
            lblSurname.setText(activeUser.getSurname());
            lblEmail.setText(activeUser.getMail());
            lblPhone.setText(activeUser.getTelNo());
            lblLoggedInUser.setText(activeUser.getName() + " " + activeUser.getSurname());

            // Kullanıcının favori ilanlarını ve kendi ilanlarını yükleme
            loadFavorites();
            loadMyAdverts();
        }
    }

    private void loadFavorites() {
        try {
            DbHelper dbHelper = new DbHelper();
            favoriteAdverts = dbHelper.getFavoriteAdverts(LoginController.activeUser.getID()); // Örnek userID, dinamik hale getirin
            int col = 0;
            int row = 1;

            for (Advert advert : favoriteAdverts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("advert.fxml"));
                Pane pane = fxmlLoader.load();

                AdvertController advertController = fxmlLoader.getController();
                advertController.setData(advert);

                if (col == 1) {
                    col = 0;
                    ++row;
                }
                gridFavorites.add(pane, col++, row);
                GridPane.setMargin(pane, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMyAdverts() {
        try {
            DbHelper dbHelper = new DbHelper();
            myAdverts = dbHelper.getMyAdverts(LoginController.activeUser.getID());
            int col = 0;
            int row = 1;

            for (Advert advert : myAdverts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("advert.fxml"));
                Pane pane = fxmlLoader.load();

                AdvertController advertController = fxmlLoader.getController();
                advertController.setData(advert);

                if (col == 1) {
                    col = 0;
                    ++row;
                }

                gridMyAdverts.add(pane, col++, row);
                GridPane.setMargin(pane, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddAdvert() {
        try {
            Stage currentStage = (Stage) btnAddAdvert.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addAdvert.fxml"));
            Parent addAdvertRoot = fxmlLoader.load();

            Stage addAdvertStage = new Stage();
            addAdvertStage.setTitle("Add Advert");
            addAdvertStage.setScene(new Scene(addAdvertRoot));
            addAdvertStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleUpdateUser() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateUser.fxml"));
            Parent updateUserRoot = fxmlLoader.load();

            Stage updateUserStage = new Stage();
            updateUserStage.setTitle("Update User");
            updateUserStage.setScene(new Scene(updateUserRoot));
            updateUserStage.show();
            Stage curStage = (Stage)btnAddAdvert.getScene().getWindow();
            curStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateAdvert() {
        try {
            Stage currentStage = (Stage) btnUpdateAdvert.getScene().getWindow();
            currentStage.close();


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateAdvert.fxml"));
            Parent updateAdvertRoot = fxmlLoader.load();

            Stage updateAdvertStage = new Stage();
            updateAdvertStage.setTitle("Update Advert");
            updateAdvertStage.setScene(new Scene(updateAdvertRoot));
            updateAdvertStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteFav() {
        int userID = LoginController.activeUser.getID();
        int advertId = AdvertController.advert.getAdvertID();
        try {
            DbHelper dbHelper = new DbHelper();
            dbHelper.deleteFavorite(userID, advertId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Stage currentStage = (Stage) btnDeleteFavorite.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("User Profile");
            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}