package br.com.mxel.cuedot.movies;

import java.util.List;

import br.com.mxel.cuedot.data.model.Movie;

/**
 * Created by michelribeiro on 02/08/17.
 */

public interface IMoviesView {

    void showLoading();

    void hideLoading();

    void setEnableLoadMore(boolean enabled);

    void showError(Throwable throwable);

    void hideError();

    void showMoviesList(List<Movie> movies);
}
