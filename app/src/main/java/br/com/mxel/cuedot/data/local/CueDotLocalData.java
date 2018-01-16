package br.com.mxel.cuedot.data.local;

import java.util.List;

import br.com.mxel.cuedot.data.model.IMovie;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by michelribeiro on 01/08/17.
 */

public class CueDotLocalData implements ILocalDataSource {

    @Override
    public Observable<List<IMovie>> getFavoriteMoviesList() {
        return null;
    }

    @Override
    public Observable<IMovie> getMovie(long movieId) {
        return null;
    }

    @Override
    public Single<Void> insertMovieToFavorites(IMovie movie) {
        return null;
    }

    @Override
    public Single<Void> removeMovieFromFavorite(long movieId) {
        return null;
    }
}
