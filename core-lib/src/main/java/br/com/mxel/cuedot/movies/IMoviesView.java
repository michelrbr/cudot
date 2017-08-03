package br.com.mxel.cuedot.movies;

import java.util.List;

import br.com.mxel.cuedot.data.model.Movie;

/**
 * Created by michelribeiro on 02/08/17.
 */

public interface IMoviesView {

    void showLoading();
    void hideLoading();
    void showError();
    void showMoviesList(List<Movie> movies);
}
