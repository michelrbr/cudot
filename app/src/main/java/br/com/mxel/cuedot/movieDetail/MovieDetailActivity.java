package br.com.mxel.cuedot.movieDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.CueDotApplication;
import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.data.model.MovieVideo;

public class MovieDetailActivity extends AppCompatActivity implements IMovieDetailView {

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
                .appComponent(CueDotApplication.getAppComponent())
                .movieDetailModule(new MovieDetailModule(movie))
                .build()
                .inject(this);

        _presenter.bind(this);
        _presenter.fetchMovieDetails();
    }

    @Override
    public void showMovieLoading(boolean show) {

    }

    @Override
    public void showMovie(Movie movie) {

        setTitle(movie.title);

        ((TextView) findViewById(R.id.text_description)).setText(movie.synopsis);
    }

    @Override
    public void showMovieError(Throwable error) {

    }

    @Override
    public void hideMovieError() {

    }

    @Override
    public void showVideosLoading(boolean show) {

    }

    @Override
    public void showVideos(List<MovieVideo> movieVideos) {

    }

    @Override
    public void showVideosError(Throwable error) {

    }

    @Override
    public void hideVideosError() {

    }
}
