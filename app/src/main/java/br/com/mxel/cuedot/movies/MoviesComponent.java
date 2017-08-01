package br.com.mxel.cuedot.movies;

import br.com.mxel.cuedot.data.DataComponent;
import br.com.mxel.cuedot.util.ActivityScoped;
import dagger.Component;

/**
 * Created by michelribeiro on 01/08/17.
 */
@ActivityScoped
@Component(dependencies = DataComponent.class)
public interface MoviesComponent {

    void inject(MoviesActivity activity);
}
