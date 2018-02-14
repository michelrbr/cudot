package br.com.mxel.cuedot.movies;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import io.reactivex.Single;

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
                           ISchedulerProvider scheduler) {
        _repository = repository;
        _scheduler = scheduler;
        _movies = new ArrayList<>();
    }

    public void bind(IMoviesView moviesView) {
        _view = moviesView;
    }

    public void unbind(){
        _view = null;
    }

    public boolean isViewBound() {
        return _view != null;
    }

    public void getMoviesOrderedBy(String order) {

        if(_currentOrder == null || !_currentOrder.equals(order)) {
            if(_movies != null) _movies.clear();
            _currentOrder = order;
            _currentPage = 1;
            if (_view != null) {
                _view.setEnableLoadMore(true);
                _view.showLoading();
            }
            loadMovies().observeOn(_scheduler.mainThread())
                    .subscribe(
                            listResult -> {
                                if (_view != null) {
                                    _view.hideLoading();
                                    _view.showMoviesList(listResult);
                                }
                            },
                            throwable -> {
                                if (_view != null) {
                                    _view.showError(throwable);
                                }
                            });
        }
    }

    public void loadMore(){
        if(_currentPage < _totalPages) {
            _currentPage++;
            loadMovies().observeOn(_scheduler.mainThread())
                    .subscribe(
                            listResult -> {
                                if(_view != null) {
                                    _view.showMoviesList(listResult);
                                }
                            },
                            throwable -> {
                                if(_view != null) {
                                    _view.showError(throwable);
                                }
                            });
        } else {
            if(_view != null) {
                _view.setEnableLoadMore(false);
            }
        }
    }

    private Single<List<Movie>> loadMovies(){
        return _repository.getMoviesOrderBy(_currentOrder, _currentPage)
                .map(listResult -> {
                    _currentPage = listResult.page;
                    _totalPages = listResult.totalPages;
                    _totalResults = listResult.totalResults;
                    if (_view != null && _currentPage >= _totalPages) {
                        _view.setEnableLoadMore(false);
                    }
                    addMovies(listResult.results);
                    return _movies;
                });
    }

    private void addMovies(List<? extends Movie> movies){
        _movies.addAll(movies);
    }
}
