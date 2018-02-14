package br.com.mxel.cuedot.data.local;

import android.content.Context;

import javax.inject.Named;
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
    public ILocalDataSource providesLocalDataSource(
            Context context,
            @Named("databaseName") String databaseName,
            @Named("databaseVersion") int databaseVersion) {

        return new CueDotLocalData(context, databaseName, null, databaseVersion);
    }
}
