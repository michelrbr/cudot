package br.com.mxel.cuedot.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.CueDotApplication;
import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.movies.adapter.MoviesAdapter;
import br.com.mxel.cuedot.util.CueDotConstants;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class MoviesActivity extends AppCompatActivity implements IMoviesView{

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

        setupView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        _presenter.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        _presenter.unbind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _unbinder.unbind();
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
    public void showMoviesList(List<IMovie> movies) {

        loadingProgress.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);
        moviesRecyclerView.setVisibility(View.VISIBLE);
        ((MoviesAdapter) moviesRecyclerView.getAdapter()).setData(movies);

    }

    private void setupView() {

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setAdapter(new MoviesAdapter());
        moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {

                    if(_loadMore) {
                        _presenter.loadMore();
                    }
                }
            }
        });

        ArrayAdapter orderAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                orderArray);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        orderingSpinner.setAdapter(orderAdapter);
        orderingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String ordering;
                switch (position) {
                    case 1:
                        ordering = CueDotConstants.ORDER_BY_TOP_RATED;
                        break;
                    case 2:
                        ordering = CueDotConstants.ORDER_BY_NOW_PLAYING;
                        break;
                    case 3:
                        ordering = CueDotConstants.ORDER_BY_UPCOMING;
                        break;
                    default:
                        ordering = CueDotConstants.ORDER_BY_POPULAR;
                }

                _presenter.getMoviesOrderedBy(ordering);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
