package br.com.mxel.cuedot.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelribeiro on 26/07/17.
 */

public class ApiResult {

    public int page;

    @SerializedName("total_results")
    public long totalResults;

    @SerializedName("total_pages")
    public long totalPages;

    public List<Movie> results;
}
