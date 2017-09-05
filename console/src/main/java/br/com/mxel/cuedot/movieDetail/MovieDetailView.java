package br.com.mxel.cuedot.movieDetail;

import java.util.Scanner;

import javax.inject.Inject;

import br.com.mxel.cuedot.MainClass;
import br.com.mxel.cuedot.data.model.Movie;

/**
 * Created by michelribeiro on 18/08/17.
 */

public class MovieDetailView implements IMovieDetail {

    @Inject
    Scanner scanner;

    @Inject
    MovieDetailPresenter presenter;

    public MovieDetailView(Movie movie) {

        DaggerMovieDetailComponent.builder()
                .consoleComponent(MainClass.getConsoleComponent())
                .movieDetailModule(new MovieDetailModule(movie, this))
                .build()
                .inject(this);

        presenter.fetchMovieDetails();
    }

    @Override
    public void showMovie(Movie movie) {

        System.out.println("Name: " + movie.title);
        System.out.println("Rate: " + movie.rating);
        System.out.println("Synopsis: " + movie.synopsis);
    }
}
