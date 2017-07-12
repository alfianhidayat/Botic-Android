
package com.botic.coreapps.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("menu")
    @Expose
    private String menu;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    public final static Creator<Menu> CREATOR = new Creator<Menu>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Menu createFromParcel(Parcel in) {
            Menu instance = new Menu();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.menu = ((String) in.readValue((String.class.getClassLoader())));
            instance.icon = ((String) in.readValue((String.class.getClassLoader())));
            instance.createdAt = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.updatedAt = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public Menu[] newArray(int size) {
            return (new Menu[size]);
        }

    }
    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(menu);
        dest.writeValue(icon);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return  0;
    }

}
