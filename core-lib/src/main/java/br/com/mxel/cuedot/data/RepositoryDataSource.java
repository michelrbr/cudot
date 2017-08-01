package br.com.mxel.cuedot.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.mxel.cuedot.data.local.ILocalDataSource;
import br.com.mxel.cuedot.data.model.ListResult;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.data.remote.IRemoteDataSource;
import io.reactivex.Observable;

/**
 * Created by michelribeiro on 31/07/17.
 */
@Singleton
public class RepositoryDataSource {

    private IRemoteDataSource _remoteData;
    private ILocalDataSource _localData;

    @Inject
    public RepositoryDataSource(IRemoteDataSource remoteData, ILocalDataSource locaData) {
        _remoteData = remoteData;
        _localData = locaData;
    }

    public Observable<ListResult> getFavoriteMoviesList() {
        return _localData.getFavoriteMoviesList();
    }

    public void insertMovieToFavorites(Movie movie) throws Exception {
        _localData.insertMovieToFavorites(movie);
    }

    public void deleteMovieFromFavorite(long movieId) throws Exception {
        _localData.deleteMovieFromFavorite(movieId);
    }

    public Observable<ListResult> getMoviesOrderBy(String orderBy) {
        return _remoteData.getMoviesOrderBy(orderBy);
    }

    public Observable<Movie> getMovie(long movieId) {

        // Check local data source before request remote one
        return _remoteData.getMovie(movieId);
    }
}
