package br.com.mxel.cuedot.movieDetail;

import java.util.List;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.data.model.IMovieVideo;
import br.com.mxel.cuedot.util.ISchedulerProvider;

/**
 * Created by michelribeiro on 04/08/17.
 */

public class MovieDetailPresenter {

    private RepositoryDataSource _repository;
    private ISchedulerProvider _scheduler;
    private IMovie _movie;
    private List<IMovieVideo> _movieVideos;
    private IMovieDetailView _view;

    public MovieDetailPresenter(RepositoryDataSource repository,
                                ISchedulerProvider scheduler,
                                IMovie movie) {
        _repository = repository;
        _scheduler = scheduler;
        _movie = movie;
    }

    public void bind(IMovieDetailView movieDetailView) {
        _view = movieDetailView;
    }

    public void unbind(){
        _view = null;
    }

    public void fetchMovieDetails() {

        if(_view != null) {
            _view.hideMovieError();
            _view.showMovieLoading(true);
        }
        _repository.getMovie(_movie.getId())
                .map(movie -> {
                    _movie = movie;
                    return _movie;
                })
                .observeOn(_scheduler.mainThread())
                .subscribe(movie -> {
                    if(_view != null) {
                        _view.showMovieLoading(false);
                        _view.showMovie(_movie);
                    }
                }, throwable -> {
                    if (_view != null) {
                        _view.showMovieError(throwable);
                    }
                });
    }

    public void fetchMovieVideos() {
        if(_view != null) {
            _view.hideVideosError();
            _view.showVideosLoading(true);
        }
        _repository.getMovieVideos(_movie.getId())
                .map(listVideoResult -> listVideoResult.results)
                .observeOn(_scheduler.mainThread())
                .subscribe( movieVideos -> {
                    if(_view != null) {
                        _view.showMovieLoading(false);
                        _view.showVideos(movieVideos);
                    }
                }, throwable -> {
                    if (_view != null) {
                        _view.showVideosError(throwable);
                    }
                });
    }

    public void addToFavorites() {
        try {
            _repository.addMovieToFavorites(_movie);
            _view.markAsFavorite();
        } catch (Exception e) {
            e.printStackTrace();
            _view.unmarkAsFavorite();
        }
    }

    public void removeFromFavorites() {
        try {
            _repository.removeMovieFromFavorites(_movie.getId());
            _view.unmarkAsFavorite();
        } catch (Exception e) {
            e.printStackTrace();
            _view.markAsFavorite();
        }
    }
}
