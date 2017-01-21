package com.achyut.movielistingapp.rest;

import com.achyut.movielistingapp.rest.response.MovieListing;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bhand on 1/9/2017.
 */

public class RetrofitManager {
    public static Retrofit retrofit = null ;
    public  static  MovieListingService movieListingService = null ;
    public  static  RetrofitManager retrofitManager = null;
    private  static  String BASE_URL = "https://api.themoviedb.org/3/movie/" ;

    private  RetrofitManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) ;
        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                   .client(client)
                   .build();

        movieListingService = retrofit.create(MovieListingService.class) ;

    }

    public  static RetrofitManager getInstance() {
        if(retrofitManager == null) {
            retrofitManager =new RetrofitManager();
        }
        return  retrofitManager ;
    }

    public  static  void  getUpcomingMovieList(String apikey, Callback<MovieListing> getMovieListingCallBack) {
      Call<MovieListing> getMovieListing = movieListingService.getUpcomingMovies(apikey ) ;
       getMovieListing.enqueue(getMovieListingCallBack);
    }

    public static void getPopularMovieList(String apikey,Callback<MovieListing> getMovieListingCallBack ) {
        Call<MovieListing> getMovieListing = movieListingService.getPopularMovies(apikey) ;
        getMovieListing.enqueue(getMovieListingCallBack);
    }

    public  static  void  getTopRatedMovieList(String apikey, Callback<MovieListing> getMovieListingCallBack) {
        Call<MovieListing> getMovieListing = movieListingService.getTopRatedMovies(apikey) ;
        getMovieListing.enqueue(getMovieListingCallBack);
    }

    public static void getNowPlayingMovieList(String apikey, Callback<MovieListing> getMovieListingCallBack) {
        Call<MovieListing> getMovieListing = movieListingService.getNowPlayingMovies(apikey) ;
        getMovieListing.enqueue(getMovieListingCallBack);
    }


}
