package br.com.mxel.cuedot.movieDetail;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.mxel.cuedot.CueDotApplication;
import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.data.model.MovieVideo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

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

    private MenuItem _favoriteButton;
    private boolean _isFavorite;

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

            _presenter.bind(this);
            _presenter.fetchMovieDetails();
        } catch (NullPointerException npe) {
            onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        _favoriteButton = menu.findItem(R.id.action_favorite);
        setIsFavorite(_isFavorite);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_favorite:

                toggleFavorite();
                return true;
            case android.R.id.home:

                onBackPressed();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!_presenter.isViewBound()) {
            _presenter.bind(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        _presenter.unbind();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if(_presenter.isViewBound()) {
            _presenter.unbind();
            _unbinder.unbind();
        }
        super.onDestroy();
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
    public void showMovie(Movie movie) {

        setTitle(movie.getTitle());

        Picasso.with(this)
                .load(movie.getBackdropPath())
                .into(backdropImage);
        descriptionTextView.setText(movie.getSynopsis());
        releaseDateTextView.setText(movie.getReleaseDate());
        ratingBar.setRating(movie.getRating() / 2);
        setIsFavorite(movie.isFavorite());
    }

    @Override
    public void showMovieError(Throwable error) {
        Timber.e(error);
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
    public void showVideos(List<? extends MovieVideo> movieVideos) {

    }

    @Override
    public void showVideosError(Throwable error) {
        Timber.e(error);
    }

    @Override
    public void hideVideosError() {

    }

    @Override
    public void markAsFavorite() {

        if(!_isFavorite) {
            setIsFavorite(true);
        }
    }

    @Override
    public void unmarkAsFavorite() {
        if(_isFavorite) {
            setIsFavorite(false);
        }
    }

    private void toggleFavorite() {
        setIsFavorite(!_isFavorite);
        _presenter.toggleFavorite();
    }

    private void setIsFavorite(boolean fav) {

        _isFavorite = fav;
        if(_favoriteButton != null) {
            int icon = (_isFavorite) ? R.drawable.ic_red_heart_24dp : R.drawable.ic_white_heart_24dp;
            _favoriteButton.setIcon(icon);
        }
    }
}
