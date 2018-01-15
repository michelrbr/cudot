package br.com.mxel.cuedot.data.local;

import java.util.List;

import br.com.mxel.cuedot.data.model.IMovie;
import io.reactivex.Observable;

/**
 * Created by michelribeiro on 31/07/17.
 */

public interface ILocalDataSource {

    Observable<List<IMovie>> getFavoriteMoviesList();
    Observable<IMovie> getMovie(long movieId);
    void insertMovieToFavorites(IMovie movie) throws Exception;
    void removeMovieFromFavorite(long movieId) throws Exception;
}
