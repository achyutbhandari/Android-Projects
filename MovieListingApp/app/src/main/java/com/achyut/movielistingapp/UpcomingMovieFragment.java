package com.achyut.movielistingapp;

/**
 * Created by achyut on 1/19/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.achyut.movielistingapp.rest.DetailActivity;
import com.achyut.movielistingapp.rest.RetrofitManager;
import com.achyut.movielistingapp.rest.response.MovieListing;
import com.achyut.movielistingapp.rest.response.MovieListingAdapter;
import com.achyut.movielistingapp.rest.response.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class UpcomingMovieFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ArrayList<Result> movieListingArrayList = new ArrayList<>();
    private MovieListingAdapter movieListingAdapter;

    public UpcomingMovieFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static UpcomingMovieFragment newInstance(int sectionNumber) {
        UpcomingMovieFragment fragment = new UpcomingMovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_upcoming);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        getUpcomingMovieList();
        movieListingAdapter = new MovieListingAdapter(getActivity(), movieListingArrayList);
        recyclerView.setAdapter(movieListingAdapter);

       /* TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }

    public void getUpcomingMovieList() {
        RetrofitManager.getInstance().getUpcomingMovieList(BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieListing>() {
            @Override
            public void onResponse(Call<MovieListing> call, Response<MovieListing> response) {
                if (response.code() == 200) {
                    movieListingArrayList.addAll(response.body().getResults());
                    movieListingAdapter.notifyDataSetChanged();

                    movieListingAdapter.setClickListener(new MovieListingAdapter.MovieItemClickListener() {
                        @Override
                        public void onClick(Result result) {

                            Intent intent = new Intent(getActivity(), DetailActivity.class);

                                intent.putExtra("Movie_Detail", result);
                                startActivity(intent);


                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<MovieListing> call, Throwable t) {

            }
        });
    }
}