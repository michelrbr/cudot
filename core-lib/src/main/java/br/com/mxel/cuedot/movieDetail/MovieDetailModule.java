package br.com.mxel.cuedot.movieDetail;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 04/08/17.
 */
@Module
public class MovieDetailModule {

    private final Movie _movie;

    public MovieDetailModule(Movie movie) {
        _movie = movie;
    }

    @Provides
    public MovieDetailPresenter provideMovieDetailPresenter(RepositoryDataSource repository,
                                                            ISchedulerProvider scheduler) {
        return new MovieDetailPresenter(repository, scheduler, _movie);
    }
}
