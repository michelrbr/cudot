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

//@Module(subcomponents = MovieDetailComponent.class)
@Module
public class ConsoleModule {

    @Provides
    @Named("baseUrl")
    public String provideBaseUrl() {
        return String.format(
                "%s/%s/",
                "http://api.themoviedb.org",
                "3"
        );
    }

    @Provides
    @Named("apiKey")
    public String provideApiKey() {
        return "YOUR_KEY";
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
