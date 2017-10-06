package br.com.mxel.cuedot.data.model;

/**
 * Created by michelribeiro on 05/10/17.
 */

public interface IMovie {
    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String title);

    String getPosterPath();

    void setPosterPath(String posterPath);

    String getBackdropPath();

    void setBackdropPath(String backdropPath);

    String getSynopsis();

    void setSynopsis(String synopsis);

    double getRating();

    void setRating(double rating);

    String getReleaseDate();

    void setReleaseDate(String releaseDate);

    String getHomepage();

    void setHomepage(String homepage);

    boolean isVideo();

    void setVideo(boolean video);
}
