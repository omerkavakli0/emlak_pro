package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Advert;

public class AdvertController {

    @FXML
    private Button btnFav;

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

    public void setData(Advert advert){
        Image image = new Image(getClass().getResourceAsStream(advert.getImgSrc()));
        img.setImage(image);
        lblCategory.setText(advert.getCategoryID()==1?"House":"Workplace");
        lblCity.setText(advert.getCity());
        lblDistrict.setText(advert.getDistrict());
        lblStreet.setText(advert.getStreet());
        lblM2.setText(String.valueOf(advert.getM2()));
        lblDate.setText(advert.getDate());
        lblRoom.setText(advert.getRoomCount());
        lblPrice.setText(String.format("%,.0f ₺", advert.getPrice()));
    }
}