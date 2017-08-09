package br.com.mxel.cuedot.movies;

import javax.inject.Inject;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.util.ISchedulerProvider;

/**
 * Created by michelribeiro on 03/08/17.
 */

public class MoviesPresenter {

    private RepositoryDataSource _repository;
    private ISchedulerProvider _scheduler;
    private IMoviesView _view;

    @Inject
    public MoviesPresenter(RepositoryDataSource repository,
                           ISchedulerProvider scheduler,
                           IMoviesView view) {
        _repository = repository;
        _scheduler = scheduler;
        _view = view;
    }

    public void getMoviesOrderedBy(String order) {

        _repository.getMoviesOrderBy(order)
                .observeOn(_scheduler.mainThread())
                .subscribe(
                        listResult -> _view.showMoviesList(listResult.results),
                        throwable -> _view.showError(throwable));
    }
}
