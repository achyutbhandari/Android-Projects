package com.achyut.movielistingapp.rest.response;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.net.InterfaceAddress;
import java.util.ArrayList;

import com.achyut.movielistingapp.R;
import com.bumptech.glide.Glide;


/**
 * Created by bhand on 1/9/2017.
 */

public class MovieListingAdapter extends RecyclerView.Adapter<MovieListingAdapter.MovieListViewHolder> {

    private Context context ;
    ArrayList<Result> movieListArrayList ;
    private MovieItemClickListener movieItemClickListener ;

    public MovieListingAdapter(Context context, ArrayList<Result> movieListArrayList) {
        this.context = context;
        this.movieListArrayList = movieListArrayList;
    }

    public  void  setClickListener(MovieItemClickListener movieItemClickListener) {
        this.movieItemClickListener = movieItemClickListener ;
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list,parent,false) ;
       MovieListViewHolder movieListViewHolder = new MovieListViewHolder(layoutView) ;
        return movieListViewHolder ;
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, final int position) {
    holder.tvMovieTitle.setText(movieListArrayList.get(position).getOriginalTitle());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185//"+movieListArrayList.get(position).getPosterPath())
                .into(holder.ivMovieImage);
        holder.tvmovieReleaseDate.setText(movieListArrayList.get(position).getReleaseDate());
        holder.tvMovieRatings.setText("" + movieListArrayList.get(position).getVoteAverage());

        holder.rlMovieContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieItemClickListener != null) {
                    movieItemClickListener.onClick(movieListArrayList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.movieListArrayList.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMovieTitle, tvMovieRatings, tvmovieReleaseDate;
        private ImageView ivMovieImage;
        private RelativeLayout rlMovieContainer;

        public MovieListViewHolder(View itemView) {
            super(itemView);

            tvMovieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            tvMovieRatings = (TextView) itemView.findViewById(R.id.tv_movie_ratings);
            tvmovieReleaseDate = (TextView) itemView.findViewById(R.id.tv_movie_release_date);
            ivMovieImage = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
            rlMovieContainer = (RelativeLayout) itemView.findViewById(R.id.rl_movie_container);
        }
    }

  public interface MovieItemClickListener {
      void  onClick(Result result) ;
    }

}
