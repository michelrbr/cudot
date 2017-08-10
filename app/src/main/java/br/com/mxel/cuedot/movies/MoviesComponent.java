package br.com.mxel.cuedot.movies;

import br.com.mxel.cuedot.AppComponent;
import dagger.Component;

/**
 * Created by michelribeiro on 01/08/17.
 */
@Movies
@Component(dependencies = AppComponent.class, modules =  MoviesModule.class)
public interface MoviesComponent {

    void inject(MoviesActivity activity);
}
