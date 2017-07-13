
package com.botic.coreapps.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("mime")
    @Expose
    private String mime;
    @SerializedName("original_filename")
    @Expose
    private String originalFilename;
    @SerializedName("id_object")
    @Expose
    private int idObject;
    @SerializedName("id_menu")
    @Expose
    private int idMenu;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    public final static Creator<Picture> CREATOR = new Creator<Picture>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Picture createFromParcel(Parcel in) {
            Picture instance = new Picture();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.filename = ((String) in.readValue((String.class.getClassLoader())));
            instance.mime = ((String) in.readValue((String.class.getClassLoader())));
            instance.originalFilename = ((String) in.readValue((String.class.getClassLoader())));
            instance.idObject = ((int) in.readValue((int.class.getClassLoader())));
            instance.idMenu = ((int) in.readValue((int.class.getClassLoader())));
            instance.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Picture[] newArray(int size) {
            return (new Picture[size]);
        }

    }
    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
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

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
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
        dest.writeValue(filename);
        dest.writeValue(mime);
        dest.writeValue(originalFilename);
        dest.writeValue(idObject);
        dest.writeValue(idMenu);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return  0;
    }

}
