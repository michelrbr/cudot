package br.com.mxel.cuedot.movies;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import io.reactivex.Observable;

/**
 * Created by michelribeiro on 03/08/17.
 */

public class MoviesPresenter {

    private RepositoryDataSource _repository;
    private ISchedulerProvider _scheduler;
    private IMoviesView _view;
    private List<Movie> _movies;
    private String _currentOrder;
    private int _currentPage;
    private int _totalPages;
    private int _totalResults;

    @Inject
    public MoviesPresenter(RepositoryDataSource repository,
                           ISchedulerProvider scheduler,
                           IMoviesView view) {
        _repository = repository;
        _scheduler = scheduler;
        _view = view;
        _movies = new ArrayList<>();
    }

    public void getMoviesOrderedBy(String order) {

        _currentOrder = order;
        _currentPage = 1;
        _view.setEnableLoadMore(true);
        _view.showLoading();
        loadMovies().observeOn(_scheduler.mainThread())
                .subscribe(
                        listResult -> {
                            _view.hideLoading();
                            _view.showMoviesList(listResult);
                        },
                        throwable -> _view.showError(throwable));
    }

    public void loadMore(){
        if(_currentPage < _totalPages) {
            _currentPage++;
            loadMovies().observeOn(_scheduler.mainThread())
                    .subscribe(
                            listResult -> {
                                _view.showMoviesList(listResult);
                            },
                            throwable -> _view.showError(throwable));
        } else {
            _view.setEnableLoadMore(false);
        }
    }

    private Observable<List<Movie>> loadMovies(){
        return _repository.getMoviesOrderBy(_currentOrder, _currentPage)
                .map(listResult -> {
                    _currentPage = listResult.page;
                    _totalPages = listResult.totalPages;
                    _totalResults = listResult.totalResults;
                    if (_currentPage >= _totalPages) {
                        _view.setEnableLoadMore(false);
                    }
                    addMovies(listResult.results);
                    return _movies;
                });
    }

    private void addMovies(List<Movie> movies){
        _movies.addAll(movies);
    }
}
