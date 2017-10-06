package br.com.mxel.cuedot;

import java.util.Scanner;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.mxel.cuedot.util.ConsoleScheduleProvider;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 03/08/17.
 */

@Module
public class ConsoleModule {

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
        return false;
    }

    @Provides
    @Singleton
    public ISchedulerProvider provideScheluler() {
        return new ConsoleScheduleProvider();
    }

    @Provides
    @Singleton
    public Scanner provideScanner() {
        return new Scanner(System.in);
    }
}
