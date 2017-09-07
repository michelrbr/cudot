package br.com.mxel.cuedot;

import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.movieDetail.DaggerMovieDetailComponent;
import br.com.mxel.cuedot.movieDetail.MovieDetailComponent;
import br.com.mxel.cuedot.movieDetail.MovieDetailModule;
import br.com.mxel.cuedot.movieDetail.MovieDetailView;
import br.com.mxel.cuedot.movies.MoviesView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainClass {

    private static ConsoleComponent _consoleComponent;
    private static MovieDetailView _movieDetailView;

    public static void main(String[] args) {

        _consoleComponent = DaggerConsoleComponent.builder().build();
        MoviesView movies = new MoviesView();
        Disposable disposable = movies.getNotifyMovie().subscribe(new Consumer<Movie>() {
            @Override
            public void accept(Movie movie) throws Exception {

                MovieDetailComponent movieDetailComponent = DaggerMovieDetailComponent.builder()
                    .consoleComponent(MainClass.getConsoleComponent())
                    .movieDetailModule(new MovieDetailModule(movie))
                    .build();
                _movieDetailView = new MovieDetailView(movieDetailComponent);
            }
        });
        while (!movies.getIsRunning()) {

            if(_movieDetailView != null) {
                if (_movieDetailView.getShouldFinish()) {
                    _movieDetailView = null;
                    movies.promptUser();
                } else {
                    _movieDetailView.promptUser();
                }
            } else {
                movies.promptUser();
            }
        }

        disposable.dispose();
    }

    public static ConsoleComponent getConsoleComponent() {
        return _consoleComponent;
    }
}
