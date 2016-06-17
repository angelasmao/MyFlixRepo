package com.codepath.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    ArrayList<Movie> movies = new ArrayList<Movie>();
    MoviesAdapter adapter;
    ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        lvMovies = (ListView) findViewById(R.id.lvMovies);
        adapter = new MoviesAdapter(MoviesActivity.this, movies);

        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results");

                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    adapter.notifyDataSetChanged();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });



        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MoviesActivity.this, MovieDetail.class);
                i.putExtra("title", movies.get(position).title);
                i.putExtra("release_date", movies.get(position).releaseDate);
                float rating = (float) movies.get(position).getRating()*(0.5f);
                i.putExtra("rating", rating);
                i.putExtra("votes", movies.get(position).votes);
                i.putExtra("description", movies.get(position).overview);
                i.putExtra("background_url", movies.get(position).getBackgroundUrl());
                i.putExtra("id", movies.get(position).id);

                startActivity(i);
            }
        });


    }
}
