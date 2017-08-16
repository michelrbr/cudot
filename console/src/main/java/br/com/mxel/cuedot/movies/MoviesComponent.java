package br.com.mxel.cuedot.movies;

import br.com.mxel.cuedot.ConsoleComponent;
import dagger.Component;

/**
 * Created by michelribeiro on 03/08/17.
 */
@Movies
@Component(dependencies = ConsoleComponent.class, modules = MoviesModule.class)
public interface MoviesComponent {

    void inject(MoviesView target);
}
