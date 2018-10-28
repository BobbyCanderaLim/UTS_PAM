package com.example.bobby.mymovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMovieResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    @SerializedName("results")
    @Expose
    private List<PopularMovieModel> results = null;
    public final static Parcelable.Creator<PopularMovieResponse> CREATOR = new Creator<PopularMovieResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PopularMovieResponse createFromParcel(Parcel in) {
            return new PopularMovieResponse(in);
        }

        public PopularMovieResponse[] newArray(int size) {
            return (new PopularMovieResponse[size]);
        }

    }
            ;

    protected PopularMovieResponse(Parcel in) {
        this.page = ((int) in.readValue((int.class.getClassLoader())));
        this.totalResults = ((int) in.readValue((int.class.getClassLoader())));
        this.totalPages = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.results, (PopularMovieResponse.class.getClassLoader()));
    }

    public PopularMovieResponse() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<PopularMovieModel> getResults() {
        return results;
    }

    public void setResults(List<PopularMovieModel> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
