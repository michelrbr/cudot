package br.com.mxel.cuedot.movieDetail;

import br.com.mxel.cuedot.AppComponent;
import dagger.Component;

/**
 * Created by michelribeiro on 04/08/17.
 */
@MovieDetail
@Component(dependencies = AppComponent.class, modules = MovieDetailModule.class)
public interface MovieDetailComponent {

    void inject(MovieDetailActivity target);
}
