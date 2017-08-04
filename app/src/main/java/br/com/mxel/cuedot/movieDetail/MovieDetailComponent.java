package br.com.mxel.cuedot.movieDetail;

import dagger.Component;

/**
 * Created by michelribeiro on 04/08/17.
 */
@MovieDetail
@Component(modules = MovieDetailModule.class)
public interface MovieDetailComponent {

    void inject(MovieDetailActivity target);
}
