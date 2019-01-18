package com.example.khanh.melody.HomeDetails;

public class Items {
    public Items(String id, int icon, String name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    private String id;
    private int icon;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Items() {

    }
}
