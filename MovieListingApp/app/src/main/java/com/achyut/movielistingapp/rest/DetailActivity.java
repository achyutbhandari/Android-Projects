package com.achyut.movielistingapp.rest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.achyut.movielistingapp.R;
import com.achyut.movielistingapp.rest.response.Result;
import com.bumptech.glide.Glide;


public class DetailActivity extends AppCompatActivity {

    private TextView movieTitle, movieRating , releaseDate, moviedetail ;
    private ImageView imageView , imageSmall;
    private Context context ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Toolbar toolbars = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Result movieDetail = (Result) intent.getExtras().get("Movie_Detail");
      /*
        Bundle bundle = getIntent().getExtras();
        Result movieDetail =  bundle.getParcelable("Movie_Detail") ;*/

        imageView = (ImageView) findViewById(R.id.image) ;
        imageSmall = (ImageView) findViewById(R.id.image_small) ;
        movieTitle = (TextView) findViewById(R.id.tv_title_name) ;
        releaseDate = (TextView) findViewById(R.id.tv_date) ;
        movieRating = (TextView) findViewById(R.id.tv_rating) ;
        moviedetail = (TextView)findViewById(R.id.tv_moviedetail) ;


        movieTitle.setText(movieDetail.getTitle());
        releaseDate.setText(movieDetail.getReleaseDate());
        moviedetail.setText(movieDetail.getOverview());
        movieRating.setText(String.valueOf(movieDetail.getVoteAverage()));



        Glide.with(DetailActivity.this).load("http://image.tmdb.org/t/p/w185/"+ movieDetail.getPosterPath())
                .override(350,350)
                .fitCenter()
                .into(imageSmall) ;



        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(movieDetail.getTitle());

       /* Glide.with(DetailActivity.this).load("http://image.tmdb.org/t/p/w185/"+ movieDetail.getPosterPath())
                .override(350,350)
                .fitCenter()
                .into(imageView) ;*/
        Glide.with(DetailActivity.this).load("http://image.tmdb.org/t/p/w185/"+ movieDetail.getBackdropPath())
                .centerCrop()
                .into(imageView);




    }


    public  static Intent getLaunchIntent(Context context, Result result) {
        Intent movieDetailActivityIntent = new Intent(context, DetailActivity.class);
        movieDetailActivityIntent.putExtra("Movie_Detail", result);
        return movieDetailActivityIntent;
    }
}
