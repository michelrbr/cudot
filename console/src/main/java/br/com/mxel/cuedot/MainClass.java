package br.com.mxel.cuedot;

import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.movieDetail.MovieDetailComponent;
import br.com.mxel.cuedot.movieDetail.MovieDetailView;
import br.com.mxel.cuedot.movies.MoviesView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainClass {

    private static ConsoleComponent _consoleComponent;
    private static MovieDetailComponent _movieDetailComponent;

    public static void main(String[] args) {

        _consoleComponent = DaggerConsoleComponent.builder().build();
        MoviesView movies = new MoviesView();
        Disposable disposable = movies.getNotifyMovie().subscribe(new Consumer<Movie>() {
            @Override
            public void accept(Movie movie) throws Exception {

                new MovieDetailView(movie);
            }
        });
        while (!movies.getIsRunning()) {
            movies.promptUser();
        }

        disposable.dispose();
    }

    public static ConsoleComponent getConsoleComponent() {
        return _consoleComponent;
    }

    public static MovieDetailComponent getMovieDetailComponent() {
        return _movieDetailComponent;
    }
}
