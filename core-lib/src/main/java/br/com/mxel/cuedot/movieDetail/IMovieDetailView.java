package br.com.mxel.cuedot.movieDetail;

import java.util.List;

import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.data.model.MovieVideo;

/**
 * Created by michelribeiro on 04/08/17.
 */

public interface IMovieDetailView {

    void showMovieLoading(boolean show);

    void showMovie(Movie movie);

    void showMovieError(Throwable error);

    void hideMovieError();

    void showVideosLoading(boolean show);

    void showVideos(List<MovieVideo> movieVideos);

    void showVideosError(Throwable error);

    void hideVideosError();
}
