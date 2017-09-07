package br.com.mxel.cuedot.data.local;

import br.com.mxel.cuedot.data.model.ListMovieResult;
import br.com.mxel.cuedot.data.model.Movie;
import io.reactivex.Observable;

/**
 * Created by michelribeiro on 31/07/17.
 */

public interface ILocalDataSource {

    Observable<ListMovieResult> getFavoriteMoviesList();
    Observable<Movie> getMovie(long movieId);
    void insertMovieToFavorites(Movie movie) throws Exception;
    void deleteMovieFromFavorite(long movieId) throws Exception;
}
