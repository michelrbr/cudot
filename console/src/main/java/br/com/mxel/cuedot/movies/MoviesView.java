package br.com.mxel.cuedot.movies;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.data.DataComponent;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.util.CueDotConstants;

/**
 * Created by michelribeiro on 03/08/17.
 */

public class MoviesView implements IMoviesView {

    @Inject
    MoviesPresenter presenter;

    public MoviesView(DataComponent dataComponent) {
        DaggerMoviesComponent.builder()
                .dataComponent(dataComponent)
                .moviesModule(new MoviesModule(this))
                .build()
                .inject(this);

        presenter.getMoviesOrderedBy(CueDotConstants.ORDER_BY_POPULAR);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        System.out.println("Cassildis Error!");
    }

    @Override
    public void showMoviesList(List<Movie> movies) {

        for (Movie m : movies) {
            System.out.println(m.title);
        }
    }
}
