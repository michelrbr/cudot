package br.com.mxel.cuedot.movies;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.remote.model.ListMovieResult;
import br.com.mxel.cuedot.data.remote.model.Movie;
import br.com.mxel.cuedot.util.CueDotConstants;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by michelribeiro on 09/10/17.
 */

public class MoviesPresenterTest {

    @Mock
    private RepositoryDataSource _repository;
    @Mock
    private IMoviesView _view;
    @Mock
    private ISchedulerProvider _scheduler;

    private MoviesPresenter _presenter;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);

        // mock scheduler calls
        when(_scheduler.mainThread())
                .thenReturn(Schedulers.computation());
        when(_scheduler.backgroundThread())
                .thenReturn(Schedulers.computation());

        _presenter = new MoviesPresenter(_repository, _scheduler);
        _presenter.bind(_view);
    }

    @Test
    public void getMoviesByOrderTest() {

        Movie movie = new Movie();
        movie.setTitle("Testing movie");

        ArrayList listArr = new ArrayList(1);
        listArr.add(movie);

        ListMovieResult list = new ListMovieResult();
        list.page = 1;
        list.totalPages = 1;
        list.results = listArr;

        // mock repository calls
        when(_repository.getMoviesOrderBy(any(String.class), any(Integer.class)))
                .thenReturn(Observable.just(list));

        _presenter.getMoviesOrderedBy(CueDotConstants.ORDER_BY_POPULAR);

        InOrder viewOrder = Mockito.inOrder(_view, _repository, _scheduler);
        viewOrder.verify(_view).setEnableLoadMore(true);
        viewOrder.verify(_view).showLoading();
        viewOrder.verify(_repository).getMoviesOrderBy(any(String.class), any(Integer.class));
        viewOrder.verify(_scheduler).mainThread();
        viewOrder.verify(_view).hideLoading();
        viewOrder.verify(_view).showMoviesList(listArr);
    }
}
