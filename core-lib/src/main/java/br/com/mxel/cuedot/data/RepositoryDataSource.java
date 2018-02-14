package br.com.mxel.cuedot.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.mxel.cuedot.data.local.ILocalDataSource;
import br.com.mxel.cuedot.data.remote.IRemoteDataSource;
import br.com.mxel.cuedot.data.remote.model.ListMovieResult;
import br.com.mxel.cuedot.data.remote.model.ListVideoResult;
import br.com.mxel.cuedot.data.remote.model.Movie;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by michelribeiro on 31/07/17.
 */
@Singleton
public class RepositoryDataSource {

    private IRemoteDataSource _remoteData;
    private ILocalDataSource _localData;

    @Inject
    public RepositoryDataSource(IRemoteDataSource remoteData, ILocalDataSource localData) {
        _remoteData = remoteData;
        _localData = localData;
    }

    public Maybe<List<Movie>> getFavoriteMoviesList() {
        return _localData.getFavoriteMoviesList();
    }

    public Completable addMovieToFavorites(Movie movie) {
        return _localData.insertMovieToFavorites(movie);
    }

    public Completable removeMovieFromFavorites(long movieId) {
        return _localData.removeMovieFromFavorite(movieId);
    }

    public Single<ListMovieResult> getMoviesOrderBy(String orderBy, int page) {
        return _remoteData.getMoviesOrderBy(orderBy, page);
    }

    public Single<Movie> getMovie(long movieId) {

        // Check local data source before request remote one
        return Single
                .concat(_localData.getMovie(movieId), _remoteData.getMovie(movieId))
                .filter(movie -> movie.getId() != 0)
                .firstOrError();
    }

    public Single<ListVideoResult> getMovieVideos(long movieId) {

        return _remoteData.getMovieVideos(movieId);
    }

    public Single<ListMovieResult> getSimilarMovies(long movieId) {
        
        return _remoteData.getSimilarMovies(movieId);
    }
}
