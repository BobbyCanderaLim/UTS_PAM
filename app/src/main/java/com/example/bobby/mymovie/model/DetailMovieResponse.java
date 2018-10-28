package com.example.bobby.mymovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailMovieResponse implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<DetailMovieModel> results = null;
    public final static Parcelable.Creator<DetailMovieResponse> CREATOR = new Creator<DetailMovieResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetailMovieResponse createFromParcel(Parcel in) {
            return new DetailMovieResponse(in);
        }

        public DetailMovieResponse[] newArray(int size) {
            return (new DetailMovieResponse[size]);
        }

    }
            ;

    protected DetailMovieResponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (DetailMovieModel.class.getClassLoader()));
    }

    public DetailMovieResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DetailMovieModel> getResults() {
        return results;
    }

    public void setResults(List<DetailMovieModel> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
