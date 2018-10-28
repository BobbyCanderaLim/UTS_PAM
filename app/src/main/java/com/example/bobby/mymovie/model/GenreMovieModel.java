package com.example.bobby.mymovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreMovieModel implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<GenreMovieModel> CREATOR = new Creator<GenreMovieModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GenreMovieModel createFromParcel(Parcel in) {
            return new GenreMovieModel(in);
        }

        public GenreMovieModel[] newArray(int size) {
            return (new GenreMovieModel[size]);
        }

    }
            ;

    protected GenreMovieModel(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GenreMovieModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

}