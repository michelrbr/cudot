package br.com.mxel.cuedot.data.remote.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.util.CueDotConstants;

/**
 * Created by michelribeiro on 26/07/17.
 */

@DatabaseTable(tableName = "movies")
public class Movie implements Serializable, IMovie {

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
    private long _id;

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

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        this._id = id;
    }

    @Override
    public String getTitle() {
        return _title;
    }

    @Override
    public void setTitle(String title) {
        this._title = title;
    }

    @Override
    public String getPosterPath() {
        return String.format("%s/%s%s",
                CueDotConstants.IMAGES_BASE_PATH,
                CueDotConstants.POSTER_SIZE,
                _posterPath);
    }

    @Override
    public void setPosterPath(String posterPath) {
        this._posterPath = posterPath;
    }

    @Override
    public String getBackdropPath() {
        return String.format("%s/%s%s",
                CueDotConstants.IMAGES_BASE_PATH,
                CueDotConstants.BACKDROP_SIZE,
                _backdropPath);
    }

    @Override
    public void setBackdropPath(String backdropPath) {
        this._backdropPath = backdropPath;
    }

    @Override
    public String getSynopsis() {
        return _synopsis;
    }

    @Override
    public void setSynopsis(String synopsis) {
        this._synopsis = synopsis;
    }

    @Override
    public float getRating() {
        return _rating;
    }

    @Override
    public void setRating(float rating) {
        this._rating = rating;
    }

    @Override
    public String getReleaseDate() {
        return _releaseDate;
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        this._releaseDate = releaseDate;
    }

    @Override
    public String getHomepage() {
        return _homepage;
    }

    @Override
    public void setHomepage(String homepage) {
        this._homepage = homepage;
    }

    @Override
    public boolean isVideo() {
        return _video;
    }

    @Override
    public void setVideo(boolean video) {
        this._video = video;
    }

    public boolean isFavorite() {
        return _isFavorite;
    }

    public void setIsFavorite(boolean _isFavorite) {
        this._isFavorite = _isFavorite;
    }
}
