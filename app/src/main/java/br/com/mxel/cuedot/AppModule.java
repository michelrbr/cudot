package br.com.mxel.cuedot;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.mxel.cuedot.util.AppSchedulerProvider;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 01/08/17.
 */
@Module
public class AppModule {

    @Provides
    @Named("baseUrl")
    public String provideBaseUrl() {
        return String.format(
                "%s/%s/",
                BuildConfig.THE_MOVIE_DB_API_URL,
                BuildConfig.THE_MOVIE_DB_API_VERSION
        );
    }

    @Provides
    @Named("apiKey")
    public String provideApiKey() {
        return BuildConfig.THE_MOVIE_DB_API_KEY;
    }

    @Provides
    @Named("isDebug")
    public boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    public ISchedulerProvider provideScheduler() {
        return new AppSchedulerProvider();
    }
}
