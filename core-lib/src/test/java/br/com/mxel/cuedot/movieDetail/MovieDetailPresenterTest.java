package br.com.mxel.cuedot.movieDetail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.data.remote.model.Movie;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by michelribeiro on 09/10/17.
 */

public class MovieDetailPresenterTest {

    @Mock
    private Movie _movie;
    @Mock
    private RepositoryDataSource _repository;
    @Mock
    private IMovieDetailView _view;
    @Mock
    private ISchedulerProvider _scheduler;

    private MovieDetailPresenter _presenter;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);

        Single<Void> single = new Single<Void>() {
            @Override
            protected void subscribeActual(SingleObserver<? super Void> observer) {
                observer.onSuccess(null);
            }
        };

        // mock scheduler calls
        when(_scheduler.mainThread())
                .thenReturn(Schedulers.computation());
        when(_scheduler.backgroundThread())
                .thenReturn(Schedulers.computation());

        // mock movie calls
        when(_movie.getId()).thenReturn(Long.valueOf(1));
        when(_movie.getTitle()).thenReturn("Testing movie");

        // mock repository calls
        when(_repository.getMovie(any(Long.class))).thenReturn(Observable.just(_movie));
        when(_repository.addMovieToFavorites(any(IMovie.class))).thenReturn(single);
        when(_repository.removeMovieFromFavorites(any(Long.class))).thenReturn(single);

        _presenter = new MovieDetailPresenter(_repository, _scheduler, _movie);
        _presenter.bind(_view);
    }

    @Test
    public void fetchMovieDetailTest() {

        _presenter.fetchMovieDetails();

        InOrder viewOrder = Mockito.inOrder(_view, _repository, _scheduler);
        viewOrder.verify(_view).showMovieLoading(true);
        viewOrder.verify(_repository).getMovie(any(Long.class));
        viewOrder.verify(_scheduler).mainThread();
        viewOrder.verify(_view).showMovieLoading(false);
        viewOrder.verify(_view).showMovie(_movie);
    }

    @Test
    public void addToFavoriteTest() {

        _presenter.addToFavorites();

        InOrder viewOrder = Mockito.inOrder(_view, _repository, _scheduler);
        viewOrder.verify(_repository).addMovieToFavorites(any(IMovie.class));
        viewOrder.verify(_scheduler).mainThread();
        viewOrder.verify(_view).markAsFavorite();
    }

    @Test
    public void removeToFavoriteTest() {

        _presenter.removeFromFavorites();

        InOrder viewOrder = Mockito.inOrder(_view, _repository, _scheduler);
        viewOrder.verify(_repository).removeMovieFromFavorites(_movie.getId());
        viewOrder.verify(_scheduler).mainThread();
        viewOrder.verify(_view).unmarkAsFavorite();
    }
}
