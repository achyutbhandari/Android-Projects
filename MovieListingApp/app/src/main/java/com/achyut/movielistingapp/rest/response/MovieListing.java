
package com.achyut.movielistingapp.rest.response;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieListing implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    public final static Creator<MovieListing> CREATOR = new Creator<MovieListing>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MovieListing createFromParcel(Parcel in) {
            MovieListing instance = new MovieListing();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (com.achyut.movielistingapp.rest.response.Result.class.getClassLoader()));
            instance.dates = ((Dates) in.readValue((Dates.class.getClassLoader())));
            instance.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public MovieListing[] newArray(int size) {
            return (new MovieListing[size]);
        }

    }
    ;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(results);
        dest.writeValue(dates);
        dest.writeValue(totalPages);
        dest.writeValue(totalResults);
    }

    public int describeContents() {
        return  0;
    }

}
