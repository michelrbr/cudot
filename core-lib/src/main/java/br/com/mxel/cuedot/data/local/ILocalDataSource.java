package br.com.mxel.cuedot.data.local;

import java.util.List;

import br.com.mxel.cuedot.data.remote.model.Movie;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by michelribeiro on 31/07/17.
 */

public interface ILocalDataSource {

    Maybe<List<Movie>> getFavoriteMoviesList();
    Single<Movie> getMovie(Long movieId);
    Completable insertMovieToFavorites(Movie movie);
    Completable removeMovieFromFavorite(Long movieId);
}
