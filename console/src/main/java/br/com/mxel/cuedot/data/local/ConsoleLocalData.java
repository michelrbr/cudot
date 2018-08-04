package br.com.mxel.cuedot.data.local;

import java.util.Collection;
import java.util.List;

import br.com.mxel.cuedot.data.model.Movie;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by michelribeiro on 03/08/17.
 */

public class ConsoleLocalData implements ILocalDataSource {
    @Override
    public Maybe<List<Movie>> getFavoriteMoviesList() {
        return Maybe.empty();
    }

    @Override
    public Single<Movie> getMovie(int movieId) {
        return Single.just(new Movie());
    }

    @Override
    public Completable insertMovieToFavorites(Movie movie) {
        return Completable.complete();
    }

    @Override
    public Completable removeMovieFromFavorite(Movie movie) {
        return Completable.complete();
    }
}
