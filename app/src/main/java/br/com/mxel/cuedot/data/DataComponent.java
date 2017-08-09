package br.com.mxel.cuedot.data;

import javax.inject.Singleton;

import br.com.mxel.cuedot.AndroidModule;
import br.com.mxel.cuedot.AppModule;
import br.com.mxel.cuedot.data.local.LocalDataModule;
import br.com.mxel.cuedot.data.remote.NetworkModule;
import br.com.mxel.cuedot.util.ISchedulerProvider;
import dagger.Component;

/**
 * Created by michelribeiro on 01/08/17.
 */
@Singleton
@Component( modules = {AndroidModule.class, AppModule.class, NetworkModule.class, LocalDataModule.class})
public interface DataComponent {

    RepositoryDataSource getRepository();

    ISchedulerProvider getScheduleProvider();
}
