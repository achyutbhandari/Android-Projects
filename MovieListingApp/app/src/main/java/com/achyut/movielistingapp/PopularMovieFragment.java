package com.achyut.movielistingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends Fragment {


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ArrayList<Result> movieListingArrayList = new ArrayList<>();
    private MovieListingAdapter movieListingAdapter;

    public PopularMovieFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PopularMovieFragment newInstance(int sectionNumber) {

        Bundle args = new Bundle();

        PopularMovieFragment fragment = new PopularMovieFragment();
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


        getPopularMovieList();
        movieListingAdapter = new MovieListingAdapter(getActivity(), movieListingArrayList);
        recyclerView.setAdapter(movieListingAdapter);

       /* TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }

    public void getPopularMovieList() {
        RetrofitManager.getInstance().getPopularMovieList(BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieListing>() {
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
