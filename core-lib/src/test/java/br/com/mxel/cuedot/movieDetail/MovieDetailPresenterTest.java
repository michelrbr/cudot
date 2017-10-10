package br.com.mxel.cuedot.movieDetail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.remote.model.Movie;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import io.reactivex.Observable;
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

        // mock scheduler calls
        when(_scheduler.mainThread())
                .thenReturn(Schedulers.computation());
        when(_scheduler.backgroundThread())
                .thenReturn(Schedulers.computation());

        when(_movie.getId()).thenReturn(Long.valueOf(1));
        when(_movie.getTitle()).thenReturn("Testing movie");

        _presenter = new MovieDetailPresenter(_repository, _scheduler, _movie);
        _presenter.bind(_view);
    }

    @Test
    public void fetchMovieDetailTest() {

        when(_repository.getMovie(any(Long.class)))
                .thenReturn(Observable.just(_movie));

        _presenter.fetchMovieDetails();

        InOrder viewOrder = Mockito.inOrder(_view, _repository, _scheduler);
        viewOrder.verify(_view).showMovieLoading(true);
        viewOrder.verify(_repository).getMovie(any(Long.class));
        viewOrder.verify(_scheduler).mainThread();
        viewOrder.verify(_view).showMovieLoading(false);
        viewOrder.verify(_view).showMovie(_movie);
    }
}
