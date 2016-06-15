package com.codepath.flixster;

import java.util.ArrayList;

/**
 * Created by amao on 6/15/16.
 */
public class Movie {

    public String title;
    public int rating;
    public String posterUrl;

    public Movie(String title, int rating, String posterUrl) {
        this.title = title;
        this.rating = rating;
        this.posterUrl = posterUrl;
    }


    public static ArrayList<Movie> getFakeMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        for (int i = 0; i < 60; i++) {
            movies.add(new Movie("The Social Network", 75, ""));
            movies.add(new Movie("The Internship", 75, ""));
            movies.add(new Movie("The Lion King", 100, ""));
        }

        return movies;
    }

    @Override
    public String toString() {
        return title + " - " + rating;
    }
}
