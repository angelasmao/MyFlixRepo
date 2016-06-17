package com.codepath.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView title = (TextView) findViewById(R.id.tvMovieTitle);
        TextView release = (TextView) findViewById(R.id.tvRelease);
        RatingBar rating = (RatingBar) findViewById(R.id.rbRating);
        TextView votes = (TextView) findViewById(R.id.tvVotes);
        TextView description = (TextView) findViewById(R.id.tvDescription);
        ImageView background = (ImageView) findViewById(R.id.ivImage);

        title.setText(getIntent().getStringExtra("title"));
        release.setText("Release date: " + getIntent().getStringExtra("release_date"));
        //rating.setNumStars(5);
        //double rat = getIntent().getDoubleExtra("rating", 0.0);
        rating.setRating(getIntent().getFloatExtra("rating", (float) 0.0));
        votes.setText("Votes: " + getIntent().getIntExtra("votes", 0));
        description.setText(getIntent().getStringExtra("description"));
        String imageUri = getIntent().getStringExtra("background_url");
        Picasso.with(this).load(imageUri).transform(new RoundedCornersTransformation(10,
                10)).into(background);


        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetail.this, VideoActivity.class);
                i.putExtra("id", getIntent().getStringExtra("id"));

                startActivity(i);
            }
        });
    }
}
