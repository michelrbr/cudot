package br.com.mxel.cuedot.movieDetail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.CueDotApplication;
import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.data.model.IMovieVideo;
import br.com.mxel.cuedot.data.remote.model.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MovieDetailActivity extends AppCompatActivity implements IMovieDetailView {

    public static final String EXTRA_MOVIE = "extraMovie";

    // Views
    @BindView(R.id.backdrop_image)
    AppCompatImageView backdropImage;
    @BindView(R.id.description_text_view)
    AppCompatTextView descriptionTextView;
    @BindView(R.id.release_date_text_view)
    AppCompatTextView releaseDateTextView;
    @BindView(R.id.rating)
    RatingBar ratingBar;
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;
    @BindView(R.id.error_text_view)
    AppCompatTextView errorTextView;

    @Inject
    MovieDetailPresenter _presenter;

    private Unbinder _unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        _unbinder = ButterKnife.bind(this);

        ratingBar.setNumStars(5);

        ActionBar mActionBar = getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        try {
            Movie movie = (Movie) getIntent().getExtras().getSerializable(EXTRA_MOVIE);

            DaggerMovieDetailComponent.builder()
                    .appComponent(CueDotApplication.getAppComponent())
                    .movieDetailModule(new MovieDetailModule(movie))
                    .build()
                    .inject(this);
        } catch (NullPointerException npe) {
            onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        _presenter.bind(this);
        _presenter.fetchMovieDetails();
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
    public void showMovieLoading(boolean show) {

        if(show) {
            loadingProgress.setVisibility(View.VISIBLE);
            backdropImage.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        } else {
            loadingProgress.setVisibility(View.GONE);
            backdropImage.setVisibility(View.VISIBLE);
            descriptionTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMovie(IMovie movie) {

        setTitle(movie.getTitle());

        Picasso.with(this)
                .load(movie.getBackdropPath())
                .into(backdropImage);
        descriptionTextView.setText(movie.getSynopsis());
        releaseDateTextView.setText(movie.getReleaseDate());
        ratingBar.setRating(movie.getRating() / 2);
    }

    @Override
    public void showMovieError(Throwable error) {
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(error.getMessage());
    }

    @Override
    public void hideMovieError() {
        errorTextView.setVisibility(View.GONE);
    }

    @Override
    public void showVideosLoading(boolean show) {

    }

    @Override
    public void showVideos(List<? extends IMovieVideo> movieVideos) {

    }

    @Override
    public void showVideosError(Throwable error) {

    }

    @Override
    public void hideVideosError() {

    }

    @Override
    public void markAsFavorite() {
        ((Button)findViewById(R.id.favorite_button)).setText("Remove from favorite");
    }

    @Override
    public void unmarkAsFavorite() {
        ((Button)findViewById(R.id.favorite_button)).setText("Add to favorite");
    }

    @OnClick(R.id.favorite_button)
    public void toggleFavorite(View view) {
        _presenter.toggleFavorite();
    }
}
