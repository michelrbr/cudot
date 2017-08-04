package br.com.mxel.cuedot;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 03/08/17.
 */

@Module
public class ConsoleModule {

    @Provides
    @Named("isDebug")
    public boolean provideIsDebug() {
        return true;
    }
}
