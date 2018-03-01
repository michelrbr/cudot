package br.com.mxel.cuedot.data.local;

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
        return null;
    }

    @Override
    public Single<Movie> getMovie(int movieId) {
        return null;
    }

    @Override
    public Completable insertMovieToFavorites(Movie movie) {
        return null;
    }

    @Override
    public Completable removeMovieFromFavorite(Movie movie) {
        return null;
    }
}
