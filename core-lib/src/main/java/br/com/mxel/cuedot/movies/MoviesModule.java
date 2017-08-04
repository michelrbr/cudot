package br.com.mxel.cuedot.movies;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 03/08/17.
 */

@Module
public class MoviesModule {

    private final IMoviesView _movieView;

    public MoviesModule(IMoviesView movieView) {
        _movieView = movieView;
    }

    @Provides
    public MoviesPresenter provideMoviesPresenter(RepositoryDataSource repository) {

        return new MoviesPresenter(repository, _movieView);
    }
}
