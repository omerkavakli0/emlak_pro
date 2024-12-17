package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Advert;

public class PrimaryController implements Initializable{

    @FXML
    private Button btnFilter;

    @FXML
    private CheckBox cb10;

    @FXML
    private CheckBox cb11;

    @FXML
    private CheckBox cb12;

    @FXML
    private CheckBox cb13;

    @FXML
    private CheckBox cb14;

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
        cmbHCity.getItems().addAll("istanbul","ankara");
        adverts = new ArrayList<Advert>(getAdverts());
        int col = 0;
        int row = 1;
        try {
            for (Advert advert : adverts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("advert.fxml"));
                Pane pane = fxmlLoader.load();
                AdvertController advertController = fxmlLoader.getController();
                advertController.setData(advert);

                if(col == 3){
                    col = 0;
                    ++row;
                }
                gridAdverts.add(pane, col++, row);
                GridPane.setMargin(pane, new Insets(20));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            System.out.println("size "+ adverts.size());
        }
    }

    private List<Advert> getAdverts(){

        List<Advert> adverts = new ArrayList<>();
    
        Advert adv = new Advert();
        adv.setImgSrc("/imgs/mustakil-ev.jpg");
        adv.setCity("Sakarya");
        adv.setDistrict("Sapanca");
        adv.setStreet("Mila");
        adv.setAdvertID(1);
        adv.setPublisherID(1);
        adv.setCategoryID(1);
        adv.setRoomCount("7+2");
        adv.setM2(600);
        adv.setPrice(50000000L);
        adv.setDate();
        adverts.add(adv);
    
        Advert adv1 = new Advert();
        adv1.setImgSrc("/imgs/mustakil-ev.jpg");
        adv1.setCity("İstanbul");
        adv1.setDistrict("Kadıköy");
        adv1.setStreet("Moda");
        adv1.setAdvertID(1);
        adv1.setPublisherID(2);
        adv1.setCategoryID(1);
        adv1.setRoomCount("4+1");
        adv1.setM2(250);
        adv1.setPrice(7500000L);
        adv1.setDate();
        adverts.add(adv1);
    
        Advert adv2 = new Advert();
        adv2.setImgSrc("/imgs/mustakil-ev.jpg");
        adv2.setCity("Antalya");
        adv2.setDistrict("Kaş");
        adv2.setStreet("Çukurbağ");
        adv2.setAdvertID(1);
        adv2.setPublisherID(3);
        adv2.setCategoryID(1);
        adv2.setRoomCount("5+2");
        adv2.setM2(800);
        adv2.setPrice(15000000L);
        adv2.setDate();
        adverts.add(adv2);

        Advert adv3 = new Advert();
        adv3.setImgSrc("/imgs/mustakil-ev.jpg");
        adv3.setCity("Antalya");
        adv3.setDistrict("Kaş");
        adv3.setStreet("Çukurbağ");
        adv3.setAdvertID(1);
        adv3.setPublisherID(4);
        adv3.setCategoryID(1);
        adv3.setRoomCount("5+2");
        adv3.setM2(800);
        adv3.setPrice(15000000L);
        adv3.setDate();
        adverts.add(adv3);
    
        return adverts;

    }
    
    @FXML
    private List<Advert> filterAdverts(ActionEvent event){
        List<Advert> filteredAdverts = new ArrayList<Advert>();
        if(cbSaleHouse.isSelected() || cbRentHouse.isSelected() || cbWSale.isSelected() || cbWRext.isSelected()){
            //Ev için
            if (cbSaleHouse.isSelected() || cbRentHouse.isSelected()) {
                int stateID = cbSaleHouse.isSelected() ? 1 : 2;
                int categoryID = 3;
                ArrayList<Integer> countRoom = new ArrayList<>();
            
                CheckBox[] roomCheckBoxes = {cb10, cb11, cb12, cb13, cb14};
            
                for (int i = 0; i    < roomCheckBoxes.length; i++) {
                    if (roomCheckBoxes[i].isSelected()) {
                        countRoom.add(10 + i);
                    }
                }
                //min-maxes
                int ageMin = txtHAgeMin.getText().isEmpty() ? 0 : Integer.parseInt(txtHAgeMin.getText());
                int ageMax = txtHAgeMax.getText().isEmpty() ? 0 : Integer.parseInt(txtHAgeMax.getText());
                int m2Min = txtHM2Min.getText().isEmpty() ? 0 : Integer.parseInt(txtHM2Min.getText());
                int m2Max = txtHM2Max.getText().isEmpty() ? 0 : Integer.parseInt(txtHM2Max.getText());
                long priceMin = txtHPriceMin.getText().isEmpty() ? 0 : Long.parseLong(txtHPriceMin.getText());
                long priceMax = txtHPriceMax.getText().isEmpty() ? 0 : Long.parseLong(txtHPriceMax.getText());
                                
                //comboboxes
                String city = cmbHCity.getSelectionModel().isEmpty() ? null : cmbHCity.getSelectionModel().getSelectedItem();
                String district = cmbHDistrict.getSelectionModel().isEmpty() ? null : cmbHDistrict.getSelectionModel().getSelectedItem();
                String street = cmbHStreet.getSelectionModel().isEmpty() ? null : cmbHStreet.getSelectionModel().getSelectedItem();
                }
                else if (cbWSale.isSelected() || cbWRext.isSelected()) {
                int stateID = cbWSale.isSelected() ? 1 : 2;
                int categoryID = 4;
                //min-maxes
                int ageMin = txtWAgeMin.getText().isEmpty() ? 0 : Integer.parseInt(txtWAgeMin.getText());
                int ageMax = txtWAgeMax.getText().isEmpty() ? 0 : Integer.parseInt(txtWAgeMax.getText());
                int m2Min = txtWM2Min.getText().isEmpty() ? 0 : Integer.parseInt(txtWM2Min.getText());
                int m2Max = txtWM2Max.getText().isEmpty() ? 0 : Integer.parseInt(txtWM2Max.getText());
                long priceMin = txtWPriceMin.getText().isEmpty() ? 0 : Long.parseLong(txtWPriceMin.getText());
                long priceMax = txtWPriceMax.getText().isEmpty() ? 0 : Long.parseLong(txtWPriceMax.getText());
                                
                //comboboxes
                String city = cmbHCity.getSelectionModel().isEmpty() ? null : cmbWCity.getSelectionModel().getSelectedItem();
                String district = cmbHDistrict.getSelectionModel().isEmpty() ? null : cmbWDistrict.getSelectionModel().getSelectedItem();
                String street = cmbHStreet.getSelectionModel().isEmpty() ? null : cmbWStreet.getSelectionModel().getSelectedItem();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Warning!");
            alert.setContentText("Please first check a state for house or workplace.");
            alert.showAndWait();
        }
        
        


        return filteredAdverts;
    }
    

}
