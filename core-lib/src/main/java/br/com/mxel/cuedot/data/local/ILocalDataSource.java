package br.com.mxel.cuedot.data.local;

import br.com.mxel.cuedot.data.model.ListResult;
import br.com.mxel.cuedot.data.model.Movie;
import io.reactivex.Observable;

/**
 * Created by michelribeiro on 31/07/17.
 */

public interface ILocalDataSource {

    Observable<ListResult> getFavoriteMoviesList();
    Observable<Movie> getMovie(long movieId);
    void insertMovieToFavorites(Movie movie) throws Exception;
    void deleteMovieFromFavorite(long movieId) throws Exception;
}
