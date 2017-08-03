package br.com.mxel.cuedot.movies;

import javax.inject.Inject;

import br.com.mxel.cuedot.data.RepositoryDataSource;

/**
 * Created by michelribeiro on 03/08/17.
 */

public class MoviesPresenter {

    private RepositoryDataSource _repository;
    private IMoviesView _view;

    @Inject
    public MoviesPresenter(RepositoryDataSource repository, IMoviesView view) {
        this._repository = repository;
        this._view = view;
    }

    public void getMoviesOrderedBy(String order) {

        _repository.getMoviesOrderBy(order)
                .subscribe(
                        listResult -> _view.showMoviesList(listResult.results),
                        throwable -> _view.showError());
    }
}
