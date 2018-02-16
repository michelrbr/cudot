package br.com.mxel.cuedot;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.mxel.cuedot.util.AppSchedulerProvider;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

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

    @Provides
    @Named("databaseName")
    public String provideDatabaseName() {
        return "cuedot.db";
    }

    @Provides
    @Named("databaseVersion")
    public int provideDatabaseVersion() {
        return 1;
    }

    @Provides
    @Singleton
    public Cache provideOkHttpCache(@Named("cacheDir") File cacheDir) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        return new Cache(cacheDir, cacheSize);
    }
}
