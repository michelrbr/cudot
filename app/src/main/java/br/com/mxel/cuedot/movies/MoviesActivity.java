package br.com.mxel.cuedot.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.CueDotApplication;
import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.movies.adapter.MoviesAdapter;
import br.com.mxel.cuedot.util.CueDotConstants;
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

    @Inject
    MoviesPresenter _presenter;

    private Unbinder _unbinder;

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
        _presenter.getMoviesOrderedBy(CueDotConstants.ORDER_BY_POPULAR);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setEnableLoadMore(boolean enabled) {

    }

    @Override
    public void showError(Throwable throwable) {
        Timber.e(throwable);
    }

    @Override
    public void hideError() {

    }

    @Override
    public void showMoviesList(List<IMovie> movies) {

        // Just to test Detail movie activity
        /*final IMovie movie = movies.get(0);
        ((TextView) findViewById(R.id.textList)).setText(strList);
        findViewById(R.id.textList).setOnClickListener(view -> {
            Intent intent = new Intent(MoviesActivity.this, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, (Movie)movie);
            startActivity(intent);
        });*/
        ((MoviesAdapter) moviesRecyclerView.getAdapter()).setData(movies);

    }

    private void setupView() {

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setAdapter(new MoviesAdapter());

        //orderingSpinner.setAdapter(new SimpleAdapter(this, ));
    }
}
