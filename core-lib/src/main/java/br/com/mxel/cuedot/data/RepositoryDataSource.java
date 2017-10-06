package br.com.mxel.cuedot.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.mxel.cuedot.data.local.ILocalDataSource;
import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.data.remote.model.ListMovieResult;
import br.com.mxel.cuedot.data.remote.model.ListVideoResult;
import br.com.mxel.cuedot.data.remote.IRemoteDataSource;
import br.com.mxel.cuedot.data.remote.model.Movie;
import io.reactivex.Observable;

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

    public Observable<List<IMovie>> getFavoriteMoviesList() {
        return _localData.getFavoriteMoviesList();
    }

    public void insertMovieToFavorites(IMovie movie) throws Exception {
        _localData.insertMovieToFavorites(movie);
    }

    public void deleteMovieFromFavorite(long movieId) throws Exception {
        _localData.deleteMovieFromFavorite(movieId);
    }

    public Observable<ListMovieResult> getMoviesOrderBy(String orderBy, int page) {
        return _remoteData.getMoviesOrderBy(orderBy, page);
    }

    public Observable<Movie> getMovie(long movieId) {

        // Check local data source before request remote one
        return _remoteData.getMovie(movieId);
    }

    public Observable<ListVideoResult> getMovieVideos(long movieId) {

        return _remoteData.getMovieVideos(movieId);
    }

    public Observable<ListMovieResult> getSimilarMovies(long movieId) {
        
        return _remoteData.getSimilarMovies(movieId);
    }
}
