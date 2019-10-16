package com.osman.cloudfirestoretask.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Car {

    private String name;
    private String city;
    private String category;
    private String photo;
    private int price;
    private String publisherName;
    private String publicationDate;
    private String id;

    public Car() {
    }

    public Car(String name, String city, String category, String photo,
               int price, String publisherName, String id, String publicationDate) {
        this.name = name;
        this.city = city;
        this.category = category;
        this.price = price;
        this.publisherName = publisherName;
        this.id = id;
        this.publicationDate = publicationDate;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}
