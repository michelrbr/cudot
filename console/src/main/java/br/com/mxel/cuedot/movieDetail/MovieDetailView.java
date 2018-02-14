package br.com.mxel.cuedot.movieDetail;

import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.data.model.MovieVideo;


/**
 * Created by michelribeiro on 18/08/17.
 */

public class MovieDetailView implements IMovieDetailView {

    @Inject
    Scanner scanner;

    @Inject
    MovieDetailPresenter presenter;

    private boolean _shouldFinish = false;
    private Movie _movie;

    public MovieDetailView(MovieDetailComponent component) {

        component.inject(this);

        presenter.bind(this);

        presenter.fetchMovieDetails();
    }

    @Override
    public void showMovieLoading(boolean show) {
        if(show) {
            System.out.println("Loading movie...");
        } else {
            System.out.println("Load complete\n");
        }
    }

    @Override
    public void showMovie(Movie movie) {

        _movie = movie;

        System.out.println("Name: " + _movie.getTitle());
        System.out.println("Rate: " + _movie.getRating());
        System.out.println("Synopsis: " + _movie.getSynopsis());
        System.out.println("Poster: " + _movie.getPosterPath());
        System.out.println("Backdrop: " + _movie.getBackdropPath());
    }

    @Override
    public void showMovieError(Throwable error) {
        System.out.println("Movie error: " + error.getMessage());
    }

    @Override
    public void hideMovieError() {

    }

    @Override
    public void showVideosLoading(boolean show) {
        if(show) {
            System.out.println("Loading movie videos...");
        } else {
            System.out.println("Load complete\n");
        }
    }

    @Override
    public void showVideos(List<? extends MovieVideo> movieVideos) {
        int count = 0;

        if(movieVideos.size() > 0) {
            for (MovieVideo mv : movieVideos) {
                count++;
                System.out.println(String.valueOf(count) + ": " + mv.getName());
            }
        } else {
            System.out.println("There are no video for : " + _movie.getTitle() + "\n");
        }
    }

    @Override
    public void showVideosError(Throwable error) {
        System.out.println("Movie error: " + error.getMessage());
    }

    @Override
    public void hideVideosError() {

    }

    @Override
    public void markAsFavorite() {

    }

    @Override
    public void unmarkAsFavorite() {

    }


    public void promptUser() {
        String options = "\nPlease choose an option\n";
        options += "Press 'v' to list videos\n";
        options += "Press 'b' to back to the list\n";

        System.out.println(options);

        String query = scanner.nextLine();
        if(query.equalsIgnoreCase("b")) {

            presenter.unbind();
            _shouldFinish = true;
        } else if(query.equalsIgnoreCase("v")) {

            presenter.fetchMovieVideos();
        }

    }

    public boolean getShouldFinish() {
        return _shouldFinish;
    }
}
