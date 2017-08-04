package br.com.mxel.cuedot;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 01/08/17.
 */
@Module
public class AppModule {

    private final Application _application;

    public AppModule(Application application) {
        this._application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return _application;
    }

    @Provides
    @Named("isDebug")
    public boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }
}
