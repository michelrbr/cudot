package br.com.mxel.cuedot.movieDetail;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import io.reactivex.Observable;

/**
 * Created by michelribeiro on 04/08/17.
 */

public class MovieDetailPresenter {

    private RepositoryDataSource _repository;
    private ISchedulerProvider _scheduler;
    private Movie _movie;
    private IMovieDetailView _movieDetailView;

    public MovieDetailPresenter(RepositoryDataSource repository,
                                ISchedulerProvider scheduler,
                                Movie movie) {
        _repository = repository;
        _scheduler = scheduler;
        _movie = movie;
    }

    public void fetchMovieDetails() {
        loadMovie()
                .observeOn(_scheduler.mainThread())
                .subscribe(movie -> {
                    if(_movieDetailView != null) {
                        _movieDetailView.showMovie(_movie);
                    }
                });
    }

    private Observable<Movie> loadMovie(){
        return _repository.getMovie(_movie.id)
                .map(movie -> {
                    _movie = movie;
                    return _movie;
                });
    }

    public void bind(IMovieDetailView movieDetailView) {
        _movieDetailView = movieDetailView;
    }

    public void unbind(){
        _movieDetailView = null;
    }
}
