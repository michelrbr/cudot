package br.com.mxel.cuedot.movies;

import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 03/08/17.
 */

@Module
public class MoviesModule {

    @Provides
    public MoviesPresenter provideMoviesPresenter(RepositoryDataSource repository,
                                                  ISchedulerProvider scheduler) {

        return new MoviesPresenter(repository, scheduler);
    }
}
