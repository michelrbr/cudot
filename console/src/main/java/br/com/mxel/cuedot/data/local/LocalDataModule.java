package br.com.mxel.cuedot.data.local;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 03/08/17.
 */

@Module
public class LocalDataModule {

    @Provides
    @Singleton
    public ILocalDataSource provideLocalDataSource() {
        return new ConsoleLocalData();
    }
}
