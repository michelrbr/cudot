package br.com.mxel.cuedot.movieDetail;

import java.util.List;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.model.IMovieVideo;
import br.com.mxel.cuedot.data.remote.model.Movie;
import br.com.mxel.cuedot.util.ISchedulerProvider;

/**
 * Created by michelribeiro on 04/08/17.
 */

public class MovieDetailPresenter {

    private RepositoryDataSource _repository;
    private ISchedulerProvider _scheduler;
    private Movie _movie;
    private List<IMovieVideo> _movieVideos;
    private IMovieDetailView _view;

    public MovieDetailPresenter(RepositoryDataSource repository,
                                ISchedulerProvider scheduler,
                                Movie movie) {
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
                        if(_movie.isFavorite()) {
                            _view.markAsFavorite();
                        } else {
                            _view.unmarkAsFavorite();
                        }
                    }
                }, throwable -> {
                    if (_view != null) {
                        _view.showMovieLoading(false);
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
                        _view.showMovieLoading(false);
                        _view.showVideosError(throwable);
                    }
                });
    }

    public void toggleFavorite() {
        if (_movie.isFavorite()) {
            removeFromFavorites();
        } else {
            addToFavorites();
        }
    }

    public void addToFavorites() {

        _repository.addMovieToFavorites(_movie)
                .observeOn(_scheduler.mainThread())
                .subscribe(() -> {
                    if(_view != null) {
                        _view.markAsFavorite();
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    if(_view != null) {
                        _view.unmarkAsFavorite();
                    }
                });
    }

    public void removeFromFavorites() {
        _repository.removeMovieFromFavorites(_movie.getId())
                .observeOn(_scheduler.mainThread())
                .subscribe(() -> {
                    if(_view != null) {
                        _view.unmarkAsFavorite();
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    if(_view != null) {
                        _view.markAsFavorite();
                    }
                });
    }
}
