package br.com.mxel.cuedot;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 07/08/17.
 */
@Module
public class AndroidModule {

    private final Application _application;

    public AndroidModule(Application application) {
        this._application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return _application;
    }
}
