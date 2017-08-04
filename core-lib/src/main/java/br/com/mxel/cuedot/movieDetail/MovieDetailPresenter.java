package br.com.mxel.cuedot.movieDetail;

import br.com.mxel.cuedot.data.model.Movie;

/**
 * Created by michelribeiro on 04/08/17.
 */

public class MovieDetailPresenter {

    private Movie _movie;
    private IMovieDetail _movieDetailView;

    public MovieDetailPresenter(Movie movie, IMovieDetail movieDetailView) {
        _movie = movie;
        _movieDetailView = movieDetailView;
    }

    public void fetchMovieDetails() {
        _movieDetailView.showMovie(_movie);
    }
}
