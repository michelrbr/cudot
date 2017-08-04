package br.com.mxel.cuedot.movieDetail;

import br.com.mxel.cuedot.data.model.Movie;
import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 04/08/17.
 */
@Module
public class MovieDetailModule {

    private final Movie _movie;
    private final IMovieDetail _movieDetailView;

    public MovieDetailModule(Movie movie, IMovieDetail movieDetailView) {
        _movie = movie;
        _movieDetailView = movieDetailView;
    }

    @Provides
    public MovieDetailPresenter provideMovieDetailPresenter() {
        return new MovieDetailPresenter(_movie, _movieDetailView);
    }
}
