package br.com.mxel.cuedot.data.local;

import br.com.mxel.cuedot.data.model.ListMovieResult;
import br.com.mxel.cuedot.data.model.Movie;
import io.reactivex.Observable;

/**
 * Created by michelribeiro on 01/08/17.
 */

public class CueDotLocalData implements ILocalDataSource {

    @Override
    public Observable<ListMovieResult> getFavoriteMoviesList() {
        return null;
    }

    @Override
    public Observable<Movie> getMovie(long movieId) {
        return null;
    }

    @Override
    public void insertMovieToFavorites(Movie movie) throws Exception {

    }

    @Override
    public void deleteMovieFromFavorite(long movieId) throws Exception {

    }
}
