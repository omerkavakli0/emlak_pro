package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Advert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddAdvertController {

    @FXML
    private ComboBox<String> comboCategory;

    @FXML
    private ComboBox<String> comboState;

    @FXML
    private ComboBox<String> comboRoomCount;

    @FXML
    private ComboBox<String> comboAge;

    @FXML
    private TextField txtSquareMeters;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtDistrict;

    @FXML
    private TextField txtStreet;

    @FXML
    private Button btnImage;

    @FXML
    private ImageView imgImage;

    @FXML
    private Button btnCreate;

    private File file;

    // SQL Connection Variables
    Connection connection = null;
    PreparedStatement statement = null;
    DbHelper db = new DbHelper();

    @FXML
    public void initialize() {
        // Example data for ComboBoxes
        comboCategory.getItems().addAll("House",  "WorkPlace");
        comboState.getItems().addAll("For Sale", "For Rent");
        comboRoomCount.getItems().addAll( "1+0", "1+1", "2+1", "3+1", "4+1");
        comboAge.getItems().addAll("0-5 years", "5-10 years", "10-20 years", "20+ years");
    }

    @FXML
    public void addImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            this.file = selectedFile;
            Image image = new Image(new FileInputStream(selectedFile));
            imgImage.setImage(image);
        }
    }
    public String setDate() {
        String Date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date = LocalDate.now().format(formatter); // Formatlanmış String atanıyor.
        return  Date;
    }
    @FXML
    public void addAdvert(ActionEvent event) throws SQLException {
        try {
            connection = db.getConnection();
            String sql = "INSERT INTO advert (categoryID, stateID, roomCountID, ageID, m2, price, city, district, street, image, imageName,userID,advertDate)" +
                    " VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            statement = connection.prepareStatement(sql);

            // Get form data
            statement.setInt(1, "House".equals(comboCategory.getValue()) ? 3 : "WorkPlace".equals(comboCategory.getValue()) ? 4 : 0);
            statement.setInt(2, "For Sale".equals(comboState.getValue()) ? 1 : "For Rent".equals(comboState.getValue()) ? 2 : 0);
            int roomValue;
            switch (comboRoomCount.getValue()) {
                case "1+0":
                    roomValue = 10;
                    break;
                case "1+1":
                    roomValue = 11;
                    break;
                case "2+1":
                    roomValue = 12;
                    break;
                case "3+1":
                    roomValue = 13;
                    break;
                case "4+1":
                    roomValue = 14;
                    break;
                default:
                    roomValue = 0; // Geçersiz veya boş değer için varsayılan
                    break;
            }
            statement.setInt(3, roomValue);
            int ageValue;
            switch (comboAge.getValue()) {
                case "0-5 years":
                    ageValue = 15;
                    break;
                case "5-10 years":
                    ageValue = 16;
                    break;
                case "10-20 years":
                    ageValue = 17;
                    break;
                case "20+ years":
                    ageValue = 18;
                    break;
                default:
                    ageValue = 0;
                    break;
            }
            System.out.println(ageValue);
            statement.setInt(4, ageValue);
            statement.setString(5, txtSquareMeters.getText());
            statement.setString(6, txtPrice.getText());
            statement.setString(7, txtCity.getText());
            statement.setString(8, txtDistrict.getText());
            statement.setString(9, txtStreet.getText());
            statement.setBlob(10, new FileInputStream(file), file.length());
            statement.setString(11, file.getName());
            statement.setInt(12, LoginController.activeUser.getID());
            statement.setString(13, setDate());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Advert Created Successfully");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Create Advert");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.out.println(e);
        }

        try {
            Stage currentStage = (Stage) btnCreate.getScene().getWindow();
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
