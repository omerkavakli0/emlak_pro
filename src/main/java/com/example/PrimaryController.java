package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Advert;

public class PrimaryController implements Initializable{

    DbHelper db = new DbHelper();
    @FXML
    private Button btnFilter;
    
    @FXML
    private Button btnExit;

    @FXML
    private CheckBox cb05;

    @FXML
    private CheckBox cb05w;

    @FXML
    private CheckBox cb10;

    @FXML
    private CheckBox cb11;

    @FXML
    private CheckBox cb1120;

    @FXML
    private CheckBox cb1120w;

    @FXML
    private CheckBox cb12;

    @FXML
    private CheckBox cb13;

    @FXML
    private CheckBox cb14;

    @FXML
    private CheckBox cb20;

    @FXML
    private CheckBox cb20w;

    @FXML
    private CheckBox cb610;

    @FXML
    private CheckBox cb610w;

    @FXML
    private CheckBox cbRentHouse;

    @FXML
    private CheckBox cbSaleHouse;

    @FXML
    private CheckBox cbWRext;

    @FXML
    private CheckBox cbWSale;

    @FXML
    private ComboBox<String> cmbHCity;

    @FXML
    private ComboBox<String> cmbHDistrict;

    @FXML
    private ComboBox<String> cmbHStreet;

    @FXML
    private ComboBox<String> cmbWCity;

    @FXML
    private ComboBox<String> cmbWDistrict;

    @FXML
    private ComboBox<String> cmbWStreet;

    @FXML
    private ComboBox<String> cmbSort;

    @FXML
    private GridPane gridAdverts;

    @FXML
    private TextField lblSearch;

    @FXML
    private Label lblUser;

    @FXML
    private TextField txtHAgeMax;

    @FXML
    private TextField txtHAgeMin;

    @FXML
    private TextField txtHM2Max;

    @FXML
    private TextField txtHM2Min;

    @FXML
    private TextField txtHPriceMax;

    @FXML
    private TextField txtHPriceMin;

    @FXML
    private TextField txtWAgeMax;

    @FXML
    private TextField txtWAgeMin;

    @FXML
    private TextField txtWM2Max;

    @FXML
    private TextField txtWM2Min;

    @FXML
    private TextField txtWPriceMax;

    @FXML
    private TextField txtWPriceMin;

    private List<Advert> adverts;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadComboBoxes();
        adverts = new ArrayList<Advert>();
        updateTopAdvertsDisplay();
        
    }
    
    @FXML
    private void filterAdverts(ActionEvent event)throws SQLException{
        List<Advert> filteredAdvertsH = new ArrayList<Advert>();
        List<Advert> filteredAdvertsW = new ArrayList<Advert>();
        
        if(cbSaleHouse.isSelected() || cbRentHouse.isSelected() || cbWSale.isSelected() || cbWRext.isSelected()){
            //Ev için
            if (cbSaleHouse.isSelected() || cbRentHouse.isSelected()) {

                ArrayList<Integer> stateID = new ArrayList<>();
                if(cbSaleHouse.isSelected())
                    stateID.add(1);
                if(cbRentHouse.isSelected())
                    stateID.add(2);

                int categoryID = 3;
                ArrayList<Integer> countRoom = new ArrayList<>();
            
                CheckBox[] roomCheckBoxes = {cb10, cb11, cb12, cb13, cb14};
            
                for (int i = 0; i    < roomCheckBoxes.length; i++) {
                    if (roomCheckBoxes[i].isSelected()) {
                        countRoom.add(10 + i);
                    }
                }
                ArrayList<Integer> age = new ArrayList<>();
                CheckBox[] ageCheckBoxes = {cb05, cb610, cb1120,cb20};

                for(int i=0;i < ageCheckBoxes.length;i++){
                    if(ageCheckBoxes[i].isSelected()){
                        age.add(15+i);
                    }
                }
                //min-maxes
                int m2Min = txtHM2Min.getText().isEmpty() ? 0 : Integer.parseInt(txtHM2Min.getText());
                int m2Max = txtHM2Max.getText().isEmpty() ? 0 : Integer.parseInt(txtHM2Max.getText());
                long priceMin = txtHPriceMin.getText().isEmpty() ? 0 : Long.parseLong(txtHPriceMin.getText());
                long priceMax = txtHPriceMax.getText().isEmpty() ? 0 : Long.parseLong(txtHPriceMax.getText());
                                
                //comboboxes
                String city = cmbHCity.getSelectionModel().isEmpty() ? null : cmbHCity.getSelectionModel().getSelectedItem();
                String district = cmbHDistrict.getSelectionModel().isEmpty() ? null : cmbHDistrict.getSelectionModel().getSelectedItem();
                String street = cmbHStreet.getSelectionModel().isEmpty() ? null : cmbHStreet.getSelectionModel().getSelectedItem();

                String sort = cmbSort.getSelectionModel().isEmpty() ? null : cmbSort.getSelectionModel().getSelectedItem();

                filteredAdvertsH = db.sqlFilterHouses(stateID, categoryID, countRoom, age, m2Min, m2Max, priceMin, priceMax, city, district, street,sort);
                }


                //işyeri için
                if (cbWSale.isSelected() || cbWRext.isSelected()) {

                ArrayList<Integer> stateID = new ArrayList<>();
                if(cbSaleHouse.isSelected())
                    stateID.add(1);
                if(cbRentHouse.isSelected())
                    stateID.add(2);

                int categoryID = 4;

                ArrayList<Integer> age = new ArrayList<>();
                CheckBox[] ageCheckBoxes = {cb05w, cb610w, cb1120w,cb20w};

                for(int i=0;i < ageCheckBoxes.length;i++){
                    if(ageCheckBoxes[i].isSelected()){
                        age.add(15+i);
                    }
                }
                //min-maxes
                int m2Min = txtWM2Min.getText().isEmpty() ? 0 : Integer.parseInt(txtWM2Min.getText());
                int m2Max = txtWM2Max.getText().isEmpty() ? 0 : Integer.parseInt(txtWM2Max.getText());
                long priceMin = txtWPriceMin.getText().isEmpty() ? 0 : Long.parseLong(txtWPriceMin.getText());
                long priceMax = txtWPriceMax.getText().isEmpty() ? 0 : Long.parseLong(txtWPriceMax.getText());
                                
                //comboboxes
                String city = cmbHCity.getSelectionModel().isEmpty() ? null : cmbWCity.getSelectionModel().getSelectedItem();
                String district = cmbHDistrict.getSelectionModel().isEmpty() ? null : cmbWDistrict.getSelectionModel().getSelectedItem();
                String street = cmbHStreet.getSelectionModel().isEmpty() ? null : cmbWStreet.getSelectionModel().getSelectedItem();

                String sort = cmbSort.getSelectionModel().isEmpty() ? null : cmbSort.getSelectionModel().getSelectedItem();

                filteredAdvertsW = db.sqlFilterWorkplace(stateID, categoryID, age, m2Min, m2Max, priceMin, priceMax, city, district, street,sort);        
            }
            adverts.clear();
            adverts.addAll(filteredAdvertsH);
            adverts.addAll(filteredAdvertsW);

            updateAdvertsDisplay();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Warning!");
            alert.setContentText("Please first check a state for house or workplace.");
            alert.showAndWait();
        }
    }
    
    private void updateAdvertsDisplay() {
        gridAdverts.getChildren().clear(); // Clear the current contents of the GridPane
    
        int col = 0;
        int row = 1;
    
        try {
            for (Advert advert : adverts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("advert.fxml"));
                Pane pane = fxmlLoader.load();
                AdvertController advertController = fxmlLoader.getController();
                advertController.setData(advert);
    
                if (col == 2) {
                    col = 0;
                    ++row;
                }
                gridAdverts.add(pane, col++, row);
                GridPane.setMargin(pane, new Insets(20));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("size " + adverts.size());
        }
    }

    private void updateTopAdvertsDisplay(){
        ArrayList<Advert> topAdverts = null;
        gridAdverts.getChildren().clear(); // Clear the current contents of the GridPane
    
        int col = 0;
        int row = 1;
    
        try {
            topAdverts = db.sqlSelectAdverts();
            for (Advert advert : topAdverts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("advert.fxml"));
                Pane pane = fxmlLoader.load();
                AdvertController advertController = fxmlLoader.getController();
                advertController.setData(advert);
    
                if (col == 2) {
                    col = 0;
                    ++row;
                }
                gridAdverts.add(pane, col++, row);
                GridPane.setMargin(pane, new Insets(20));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("size " + topAdverts.size());
        }
    }

    @FXML
    private void toUserPanel(ActionEvent event) throws IOException {
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
    }

    private void loadComboBoxes(){

        ArrayList<String> cities = null;
        ArrayList<String> districts = null;
        ArrayList<String> streets = null;

        try {
            cmbSort.getItems().addAll("Price: Low to High","Price: High to Low");

            cities = db.sqlDistinctCity();
            districts = db.sqlDistinctDistrict();
            streets = db.sqlDistinctStreet();

            cmbHCity.getItems().addAll(cities);
            cmbWCity.getItems().addAll(cities);

            cmbHDistrict.getItems().addAll(districts);
            cmbWDistrict.getItems().addAll(districts);

            cmbHStreet.getItems().addAll(streets);
            cmbWStreet.getItems().addAll(streets);

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @FXML
    private void exit(ActionEvent event) throws IOException{
        Stage curStage = (Stage) btnExit.getScene().getWindow();
        curStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent registerRoot = fxmlLoader.load();
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(registerRoot));
        loginStage.show();

    }
}
