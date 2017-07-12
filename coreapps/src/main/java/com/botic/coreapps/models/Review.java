
package com.botic.coreapps.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("id_object")
    @Expose
    private int idObject;
    @SerializedName("id_menu")
    @Expose
    private int idMenu;
    @SerializedName("id_user")
    @Expose
    private int idUser;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    private User user;
    public final static Creator<Review> CREATOR = new Creator<Review>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Review createFromParcel(Parcel in) {
            Review instance = new Review();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.review = ((String) in.readValue((String.class.getClassLoader())));
            instance.response = ((String) in.readValue((String.class.getClassLoader())));
            instance.rating = ((int) in.readValue((int.class.getClassLoader())));
            instance.idObject = ((int) in.readValue((int.class.getClassLoader())));
            instance.idMenu = ((int) in.readValue((int.class.getClassLoader())));
            instance.idUser = ((int) in.readValue((int.class.getClassLoader())));
            instance.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Review[] newArray(int size) {
            return (new Review[size]);
        }

    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdObject() {
        return idObject;
    }

    public void setIdObject(int idObject) {
        this.idObject = idObject;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(review);
        dest.writeValue(response);
        dest.writeValue(rating);
        dest.writeValue(idObject);
        dest.writeValue(idMenu);
        dest.writeValue(idUser);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(user);
    }

    public int describeContents() {
        return 0;
    }

}
