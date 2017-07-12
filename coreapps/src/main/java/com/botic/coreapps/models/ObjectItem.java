
package com.botic.coreapps.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectItem implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("manager")
    @Expose
    private String manager;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("lat")
    @Expose
    private int lat;
    @SerializedName("lng")
    @Expose
    private int lng;
    @SerializedName("open")
    @Expose
    private String open;
    @SerializedName("close")
    @Expose
    private String close;
    @SerializedName("id_category")
    @Expose
    private int idCategory;
    @SerializedName("id_menu")
    @Expose
    private int idMenu;
    @SerializedName("created_by")
    @Expose
    private int createdBy;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("picture")
    @Expose
    private List<Object> picture = null;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("menu")
    @Expose
    private Menu menu;
    public final static Creator<ObjectItem> CREATOR = new Creator<ObjectItem>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ObjectItem createFromParcel(Parcel in) {
            ObjectItem instance = new ObjectItem();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((String) in.readValue((String.class.getClassLoader())));
            instance.phone = ((String) in.readValue((String.class.getClassLoader())));
            instance.manager = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.price = ((String) in.readValue((String.class.getClassLoader())));
            instance.lat = ((int) in.readValue((int.class.getClassLoader())));
            instance.lng = ((int) in.readValue((int.class.getClassLoader())));
            instance.open = ((String) in.readValue((String.class.getClassLoader())));
            instance.close = ((String) in.readValue((String.class.getClassLoader())));
            instance.idCategory = ((int) in.readValue((int.class.getClassLoader())));
            instance.idMenu = ((int) in.readValue((int.class.getClassLoader())));
            instance.createdBy = ((int) in.readValue((int.class.getClassLoader())));
            instance.rating = ((int) in.readValue((int.class.getClassLoader())));
            instance.createdAt = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.updatedAt = ((Object) in.readValue((Object.class.getClassLoader())));
            in.readList(instance.picture, (Object.class.getClassLoader()));
            instance.category = ((Category) in.readValue((Category.class.getClassLoader())));
            instance.menu = ((Menu) in.readValue((Menu.class.getClassLoader())));
            return instance;
        }

        public ObjectItem[] newArray(int size) {
            return (new ObjectItem[size]);
        }

    }
    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Object> getPicture() {
        return picture;
    }

    public void setPicture(List<Object> picture) {
        this.picture = picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(address);
        dest.writeValue(phone);
        dest.writeValue(manager);
        dest.writeValue(description);
        dest.writeValue(price);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(open);
        dest.writeValue(close);
        dest.writeValue(idCategory);
        dest.writeValue(idMenu);
        dest.writeValue(createdBy);
        dest.writeValue(rating);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeList(picture);
        dest.writeValue(category);
        dest.writeValue(menu);
    }

    public int describeContents() {
        return  0;
    }

}
