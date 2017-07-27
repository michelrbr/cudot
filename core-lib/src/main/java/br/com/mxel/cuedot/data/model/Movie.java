package br.com.mxel.cuedot.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michelribeiro on 26/07/17.
 */

public class Movie {

    /*
    original title
    movie poster image thumbnail
    A plot synopsis (called overview in the api)
    user rating (called vote_average in the api)
    release date

    "w92", "w154", "w185", "w342", "w500", "w780", or "original"
    */

    public long id;

    public String title;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("overview")
    public String synopsis;

    @SerializedName("vote_average")
    public double rating;

    @SerializedName("release_date")
    public String releaseDate;

    /*public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342" + posterPath;
    }*/
}
