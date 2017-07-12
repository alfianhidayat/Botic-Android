package com.example.amrizalns.botic;

/**
 * Created by amrizalns on 6/7/17.
 */

public class itemObject {
    private int id;
    private String location;
    private String name;
    private int img;
    private String desc;
    private String cost;
    private String time_open;
    private String time_close;

    public itemObject(int id, int img, String name, String location, String cost, String time_open, String time_close, String desc) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.img = img;
        this.desc = desc;
        this.cost = cost;
        this.time_open = time_open;
        this.time_close = time_close;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTime_open() {
        return time_open;
    }

    public void setTime_open(String time_open) {
        this.time_open = time_open;
    }

    public String getTime_close() {
        return time_close;
    }

    public void setTime_close(String time_close) {
        this.time_close = time_close;
    }
}
