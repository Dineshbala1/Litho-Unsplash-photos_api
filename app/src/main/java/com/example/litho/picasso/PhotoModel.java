package com.example.litho.picasso;

import com.example.litho.picasso.Model.UnSplash.Photo;
import com.facebook.litho.annotations.Event;

import java.util.List;

@Event
public class PhotoModel {
    public List<Photo> photos;

    public void setPhotos(List<Photo> photos)
    {
        this.photos = photos;
    }
}
