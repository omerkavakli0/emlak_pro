package model;

import java.util.List;

public class User {
    // Özellikler
    private int ID;
    private String Name;
    private String Surname;
    private String Mail;
    private String Password;
    private String TelNo;
    private List<Advert> MyAdverts;
    private List<Advert> Favorites;

    // Parametresiz Constructor
    public User() {
    }

    // Parametreli Constructor
    public User(int id,String name, String surname, String mail, String password, String telNo) {
        this.ID = id;
        this.Name = name;
        this.Surname = surname;
        this.Mail = mail;
        this.Password = password;
        this.TelNo = telNo;
    }

    // Getter ve Setter Metodları
    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        this.Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getTelNo() {
        return TelNo;
    }

    public void setTelNo(String telNo) {
        this.TelNo = telNo;
    }

    public List<Advert> getMyAdverts() {
        return MyAdverts;
    }

    public void setMyAdverts(List<Advert> myAdverts) {
        this.MyAdverts = myAdverts;
    }

    public List<Advert> getFavorites() {
        return Favorites;
    }

    public void setFavorites(List<Advert> favorites) {
        this.Favorites = favorites;
    }
}

