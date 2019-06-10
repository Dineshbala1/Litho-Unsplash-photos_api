package com.example.litho.picasso.Services;

import android.os.Handler;

import com.example.litho.picasso.Application;
import com.example.litho.picasso.Model.UnSplash.Photo;
import com.example.litho.picasso.Model.UnSplash.SearchResultModel;
import com.example.litho.picasso.PhotoModel;
import com.example.litho.picasso.Utilities;
import com.facebook.litho.EventHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.litho.picasso.Utilities.GetAssetJsonData;

public class PhotoDataService {

    private boolean Initialized = false;

    private EventHandler<PhotoModel> PhotoDataEventHandler = null;

    public void RegisterPhotoDataEventHandler(EventHandler<PhotoModel> photoDataEventHandler) {
        this.PhotoDataEventHandler = photoDataEventHandler;
    }

    public void UnRegisterPhotoDataEventHandler() {
        this.PhotoDataEventHandler = null;
    }

    public void FetchPhotos(final int pageNumber, final  int perPageItem) {
        final NetworkService service = new NetworkService(Application.getContext());
        service.getNewPhotos("New",pageNumber, perPageItem, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                SearchResultModel<Photo> result = (SearchResultModel<Photo>) response.body();
                PhotoModel model = new PhotoModel();
                model.setPhotos(result.getSearchResult());
                PhotoDataService.this.PhotoDataEventHandler.dispatchEvent(model);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                PhotoDataService.this.PhotoDataEventHandler.dispatchEvent(null);
            }
        });
    }

    public boolean getInitialized(){
        return this.Initialized;
    }

    public void getPhotos(int pageNumber, int perPageItem, final Callback responseCallback) {
        final NetworkService service = new NetworkService(Application.getContext());
        service.getNewPhotos("new", pageNumber, perPageItem, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                SearchResultModel<Photo> result = (SearchResultModel<Photo>) response.body();
                PhotoModel model = new PhotoModel();
                model.setPhotos(result.getSearchResult());
                PhotoDataService.this.PhotoDataEventHandler.dispatchEvent(model);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                PhotoDataService.this.PhotoDataEventHandler.dispatchEvent(null);
            }
        });
        Initialized = true;
    }
}
