package com.codepath.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amao on 6/15/16.
 */
public class Movie {

    public String title;
    public double rating;
    public String posterUrl;
    public String overview;
    public String backgroundUrl;
    public String releaseDate;
    public int votes;
    public String id;


    public String getReleaseDate() {
        return releaseDate;
    }

    public int getVotes() {
        return votes;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String getPosterUrl() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterUrl);
    }

    public String getOverview() {
        return overview;
    }

    public String getBackgroundUrl() {
        return String.format("https://image.tmdb.org/t/p/w780/%s", backgroundUrl);
    }

    public Movie(JSONObject jsonObject) throws JSONException {

        this.title = jsonObject.getString("original_title");
        this.rating = jsonObject.getDouble("vote_average");
        this.posterUrl = jsonObject.getString("poster_path");
        this.overview = jsonObject.getString("overview");
        this.backgroundUrl = jsonObject.getString("backdrop_path");
        this.votes = jsonObject.getInt("vote_count");
        this.releaseDate = jsonObject.getString("release_date");
        this.id = jsonObject.getString("id");
/*
        if (jsonObject.optJSONObject("title") != null) {
            this.title = jsonObject.getString("original_title");
        }
        if (jsonObject.optJSONObject("rating") != null) {
            this.rating = jsonObject.getInt("vote_average") * 10;
        }
        if (jsonObject.optJSONObject("poster_path") != null) {
            this.posterUrl = jsonObject.getString("poster_path");
        }
        if (jsonObject.optJSONObject("overview") != null) {
            this.overview = jsonObject.getString("overview");
        }
*/
    }


    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        for (int i = 0; i < array.length(); i++) {
            try {
                movies.add(new Movie(array.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return movies;
    }

    @Override
    public String toString() {
        return title + " - " + rating;
    }
}
