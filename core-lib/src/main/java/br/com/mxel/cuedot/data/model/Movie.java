package br.com.mxel.cuedot.data.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import br.com.mxel.cuedot.util.CueDotConstants;

/**
 * Created by michelribeiro on 26/07/17.
 */

@DatabaseTable(tableName = "movies")
public class Movie implements Serializable {

    /*
    original _title
    movie poster image thumbnail
    A plot _synopsis (called overview in the api)
    user _rating (called vote_average in the api)
    release date

    "w92", "w154", "w185", "w342", "w500", "w780", or "original"
    */

    @SerializedName("id")
    @DatabaseField(columnName = "id", id = true)
    private int _id;

    @DatabaseField(columnName = "title")
    @SerializedName("title")
    private String _title;

    @DatabaseField(columnName = "poster_path")
    @SerializedName("poster_path")
    private String _posterPath;

    @DatabaseField(columnName = "backdrop_path")
    @SerializedName("backdrop_path")
    private String _backdropPath;

    @DatabaseField(columnName = "overview")
    @SerializedName("overview")
    private String _synopsis;

    @DatabaseField(columnName = "vote_average")
    @SerializedName("vote_average")
    private float _rating;

    @DatabaseField(columnName = "release_date")
    @SerializedName("release_date")
    private String _releaseDate;

    @DatabaseField(columnName = "homepage")
    @SerializedName("homepage")
    private String _homepage;

    @SerializedName("video")
    private boolean _video;

    @DatabaseField(columnName = "is_favorite")
    private boolean _isFavorite;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getPosterPath() {
        return String.format("%s/%s%s",
                CueDotConstants.IMAGES_BASE_PATH,
                CueDotConstants.POSTER_SIZE,
                _posterPath);
    }

    public void setPosterPath(String posterPath) {
        this._posterPath = posterPath;
    }

    public String getBackdropPath() {
        return String.format("%s/%s%s",
                CueDotConstants.IMAGES_BASE_PATH,
                CueDotConstants.BACKDROP_SIZE,
                _backdropPath);
    }

    public void setBackdropPath(String backdropPath) {
        this._backdropPath = backdropPath;
    }

    public String getSynopsis() {
        return _synopsis;
    }

    public void setSynopsis(String synopsis) {
        this._synopsis = synopsis;
    }

    public float getRating() {
        return _rating;
    }

    public void setRating(float rating) {
        this._rating = rating;
    }

    public String getReleaseDate() {
        return _releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this._releaseDate = releaseDate;
    }

    public String getHomepage() {
        return _homepage;
    }

    public void setHomepage(String homepage) {
        this._homepage = homepage;
    }

    public boolean isVideo() {
        return _video;
    }

    public void setVideo(boolean video) {
        this._video = video;
    }

    public boolean isFavorite() {
        return _isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this._isFavorite = isFavorite;
    }
}
