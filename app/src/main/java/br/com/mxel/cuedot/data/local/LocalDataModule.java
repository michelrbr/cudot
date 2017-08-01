package br.com.mxel.cuedot.data.local;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by michelribeiro on 01/08/17.
 */
@Module
public class LocalDataModule {

    @Singleton
    @Provides
    public ILocalDataSource providesLocalDataSource() {
        return new CueDotLocalData();
    }
}
