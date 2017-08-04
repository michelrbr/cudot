package br.com.mxel.cuedot.data;

import javax.inject.Singleton;

import br.com.mxel.cuedot.ConsoleModule;
import br.com.mxel.cuedot.data.local.LocalDataModule;
import br.com.mxel.cuedot.data.remote.NetworkModule;
import dagger.Component;

/**
 * Created by michelribeiro on 03/08/17.
 */
@Singleton
@Component( modules = {ConsoleModule.class, NetworkModule.class, LocalDataModule.class})
public interface DataComponent {

    RepositoryDataSource getRepository();
}
