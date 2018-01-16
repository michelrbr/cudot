package br.com.mxel.cuedot.data.local;

import java.util.List;

import br.com.mxel.cuedot.data.model.IMovie;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by michelribeiro on 31/07/17.
 */

public interface ILocalDataSource {

    Observable<List<IMovie>> getFavoriteMoviesList();
    Observable<IMovie> getMovie(long movieId);
    Single<Void> insertMovieToFavorites(IMovie movie);
    Single<Void> removeMovieFromFavorite(long movieId);
}
