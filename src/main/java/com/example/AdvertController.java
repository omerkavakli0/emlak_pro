package com.example;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Advert;
import model.User;

public class AdvertController {

    public static Advert advert;

    private Advert advertp;

    @FXML
    private Button btnFav;

    @FXML
    private Button btnchs;

    @FXML
    private ImageView img;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblDistrict;

    @FXML
    private Label lblStreet;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblM2;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblRoom;
    
    @FXML
    private Label lblDate;

    @FXML
    private Label lblPubName;

    @FXML
    private Label lblPubTel;

    @FXML
    private Label lblPubMail;

    DbHelper db = new DbHelper();

    public void setData(Advert advert) throws SQLException{
        User publisher = db.sqlSelectUser(advert.getPublisherID());
        lblPubName.setText(publisher.getName()+" "+publisher.getSurname());
        lblPubTel.setText(publisher.getTelNo());
        lblPubMail.setText(publisher.getMail());

        lblCategory.setText(advert.getCategoryID()==3?"House":"Workplace");
        lblCity.setText(advert.getCity());
        lblDistrict.setText(advert.getDistrict());
        lblStreet.setText(advert.getStreet());
        lblM2.setText(String.valueOf(advert.getM2()));
        lblDate.setText(advert.getDate());
        lblRoom.setText(advert.getRoomCount());
        lblPrice.setText(String.format("%,.0f ₺",(double) advert.getPrice()));
        img.setImage(advert.getImage());
        this.advertp = advert;
    }

    @FXML
    public void choose(ActionEvent event) {
        advert = advertp;
    }

    @FXML
    public void addFav(ActionEvent event) {
        try {
            DbHelper dbHelper = new DbHelper();
            dbHelper.insertFavorite(LoginController.activeUser.getID(), advertp.getAdvertID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}