package br.com.mxel.cuedot.movieDetail;

import java.util.List;

import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.data.model.IMovieVideo;

/**
 * Created by michelribeiro on 04/08/17.
 */

public interface IMovieDetailView {

    void showMovieLoading(boolean show);

    void showMovie(IMovie movie);

    void showMovieError(Throwable error);

    void hideMovieError();

    void showVideosLoading(boolean show);

    void showVideos(List<? extends IMovieVideo> movieVideos);

    void showVideosError(Throwable error);

    void hideVideosError();

    void markAsFavorite();

    void unmarkAsFavorite();
}
