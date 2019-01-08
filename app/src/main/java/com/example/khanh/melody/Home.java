package com.example.khanh.melody;

public class Home {
    private String price;
    private String description;
    private String urlImg;
    private String homeId;

    public Home() {

    }

    public Home(String homeId, String price, String description, String urlImg) {
        this.homeId = homeId;
        this.price = price;
        this.price = description;
        this.price = urlImg;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public Home(String homeId) {

        this.homeId = homeId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
