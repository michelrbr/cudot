package br.com.mxel.cuedot.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.CueDotApplication;
import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.movieDetail.MovieDetailActivity;
import br.com.mxel.cuedot.util.CueDotConstants;

public class MoviesActivity extends AppCompatActivity implements IMoviesView{

    private static final String LOG_TAG = MoviesActivity.class.getSimpleName();

    @Inject
    MoviesPresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        DaggerMoviesComponent.builder()
                .appComponent(CueDotApplication.getAppComponent())
                .moviesModule(new MoviesModule(this))
                .build()
                .inject(this);

        _presenter.getMoviesOrderedBy(CueDotConstants.ORDER_BY_POPULAR);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(Throwable throwable) {
        Log.e(LOG_TAG, "Cassildis Error: " + throwable.getMessage());
    }

    @Override
    public void showMoviesList(List<Movie> movies) {

        String strList = "MOVIES LIST\n\n";
        for (Movie m : movies) {
            Log.d(LOG_TAG, m.title);
            strList += m.title + "\n";
        }


        // Just to test Detail movie activity
        final Movie movie = movies.get(0);
        ((TextView) findViewById(R.id.textList)).setText(strList);
        findViewById(R.id.textList).setOnClickListener(view -> {
            Intent intent = new Intent(MoviesActivity.this, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
            startActivity(intent);
        });

    }
}
