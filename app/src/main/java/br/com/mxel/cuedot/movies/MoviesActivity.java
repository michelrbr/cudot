package br.com.mxel.cuedot.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.CueDotApplication;
import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.movieDetail.MovieDetailActivity;
import br.com.mxel.cuedot.movies.adapter.MoviesAdapter;
import br.com.mxel.cuedot.util.CueDotConstants;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class MoviesActivity extends AppCompatActivity
        implements IMoviesView {

    private static final String SPINNER_SELECTION = "spinner_selection";
    //Views
    @BindView(R.id.movies_recycler_view)
    RecyclerView moviesRecyclerView;
    @BindView(R.id.ordering_spinner)
    AppCompatSpinner orderingSpinner;
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;
    @BindView(R.id.ordering_view)
    View orderingView;
    @BindView(R.id.error_text_view)
    AppCompatTextView errorTextView;

    @Inject
    MoviesPresenter _presenter;

    @BindArray(R.array.order_by_list)
    String[] orderArray;

    private Unbinder _unbinder;
    private boolean _loadMore;
    private int _currentSelection;
    private CompositeDisposable _subscriptions;
    private MoviesAdapter _moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        _unbinder = ButterKnife.bind(this);

        DaggerMoviesComponent.builder()
                .appComponent(CueDotApplication.getAppComponent())
                .moviesModule(new MoviesModule())
                .build()
                .inject(this);

        _subscriptions = new CompositeDisposable();

        _presenter.bind(this);

        if(savedInstanceState != null) {
            _currentSelection = savedInstanceState.getInt(SPINNER_SELECTION);
        }

        setupView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!_presenter.isViewBound()) {
            _presenter.bind(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(_currentSelection == 4) {
            _presenter.getFavoriteMovies();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        _presenter.unbind();

        if(orderingSpinner != null) {
            outState.putInt(SPINNER_SELECTION, orderingSpinner.getSelectedItemPosition());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if(_presenter.isViewBound()) {
            _presenter.unbind();
        }

        if(_unbinder != null) {
            _unbinder.unbind();
        }

        if(_subscriptions != null && !_subscriptions.isDisposed()) {
            _subscriptions.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {

        loadingProgress.setVisibility(View.VISIBLE);
        moviesRecyclerView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {

        loadingProgress.setVisibility(View.GONE);
        moviesRecyclerView.setVisibility(View.VISIBLE);
        orderingView.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEnableLoadMore(boolean enabled) {
        _loadMore = enabled;
        _moviesAdapter.canLoadMore = _loadMore;
    }

    @Override
    public void showError(Throwable throwable) {

        Timber.e(throwable);
        loadingProgress.setVisibility(View.GONE);
        moviesRecyclerView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(throwable.getMessage());
    }

    @Override
    public void hideError() {
        errorTextView.setVisibility(View.GONE);
    }

    @Override
    public void showMoviesList(List<Movie> movies) {

        loadingProgress.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);
        moviesRecyclerView.setVisibility(View.VISIBLE);
        ((MoviesAdapter) moviesRecyclerView.getAdapter()).setData(movies);
        //moviesRecyclerView.scrollToPosition(0);
    }

    private void setupView() {

        _moviesAdapter = new MoviesAdapter();

        _subscriptions.add(_moviesAdapter.asObservable().subscribe(this::showMovieDetails));

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setAdapter(_moviesAdapter);
        moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                checkScroll(recyclerView, dx, dy);
            }
        });

        ArrayAdapter orderAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                orderArray);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        orderingSpinner.setAdapter(orderAdapter);

        orderingSpinner.setSelection(_currentSelection);
    }

    void showMovieDetails(Movie movie) {

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    void checkScroll(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (_loadMore && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {

            _presenter.loadMore();
        }
    }

    @OnItemSelected(R.id.ordering_spinner)
    void selectCurrentOrder(AdapterView<?> parent, View view, int position, long id) {
        String ordering = "";
        _currentSelection = position;
        switch (position) {
            case 0:
                ordering = CueDotConstants.ORDER_BY_POPULAR;
                break;
            case 1:
                ordering = CueDotConstants.ORDER_BY_TOP_RATED;
                break;
            case 2:
                ordering = CueDotConstants.ORDER_BY_NOW_PLAYING;
                break;
            case 3:
                ordering = CueDotConstants.ORDER_BY_UPCOMING;
                break;
            case 4:
                _presenter.getFavoriteMovies();
                break;
            default:
                ordering = CueDotConstants.ORDER_BY_POPULAR;
                break;
        }

        if(!TextUtils.isEmpty(ordering)) {
            moviesRecyclerView.scrollToPosition(0);
            _presenter.getMoviesOrderedBy(ordering);
        }
    }
}
