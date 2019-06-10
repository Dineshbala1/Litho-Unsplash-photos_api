package com.example.litho.picasso.Model.UnSplash;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResultModel<T> implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public final static Parcelable.Creator<SearchResultModel> CREATOR = new Creator<SearchResultModel>() {

        @SuppressWarnings({"unchecked"})
        public SearchResultModel createFromParcel(Parcel in) {
            return new SearchResultModel(in);
        }

        public SearchResultModel[] newArray(int size) {
            return (new SearchResultModel[size]);
        }

    };

    public SearchResultModel(Parcel in) {
        TotalItems = ((int) in.readValue((String.class.getClassLoader())));
        TotalPages = ((int) in.readValue((String.class.getClassLoader())));
        SearchResult = ((List<T>) in.readValue((String.class.getClassLoader())));
    }

    @SerializedName("total")
    @Expose
    private int TotalItems;

    @SerializedName("total_pages")
    @Expose
    private int TotalPages;

    @SerializedName("results")
    @Expose
    private List<T> SearchResult;

    public int getTotalItems() {
        return this.TotalItems;
    }

    public void setTotalItems(int totalItems) {
        this.TotalItems = totalItems;
    }

    public int getTotalpages() {
        return this.TotalPages;
    }

    public void setTotalPages(int totalPages) {
        this.TotalPages = totalPages;
    }

    public List<T> getSearchResult() {
        return this.SearchResult;
    }

    public void setSearchResult(List<T> result) {
        this.SearchResult = result;
    }
}
