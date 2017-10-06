package br.com.mxel.cuedot.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelribeiro on 26/07/17.
 */

public class ListMovieResult {

    public int page;

    @SerializedName("total_results")
    public int totalResults;

    @SerializedName("total_pages")
    public int totalPages;

    public List<Movie> results;
}
