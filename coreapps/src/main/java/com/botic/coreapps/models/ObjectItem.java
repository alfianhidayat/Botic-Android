
package com.botic.coreapps.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ObjectItem {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("category")
    private Category mCategory;
    @SerializedName("close")
    private String mClose;
    @SerializedName("created_at")
    private Object mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private Long mId;
    @SerializedName("id_category")
    private Long mIdCategory;
    @SerializedName("id_menu")
    private Long mIdMenu;
    @SerializedName("lat")
    private Long mLat;
    @SerializedName("lng")
    private Long mLng;
    @SerializedName("manager")
    private String mManager;
    @SerializedName("menu")
    private Menu mMenu;
    @SerializedName("name")
    private String mName;
    @SerializedName("open")
    private String mOpen;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("picture")
    private List<Object> mPicture;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("updated_at")
    private Object mUpdatedAt;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public String getClose() {
        return mClose;
    }

    public void setClose(String close) {
        mClose = close;
    }

    public Object getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Object createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(Long createdBy) {
        mCreatedBy = createdBy;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getIdCategory() {
        return mIdCategory;
    }

    public void setIdCategory(Long idCategory) {
        mIdCategory = idCategory;
    }

    public Long getIdMenu() {
        return mIdMenu;
    }

    public void setIdMenu(Long idMenu) {
        mIdMenu = idMenu;
    }

    public Long getLat() {
        return mLat;
    }

    public void setLat(Long lat) {
        mLat = lat;
    }

    public Long getLng() {
        return mLng;
    }

    public void setLng(Long lng) {
        mLng = lng;
    }

    public String getManager() {
        return mManager;
    }

    public void setManager(String manager) {
        mManager = manager;
    }

    public Menu getMenu() {
        return mMenu;
    }

    public void setMenu(Menu menu) {
        mMenu = menu;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOpen() {
        return mOpen;
    }

    public void setOpen(String open) {
        mOpen = open;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public List<Object> getPicture() {
        return mPicture;
    }

    public void setPicture(List<Object> picture) {
        mPicture = picture;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public Object getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
