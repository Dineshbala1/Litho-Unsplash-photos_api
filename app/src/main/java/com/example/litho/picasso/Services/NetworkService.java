package com.example.litho.picasso.Services;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.litho.picasso.Model.UnSplash.Photo;
import com.example.litho.picasso.Model.UnSplash.SearchResultModel;
import com.example.litho.picasso.R;
import com.example.litho.picasso.Services.Interfaces.UnSplashService;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private UnSplashService splashService;
    private Context _context;

    public NetworkService(Context context) {

        _context = context;
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.unsplash.com/").addConverterFactory(GsonConverterFactory.create()).build();

        splashService = retrofit.create(UnSplashService.class);
    }

    public void getPhotos(int pageNumber, int perPageCount, Callback resultCallback) {
        Call<List<Photo>> call = splashService.getImagesList(_context.getString(R.string.splash_client_id), pageNumber, perPageCount);
        call.enqueue(resultCallback);
    }

    public void getNewPhotos(String searchKeyword, int pageNumber, int perPageCount, Callback resultCallback) {
        Call<SearchResultModel<Photo>> call = splashService.getSearchPhotoResultList(_context.getString(R.string.splash_client_id), searchKeyword, pageNumber, perPageCount);
        call.enqueue(resultCallback);
    }
}
