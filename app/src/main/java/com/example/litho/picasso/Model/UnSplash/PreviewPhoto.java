package com.example.litho.picasso.Model.UnSplash;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviewPhoto implements Parcelable {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("urls")
    @Expose
    private Urls urls;

    public final static Parcelable.Creator<PreviewPhoto> CREATOR = new Creator<PreviewPhoto>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PreviewPhoto createFromParcel(Parcel in) {
            PreviewPhoto instance = new PreviewPhoto();
            instance.id = ((String) in.readValue((User.class.getClassLoader())));
            instance.urls = ((Urls) in.readValue((Urls.class.getClassLoader())));
            return instance;
        }

        public PreviewPhoto[] newArray(int size) {
            return (new PreviewPhoto[size]);
        }

    };

    public String getId() {
        return id;
    }

    public void setUser(String id) {
        this.id = id;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(urls);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
