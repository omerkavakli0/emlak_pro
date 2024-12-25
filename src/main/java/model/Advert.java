package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.Image;

public class Advert {
    // Özellikler
    private int PublisherID;
    private int StateID;
    private int ID;
    private int CategoryID;
    private int M2;
    private String Age;
    private String Date;
    private String City;
    private String District;
    private String Street;
    private String RoomCount;
    private long Price;
    private String ImageName;
    private Image Image;

    // Parametresiz Constructor
    public Advert() {
    }

    // Parametreli Constructor
    public Advert(String imageName,Image image,int stateID, int publisherID, int id, int categoryID, int m2,String date, String city, String district, String street, String roomCount, long price,String age) {
        this.ImageName = imageName;
        this.Image = image;
        this.StateID = stateID;
        this.PublisherID = publisherID;
        this.ID = id;
        this.CategoryID = categoryID;
        this.M2 = m2;
        this.Age = age;
        this.City = city;
        this.District = district;
        this.Street = street;
        this.RoomCount = roomCount;
        this.Price = price;
        this.Date=date;
    }

    // Getter ve Setter Metotları
    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        this.ImageName = imageName;
    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        this.Image = image;
    }

    public int getPublisherID() {
        return PublisherID;
    }

    public void setPublisherID(int publisherID) {
        this.PublisherID = publisherID;
    }

    public int getStateID() {
        return StateID;
    }

    public void setStateID(int stateID) {
        this.StateID = stateID;
    }

    public int getAdvertID() {
        return ID;
    }

    public void setAdvertID(int advertID) {
        this.ID = advertID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        this.CategoryID = categoryID;
    }

    public int getM2() {
        return M2;
    }

    public void setM2(int m2) {
        this.M2 = m2;
    }

    public String getDate() {
        return Date;
    }

    public void setDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.Date = LocalDate.now().format(formatter); // Formatlanmış String atanıyor.
    }

    public void setDate(String date){
        this.Date = date;
    }
    
    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        this.Age = age;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        this.District = district;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        this.Street = street;
    }

    public String getRoomCount() {
        return RoomCount;
    }

    public void setRoomCount(String roomCount) {
        this.RoomCount = roomCount;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        this.Price = price;
    }
}

