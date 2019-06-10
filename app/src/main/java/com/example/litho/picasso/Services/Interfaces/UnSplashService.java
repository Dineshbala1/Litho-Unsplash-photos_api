package com.example.litho.picasso.Services.Interfaces;

import com.example.litho.picasso.Model.UnSplash.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UnSplashService {

    @GET("photos/")
    Call<List<Photo>> getImagesList(@Query("client_id") String clientSecret, @Query("page") int pageNumber,@Query("per_page") int perPageItemCount);

    @GET("search/photos/")
    Call<SearchResultModel<Photo>> getSearchPhotoResultList(@Query("client_id") String clientSecret,@Query("query") String searchKeyword,@Query("page") int pageNumber,@Query("per_page") int perPageItemCount);

    @GET("collections/")
    Call<List<Collection>> getCollections(@Query("client_id") String clientSecret);
}
