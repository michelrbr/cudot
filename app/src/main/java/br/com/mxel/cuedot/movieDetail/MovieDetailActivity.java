package br.com.mxel.cuedot.movieDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.Movie;

public class MovieDetailActivity extends AppCompatActivity implements IMovieDetail{

    private static final String LOG_TAG = MovieDetailActivity.class.getSimpleName();
    public static final String EXTRA_MOVIE = "extraMovie";

    @Inject
    MovieDetailPresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = (Movie) getIntent().getExtras().getSerializable(EXTRA_MOVIE);

        DaggerMovieDetailComponent.builder()
                .movieDetailModule(new MovieDetailModule(movie, this))
                .build()
                .inject(this);

        _presenter.fetchMovieDetails();
    }

    @Override
    public void showMovie(Movie movie) {

        setTitle(movie.title);

        ((TextView) findViewById(R.id.text_description)).setText(movie.synopsis);
    }
}
