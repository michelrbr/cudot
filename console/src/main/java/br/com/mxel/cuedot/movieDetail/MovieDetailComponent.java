package br.com.mxel.cuedot.movieDetail;

import br.com.mxel.cuedot.ConsoleComponent;
import dagger.Component;

/**
 * Created by michelribeiro on 18/08/17.
 */
@MovieDetail
//@Subcomponent(modules = MovieDetailModule.class)
@Component(dependencies = ConsoleComponent.class, modules = MovieDetailModule.class)
public interface MovieDetailComponent {

    void inject(MovieDetailView target);
    /*@Subcomponent.Builder
    interface Builder {
        MovieDetailComponent.Builder detailModule(MovieDetailModule detailModule);
        MovieDetailComponent build();
    }*/
}
