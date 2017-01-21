package com.achyut.movielistingapp.rest;


import com.achyut.movielistingapp.rest.response.MovieListing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bhand on 1/9/2017.
 */

public interface MovieListingService {

    @GET("upcoming")
    Call<MovieListing> getUpcomingMovies(@Query("api_key") String apikey) ;

    @GET("popular")
    Call<MovieListing> getPopularMovies(@Query("api_key") String apikey) ;

    @GET("top_rated")
    Call<MovieListing> getTopRatedMovies(@Query("api_key") String apikey);

    @GET("now_playing")
    Call<MovieListing> getNowPlayingMovies(@Query("api_key") String apikey) ;



}
