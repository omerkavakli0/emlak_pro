package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Advert {
    // Özellikler
    private String ImgSrc;
    private int PublisherID;
    private int ID;
    private int CategoryID;
    private int M2;
    private String Date;
    private String City;
    private String District;
    private String Street;
    private String RoomCount;
    private long Price;

    // Parametresiz Constructor
    public Advert() {
    }

    // Parametreli Constructor
    public Advert(String imgSrc, int publisherID, int id, int categoryID, int m2,String date, String city, String district, String street, String roomCount, long price) {
        this.ImgSrc = imgSrc;
        this.PublisherID = publisherID;
        this.ID = id;
        this.CategoryID = categoryID;
        this.M2 = m2;
        this.City = city;
        this.District = district;
        this.Street = street;
        this.RoomCount = roomCount;
        this.Price = price;
        this.Date=date;
    }

    // Getter ve Setter Metotları
    public String getImgSrc() {
        return ImgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.ImgSrc = imgSrc;
    }

    public int getPublisherID() {
        return PublisherID;
    }

    public void setPublisherID(int publisherID) {
        this.PublisherID = publisherID;
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

