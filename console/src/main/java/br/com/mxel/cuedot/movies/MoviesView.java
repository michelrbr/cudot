package br.com.mxel.cuedot.movies;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.ConsoleComponent;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.util.CueDotConstants;

/**
 * Created by michelribeiro on 03/08/17.
 */

public class MoviesView implements IMoviesView {

    @Inject
    MoviesPresenter presenter;

    public MoviesView(ConsoleComponent consoleComponent) {

        DaggerMoviesComponent.builder()
                .consoleComponent(consoleComponent)
                .moviesModule(new MoviesModule(this))
                .build()
                .inject(this);

        presenter.getMoviesOrderedBy(CueDotConstants.ORDER_BY_POPULAR);
    }

    @Override
    public void showLoading() {
        System.out.println("Loading...\n");
    }

    @Override
    public void hideLoading() {
        System.out.println("Loading complete\n");
    }

    @Override
    public void showError(Throwable throwable) {
        System.out.println("Cassildis Error: " + throwable.getMessage());
    }

    @Override
    public void showMoviesList(List<Movie> movies) {

        for (Movie m : movies) {
            System.out.println(m.title);
        }
    }
}
